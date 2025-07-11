package bitc.full502.sceneshare.domain.repository.admin;

import bitc.full502.sceneshare.domain.entity.admin.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer>{
    AdminEntity findByAdminName(@Param("adminName") String adminName);

    int findByAdminNameAndAdminPassword(String adminName, String adminPassword);
}
