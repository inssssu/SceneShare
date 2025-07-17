package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.admin.AdminEntity;

public interface AdminService {

    // 관리자 로그인
    boolean login(String adminName, String adminPassword);
}
