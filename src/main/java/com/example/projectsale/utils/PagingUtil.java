package com.example.projectsale.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PagingUtil {

    private int pageSize;

    private int pageNumber;
}
