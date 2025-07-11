package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.admin.AdminEntity;

public interface AdminService {
    boolean login(String adminName, String adminPassword);
}
