package com.example.projectsale.permissionaction.service;

import com.example.projectsale.utils.AbsServiceUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionActionServiceImpl extends AbsServiceUtil implements PermissionActionService {
}
