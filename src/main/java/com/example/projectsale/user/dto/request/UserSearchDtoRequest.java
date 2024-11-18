package com.example.projectsale.user.dto.request;

import com.example.projectsale.utils.PagingUtil;
import com.example.projectsale.excel.dto.ExtractInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchDtoRequest extends PagingUtil {

    private String key;

    private String role;

    private ExtractInfo extractInfo;

}
