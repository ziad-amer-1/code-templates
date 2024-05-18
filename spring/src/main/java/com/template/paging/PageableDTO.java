package com.template.paging;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageableDTO {

    private Integer pageNumber;
    private Integer pageSize;
    private String sortDir;

    public static PageableDTO createPageableDTO(Integer pageNumber, Integer pageSize) {
        return PageableDTO
                .builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

}