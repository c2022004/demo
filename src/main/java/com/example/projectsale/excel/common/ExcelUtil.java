package com.example.projectsale.excel.common;

import com.example.projectsale.exception.ApiRequestException;
import com.example.projectsale.excel.dto.ExtractInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


@Component
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static String API_URL = "";
    public static final String BLANK = "";
    public static final String SPACE = " ";
    public static String EMPTY = "string";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Value("${project.setting.domain}")
    public void setApiDomain(String apiDomainUrl) {
        ExcelUtil.API_URL = apiDomainUrl;
    }

    public static String hiddenToken(String prefix, String s) {
        if (Strings.isEmpty(s) || !s.contains(prefix)) return s;
        int startIndex = s.indexOf(prefix) + prefix.length();
        int stopIndex = startIndex + s.length();
        StringBuilder builder = new StringBuilder(s);
        return builder.replace(startIndex, stopIndex, "***").toString();
    }

    public static final class RESPONSE_JSON_KEY {
        public static final String DATA = "data";
        public static final String ACCESS_TOKEN = "access_token";
        public static final String TOKEN_TYPE = "token_type";
        public static final String REFRESH_TOKEN = "refresh_token";
        public static final String EXPIRED_IN = "expired_in";
        public static final String SCOPE = "scope";
        public static final String STATUS = "status";
        public static final String MESSAGE = "message";
        public static final String MESSAGE_CODE = "message_code";
        public static final String ERROR_CODE = "error_code";
        public static final String ERROR_TYPE = "error_type";
        public static final String ERROR_MSG = "error_message";
        public static final String TOTAL_RECORD = "total_record";
        public static final String HAS_MORE_RECORD = "has_more_record";
    }

    public static final class CHARSET {
        public static final String UTF_8 = "UTF-8";
        public static final String WINDOW_1252 = "Windows-1252";
    }

    public static final class REQ_RES_CONTENT_TYPE {
        public static final String JSON = "application/json";
        public static final String XML = "application/xml";
    }

    public static final class DATE_FORMAT {
        public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
        public static final String DATE_FORMAT_DDMMYYY = "ddMMyyyy";
        public static final String DATE_FORMAT_HAIFU_DDMMYYYY = "dd-MM-yyyy";
        public static final String DATE_FORMAT_SLASH_DDMMYYYY = "dd/MM/yyyy";
        public static final String DATE_FORMAT_SLASH_DDMMYYYYHHMM = "dd/MM/yyyy HH:mm";
        public static final String DATE_FORMAT_SLASH_DDMMYYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";
        public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        public static final String DATE_TIME_FORMATTER_WITH_ZONE = "yyyy-MM-dd HH:mm:ss Z";
        public static final String DATE_TIME_FORMATTER_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        public static final String DATE_TIME_HM = "HH:mm";
    }

    public static String DateToString(Date date, String pattern) {
        if (Objects.isNull(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(true);
        return sdf.format(date);
    }

    public static String DateToStringLocation(Long timezone, String patter, Long timeNumber) {
        try {
            if (timezone != null) {
                long caltimezone = timezone * 60 * 60 * 1000;
                long date = timeNumber + caltimezone;
                DateFormat formatter = new SimpleDateFormat(patter);
                formatter.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
                return formatter.format(date);
            } else {
                DateFormat formatter = new SimpleDateFormat(patter);
                return formatter.format(timeNumber);
            }
        } catch (Exception e) {
            throw new ApiRequestException("", e.getMessage());
        }
    }

    public static String NumberToStringLocation(Double amount, String currency, char thousand, char point, String currencySight, String number) {
        try {
            DecimalFormatSymbols custom = new DecimalFormatSymbols();
            custom.setDecimalSeparator(point);
            custom.setGroupingSeparator(thousand);
            String pattern = currency.concat(number);
            DecimalFormat decimalFormat = new DecimalFormat(pattern, custom);
            return decimalFormat.format(amount).concat(currencySight);
        } catch (Exception e) {
            throw new ApiRequestException("", e.getMessage());
        }
    }

    public static InputStream getResource(ResourceLoader loader, String path) {
        try {
            InputStream is = loader.getResource("classpath:" + path).getInputStream();
            logger.info("Get resource success at path: {}, InputStream<{}>", path, is);
            return is;
        } catch (Exception e) {
            logger.error("Can not get resource at path: {}", path);
        }
        return null;
    }

    public static Font createHeaderFont(Workbook workbook, String key) {
        Font headerFont = workbook.createFont();
        if (key.compareTo("titleName") == 0) {
            headerFont.setFontHeightInPoints((short) 20);
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.DARK_TEAL.getIndex());
        }
        if (key.compareTo("titleData") == 0) {
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());
        }
        return headerFont;
    }

    public static Font createHeaderFontV2(Workbook workbook, String key) {
        Font headerFont = workbook.createFont();
        if (key.compareTo("titleName") == 0) {
            headerFont.setFontHeightInPoints((short) 28);
            headerFont.setBold(true);
            headerFont.setFontName("Tahoma");
            headerFont.setColor(IndexedColors.BLACK.getIndex());
        }
        if (key.compareTo("titleHeader") == 0) {
            headerFont.setFontHeightInPoints((short) 9);
            headerFont.setBold(true);
            headerFont.setFontName("Tahoma");
            headerFont.setColor(IndexedColors.BLACK.getIndex());
        }
        if (key.compareTo("titleData") == 0) {
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());
        }
        if (key.compareTo("titleNameSecond") == 0) {
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setBold(true);
            headerFont.setFontName("Tahoma");
            headerFont.setColor(IndexedColors.BLACK.getIndex());
        }
        if (key.compareTo("titleDataWithFontSize10") == 0) {
            headerFont.setFontHeightInPoints((short) 10);
            headerFont.setFontName("Tahoma");
            headerFont.setColor(IndexedColors.BLACK.getIndex());
        }
        if (key.compareTo("titleDataWithBorder") == 0) {
            headerFont.setFontHeightInPoints((short) 9);
            headerFont.setFontName("Tahoma");
            headerFont.setColor(IndexedColors.BLACK.getIndex());
        }
        if (key.compareTo("right") == 0) {
            headerFont.setFontHeightInPoints((short) 9);
            headerFont.setFontName("Liberation Sans");
            headerFont.setColor(IndexedColors.BLACK.getIndex());
        }
        return headerFont;
    }

    public static CellStyle createHeaderCellStyle(Workbook workbook, Font font, String key) {
        CellStyle cellStyle = workbook.createCellStyle();
        if (key.compareTo("headerCellStyle") == 0) {
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
        }
        if (key.compareTo("headerCellStyleTitle") == 0) {
            cellStyle.setFont(font);
        }
        if (key.compareTo("cellStyleHeader") == 0) {
            cellStyle.setFont(font);
        }
        if (key.compareTo("cellStyleHeader2") == 0) {
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }
        if (key.compareTo("cellStyleData") == 0) {
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }
        if (key.compareTo("headerCellStyleTitleSecond") == 0) {
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }
        if (key.compareTo("footerCellStyleTitle") == 0) {
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }
        if (key.compareTo("dataCellStyleWithFontSize10") == 0) {
            cellStyle.setFont(font);
        }
        if (key.compareTo("dataCellStyleWithBorder") == 0) {
            cellStyle.setFont(font);
            cellStyle.setBorderBottom(BorderStyle.THIN);
        }
        if (key.compareTo("cellTypeCheckBox") == 0) {
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        }
        if (key.compareTo("right") == 0) {
            cellStyle.setFont(font);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        }
        return cellStyle;
    }

    public static Object getDefaultValue(Object value, Object defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static String getDefaultValue(String value, String... defaultValue) {
        if (Strings.isBlank(value))
            return defaultValue.length == 1 ? defaultValue[0] : Strings.EMPTY;
        return value;
    }

    @SafeVarargs
    public static <T> List<T> getDefaultValue(List<T> value, List<T>... defaultValue) {
        if (CollectionUtils.isEmpty(value))
            return defaultValue.length == 1 ? defaultValue[0] : Collections.emptyList();
        return value;
    }

    @SafeVarargs
    public static <T> Set<T> getDefaultValue(Set<T> value, Set<T>... defaultValue) {
        if (CollectionUtils.isEmpty(value))
            return defaultValue.length == 1 ? defaultValue[0] : Collections.emptySet();
        return value;
    }

    @SafeVarargs
    public static <T> Collection<T> getDefaultValue(Collection<T> value, Collection<T>... defaultValue) {
        if (CollectionUtils.isEmpty(value))
            return defaultValue.length == 1 ? defaultValue[0] : Collections.emptyList();
        return value;
    }

    public static Integer getDefaultValue(Integer value, Integer... defaultValue) {
        if (value == null)
            return defaultValue.length == 1 ? defaultValue[0] : 0;
        return value;
    }

    public static Double getDefaultValue(Double value, Double... defaultValue) {
        if (value == null)
            return defaultValue.length == 1 ? defaultValue[0] : 0d;
        return value;
    }

    public static Long getDefaultValue(Long value, Long... defaultValue) {
        if (value == null)
            return defaultValue.length == 1 ? defaultValue[0] : 0L;
        return value;
    }

    public static Boolean getDefaultValue(Boolean value, Boolean... defaultValue) {
        if (value == null)
            return defaultValue.length == 1 ? defaultValue[0] : false;
        return value;
    }

    public static Long getLongDate(Row row, int i, String format, long timezone) {
        Cell cell = row.getCell(i);
        if (cell == null || Strings.isBlank(cell.getStringCellValue())) {
            return -1L;
        }
        CellType cellType = cell.getCellType();
        long caltimezone = timezone * 60 * 60 * 1000;
        if (cellType == CellType.STRING) {
            try {
                String dateStr = getStringValue(row, i);
                DateFormat formatter = new SimpleDateFormat(format);
                formatter.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
                Date date = formatter.parse(dateStr);
                return date.getTime() - caltimezone;
            } catch (Exception e) {
                return -1L;
            }
        } else {
            try {
                Date date = cell.getDateCellValue();
                return date.getTime() - caltimezone;
            } catch (Exception e) {
                return -1L;
            }
        }
    }

    public static String getStringValue(Row row, int index) {
        Cell cell = row.getCell(index);
        if (Objects.isNull(cell)) {
            return Strings.EMPTY;
        }
        return cell.getCellType() == CellType.NUMERIC ? String.valueOf(getIntValue(row, index)) : cell.getStringCellValue().trim();
    }


    public static String getStringValueV2(Row row, int index) {
        Cell cell = row.getCell(index);
        if (Objects.isNull(cell)) {
            return "0";
        } else {
            try {
                return cell.getStringCellValue().trim();
            } catch (Exception ex) {
                return Integer.valueOf((int) cell.getNumericCellValue()).toString();
            }
        }
    }

    public static Long getLongValue(String valueStr) {
        if (Strings.isNotBlank(valueStr)) {
            try {
                return Long.parseLong(valueStr);
            } catch (Exception ex) {
                return 0L;
            }
        }
        return 0L;
    }

    public static Long getLongValue(Cell cell) {
        if (cell != null) {
            return getLongValue(cell.getStringCellValue());
        }
        return 0L;
    }

    public static boolean invalidNumber(Row row, int index) {
        Cell cell = row.getCell(index);
        if (Objects.isNull(cell) || cell.getCellType() == CellType.NUMERIC) {
            return false;
        }
        String valueStr = cell.getStringCellValue().trim();
        if (Strings.isBlank(valueStr)) {
            return false;
        }
        return !NumberUtils.isDigits(valueStr);
    }

    public static Integer getIntValue(Row row, int index) {
        Cell cell = row.getCell(index);
        try {
            if (Objects.isNull(cell)) {
                return 0;
            }
            return cell.getCellType() == CellType.STRING ? Integer.parseInt(getStringValue(row, index)) : (int) cell.getNumericCellValue();
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static Integer createTableHeader(int rowIdx, ExtractInfo extractInfo, CellStyle headerCellStyle, Sheet sheet) {
        Row row = sheet.createRow(rowIdx++);
        row.createCell(1).setCellValue(
                extractInfo.getGeneratedByTitle() == null ? "" : extractInfo.getGeneratedByTitle());
        row.createCell(0).setCellValue(extractInfo.getExportBy() == null ? "" : extractInfo.getExportBy());

        row = sheet.createRow(rowIdx++);
        row.createCell(0).setCellValue(
                extractInfo.getReportDateTitle() == null ? "" : extractInfo.getReportDateTitle());
        row.createCell(1).setCellValue(setTime());

        rowIdx++;
        Row headerRow = sheet.createRow(rowIdx++);
        if (!CollectionUtils.isEmpty(extractInfo.getTableHeader())) {
            for (int col = 0; col < extractInfo.getTableHeader().size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(extractInfo.getTableHeader().get(col).getTitle());
                cell.setCellStyle(headerCellStyle);
                sheet.autoSizeColumn(col);
            }
        }
        return rowIdx++;
    }

    public static Integer createDCWriteOffTableHeader(int rowIdx, ExtractInfo extractInfo, CellStyle headerCellStyle, Sheet sheet) {

        Row headerRow = sheet.createRow(rowIdx++);
        if (!CollectionUtils.isEmpty(extractInfo.getTableHeader())) {
            for (int col = 0; col < extractInfo.getTableHeader().size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(extractInfo.getTableHeader().get(col).getTitle());
                cell.setCellStyle(headerCellStyle);
                sheet.autoSizeColumn(col);
            }
        }
        return rowIdx;
    }

    public static Integer createHeaderExcel(Workbook workbook, ExtractInfo extractInfo, Sheet sheet) {
        if (extractInfo == null)
            throw new ApiRequestException("Table header is null!..");
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
        Font headerFont = ExcelUtil.createHeaderFont(workbook, "titleData");
        Font headerFontTitle = ExcelUtil.createHeaderFont(workbook, "titleName");

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        CellStyle headerCellStyleTitle = workbook.createCellStyle();
        headerCellStyleTitle.setFont(headerFontTitle);

        Row rowTitle = sheet.createRow(0);
        Cell cellTitle = rowTitle.createCell(0);
        cellTitle.setCellValue(extractInfo.getExcelTitle());
        cellTitle.setCellStyle(headerCellStyleTitle);

        int rowIdx = 1;
        Row row = sheet.createRow(rowIdx++);
        row.createCell(1)
                .setCellValue(extractInfo.getGeneratedByTitle() == null ? "" : extractInfo.getGeneratedByTitle());
        row.createCell(0).setCellValue(extractInfo.getExportBy() == null ? "" : extractInfo.getExportBy());

        row = sheet.createRow(rowIdx++);
        row.createCell(0)
                .setCellValue(extractInfo.getReportDateTitle() == null ? "" : extractInfo.getReportDateTitle());
        row.createCell(1).setCellValue(setTime());

        rowIdx++;
        Row headerRow = sheet.createRow(rowIdx++);
        if (CollectionUtils.isEmpty(extractInfo.getTableHeader()))
            return rowIdx;

        for (int col = 0; col < extractInfo.getTableHeader().size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(extractInfo.getTableHeader().get(col).getTitle());
            cell.setCellStyle(headerCellStyle);
            sheet.autoSizeColumn(col);
        }
        return rowIdx;
    }

    public static Integer createHeaderExcelForSXSSF(Workbook workbook, ExtractInfo extractInfo, Sheet sheet, Map<Object, Object> map) {
        if (extractInfo == null)
            throw new ApiRequestException("Table header is null!..");
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
        Font headerFont = ExcelUtil.createHeaderFont(workbook, "titleData");
        Font headerFontTitle = ExcelUtil.createHeaderFont(workbook, "titleName");

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        CellStyle headerCellStyleTitle = workbook.createCellStyle();
        headerCellStyleTitle.setFont(headerFontTitle);

        Row rowTitle = sheet.createRow(0);
        Cell cellTitle = rowTitle.createCell(0);
        cellTitle.setCellValue(extractInfo.getExcelTitle());
        cellTitle.setCellStyle(headerCellStyleTitle);

        int rowIdx = 1;
        Row row = sheet.createRow(rowIdx++);
        row.createCell(1)
                .setCellValue(extractInfo.getGeneratedByTitle() == null ? "" : extractInfo.getGeneratedByTitle());
        row.createCell(0).setCellValue(extractInfo.getExportBy() == null ? "" : extractInfo.getExportBy());

        row = sheet.createRow(rowIdx++);
        row.createCell(0)
                .setCellValue(extractInfo.getReportDateTitle() == null ? "" : extractInfo.getReportDateTitle());
        row.createCell(1).setCellValue(setTime());

        rowIdx++;
        Row headerRow = sheet.createRow(rowIdx++);
        if (CollectionUtils.isEmpty(extractInfo.getTableHeader()))
            return rowIdx;
        for (int col = 0; col < extractInfo.getTableHeader().size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(extractInfo.getTableHeader().get(col).getTitle());
            cell.setCellStyle(headerCellStyle);
            map.put(col, (cell.getStringCellValue().getBytes(StandardCharsets.UTF_8).length + cell.getStringCellValue().length()) / 2 * 256);
        }
        return rowIdx;
    }

    public static Integer createHeaderExcelWithOutTitle(Workbook workbook, ExtractInfo extractInfo, Sheet sheet) {
        if (extractInfo == null)
            throw new ApiRequestException("Table header is null!..");
        Font headerFont = ExcelUtil.createHeaderFont(workbook, "titleData");
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        int rowIdx = 0;
        Row headerRow = sheet.createRow(rowIdx++);
        if (CollectionUtils.isEmpty(extractInfo.getTableHeader()))
            return rowIdx;

        for (int col = 0; col < extractInfo.getTableHeader().size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(extractInfo.getTableHeader().get(col).getTitle());
            cell.setCellStyle(headerCellStyle);
            sheet.autoSizeColumn(col, true);
        }
        return rowIdx;
    }

    public static Integer parseToInteger(String value) {
        return Integer.parseInt(value);
    }

    public static String removeLeadingZero(String code) {
        return StringUtils.stripStart(code, "0");
    }

    public static Instant convertToInstant(String strDate, String format) {

        try {
            DateTimeFormatter FMT = new DateTimeFormatterBuilder()
                    .appendPattern(format)
                    .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
                    .toFormatter()
                    .withZone(ZoneId.systemDefault());
            return FMT.parse(strDate, Instant::from);
        } catch (Exception e) {
            return Instant.ofEpochSecond(0);
        }
    }

    public static void setColumnWidth(Sheet sheet, ExtractInfo extractInfo) {
        for (int i = 0; i < extractInfo.getTableHeader().size(); i++) {
            sheet.autoSizeColumn(i);
        }
        int firstRow = 0;
        if (extractInfo.getExcelTitle() != null) {
            firstRow = 4;
        }
        int maxColumn = extractInfo.getTableHeader().size();
        for (int columnNum = 0; columnNum <= maxColumn; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row currentRow;
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(columnNum) != null) {
                    Cell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = (currentCell.getStringCellValue().getBytes(StandardCharsets.UTF_8).length + currentCell.toString().length()) / 2;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }

    public static String setTime() {
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(9));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(now.toEpochMilli());
        return time;
    }

}
