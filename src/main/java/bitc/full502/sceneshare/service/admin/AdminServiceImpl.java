package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.admin.AdminEntity;
import bitc.full502.sceneshare.domain.repository.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public AdminEntity selectAdminInfo(String adminName) throws Exception {


        return adminRepository.findByAdminName(adminName);
    }

    @Override
    public int isAdminInfo(String adminName, String adminPassword) throws Exception {
        return adminRepository.findByAdminNameAndAdminPassword(adminName, adminPassword);
    }
}
