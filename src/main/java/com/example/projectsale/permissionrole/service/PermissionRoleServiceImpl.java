package com.example.projectsale.permissionrole.service;

import com.example.projectsale.utils.AbsServiceUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionRoleServiceImpl extends AbsServiceUtil implements PermissionRoleService {
}
