package com.template.excel;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ExcelInfoDTO<T>(String sheetName, String[] headers, List<T> items, PropertyValueExtractor<T> propertyExtractors, HttpServletResponse response) {
}
