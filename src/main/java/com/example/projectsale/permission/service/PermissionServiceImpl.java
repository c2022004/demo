package com.example.projectsale.permission.service;

import com.example.projectsale.utils.AbsServiceUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionServiceImpl extends AbsServiceUtil implements PermissionService {
}
