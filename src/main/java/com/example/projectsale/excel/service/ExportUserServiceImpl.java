package com.example.projectsale.excel.service;

import com.example.projectsale.exception.ApiRequestException;
import com.example.projectsale.user.dto.UserDto;
import com.example.projectsale.user.dto.request.UserSearchDtoRequest;
import com.example.projectsale.user.service.UserService;
import com.example.projectsale.utils.DateUtil;
import com.example.projectsale.excel.common.ExcelUtil;
import com.example.projectsale.excel.dto.ExtractInfo;
import com.example.projectsale.excel.dto.TableHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ExportUserServiceImpl implements ExportUserService {

    private final UserService userService;

    @Override
    public ByteArrayInputStream exportUsers(UserSearchDtoRequest request) {
        List<UserDto> users = (List<UserDto>) userService.findUsersByCondition(request).getBody().getData();
        ExtractInfo extractInfo = request.getExtractInfo();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Users info");

            // Create header row
            Integer rowIdx = ExcelUtil.createHeaderExcel(workbook, extractInfo, sheet);

            // Check isEmpty
            if (CollectionUtils.isEmpty(users) || CollectionUtils.isEmpty(extractInfo.getTableHeader())) {
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }

            Map<String, TableHeader> tableHeaderMap = extractInfo.getTableHeader().stream()
                    .collect(Collectors.toMap(TableHeader::getField, Function.identity()));

            for (UserDto user : users) {
                Row row = sheet.createRow(rowIdx++);
                int cellIdx = 0;

                cellIdx = createCell(row, tableHeaderMap, "email", cellIdx, user.getEmail());
                cellIdx = createCell(row, tableHeaderMap, "avatar", cellIdx, user.getAvatar());
                cellIdx = createCell(row, tableHeaderMap, "dob", cellIdx,
                        DateUtil.dateToString(user.getDateOfBirth(), DateUtil.FORMAT_DD_MM_YYYY));
                cellIdx = createCell(row, tableHeaderMap, "status", cellIdx, user.getStatus());
                cellIdx = createCell(row, tableHeaderMap, "isDeleted", cellIdx, user.getIsDeleted() ? "X" : "");
            }

            for (int i = 0; i < extractInfo.getTableHeader().size(); i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            log.error("Export fail: {}", e.getMessage());
            throw new ApiRequestException("Export fail!..");
        }
    }

    private int createCell(Row row, Map<String, TableHeader> headerMaps, String field, int cellIdx, String value) {
        if (headerMaps.containsKey(field)) {
            row.createCell(cellIdx++).setCellValue(value == null ? "" : value);
        }
        return cellIdx;
    }
}
