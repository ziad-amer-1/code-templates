package com.template.paging;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CreatePageable {

    public static Pageable createPageable(int pageNumber, int pageSize) {
        return PageRequest.of(pageNumber, pageSize);
    }

    public static Pageable createPageable(int pageNumber, int pageSize, String sortDir) {
        if (sortDir == null) {
            sortDir = "ASC";
        }

        if (!is_ASC_or_DESC(sortDir)) {
            throw new IllegalStateException("Sort Value should be ASC or DESC");
        }

        Sort sort = sortDir.equalsIgnoreCase("ASC") ?
                Sort.by(Sort.Direction.ASC, "name") :
                Sort.by(Sort.Direction.DESC, "name");
        return PageRequest.of(pageNumber, pageSize, sort);
    }

    public static boolean is_ASC_or_DESC(String sortDir) {
        return sortDir.equalsIgnoreCase("ASC") || sortDir.equalsIgnoreCase("DESC");
    }
}

