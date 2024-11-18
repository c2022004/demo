package com.example.projectsale.excel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExtractInfo {

    private String exportBy;
    private String generatedByTitle;
    private String reportDate;
    private String reportDateTitle;
    private String excelTitle;

    private List<TableHeader> tableHeader;

}
