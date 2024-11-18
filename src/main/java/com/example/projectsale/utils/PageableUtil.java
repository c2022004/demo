package com.example.projectsale.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {

    public static Pageable of(int page, int size) {

        page = page < 1 ? 1 : page;
        size = size <= 0 ? 9999 : size;

        Pageable pageable = PageRequest.of(page - 1, size);
        return pageable;
    }

    public static Pageable of(int page, int size, Sort sort) {

        page = page < 1 ? 1 : page;
        size = size <= 0 ? 9999 : size;

        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return pageable;
    }
}
