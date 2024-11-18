package com.example.projectsale.role.service;

import com.example.projectsale.role.repo.RoleRepo;
import com.example.projectsale.utils.AbsServiceUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl extends AbsServiceUtil implements RoleService {

    private final RoleRepo roleRepo;

}
