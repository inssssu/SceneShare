package bitc.full502.sceneshare.domain.repository.admin;

import bitc.full502.sceneshare.domain.entity.admin.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer>{
    Optional<AdminEntity> findByAdminNameAndAdminPassword(String adminName, String adminPassword);
    // 이후에 유저 엔터티로 변경
}
