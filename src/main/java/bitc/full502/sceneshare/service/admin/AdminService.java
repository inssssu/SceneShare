package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.admin.AdminEntity;

public interface AdminService {
    AdminEntity selectAdminInfo(String adminName) throws Exception;

    int isAdminInfo(String adminName, String adminPassword) throws Exception;
}
