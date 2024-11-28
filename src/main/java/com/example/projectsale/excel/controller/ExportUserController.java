package com.example.projectsale.excel.controller;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.excel.service.ExportUserService;
import com.example.projectsale.user.dto.request.
        UserSearchDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.Date;

import static com.example.projectsale.excel.common.ExcelUtil.DATE_FORMAT.DATE_FORMAT_YYYYMMDD;
import static com.example.projectsale.excel.common.ExcelUtil.DateToString;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_ONE + "/excel")
@RequiredArgsConstructor
public class ExportUserController {

    private final ExportUserService exportUserService;

    @PreAuthorize("hasAnyAuthority('SUPPER_ADMIN')")
    @PostMapping("/export-users")
    public ResponseEntity<InputStreamResource> exportUserListing(@RequestBody UserSearchDtoRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + ("Users" + "_" + DateToString(new Date(), DATE_FORMAT_YYYYMMDD) + ".xlsx"));
        ByteArrayInputStream in = exportUserService.exportUsers(request);
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

}
