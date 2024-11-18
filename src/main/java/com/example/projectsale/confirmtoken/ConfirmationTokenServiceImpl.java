package com.example.projectsale.confirmtoken;

import com.example.projectsale.exception.ApiRequestException;
import com.example.projectsale.utils.AbsServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl extends AbsServiceUtil implements ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepo;

    private final ConfirmationTokenDTOMapper confirmationTokenDTOMapper;

    @Override
    public ConfirmationTokenDTO findByToken(String token) {
        return confirmationTokenRepo.findConfirmationTokenByToken(token)
                .map(confirmationTokenDTOMapper)
                .orElseThrow(() -> new ApiRequestException("CFT_001", getMessage("CFT_001")));
    }

    @Override
    public void setConfirmAt(String token) {
        confirmationTokenRepo.updateConfirmAtByToken(token, LocalDateTime.now());
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepo.save(token);
    }
}
