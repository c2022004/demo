package com.example.projectsale.excel.service;

import com.example.projectsale.user.dto.request.UserSearchDtoRequest;

import java.io.ByteArrayInputStream;

public interface ExportUserService {

    ByteArrayInputStream exportUsers(UserSearchDtoRequest request);

}
