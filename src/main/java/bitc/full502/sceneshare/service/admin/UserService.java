package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.user.UserAccountEntity;

import java.util.List;

public interface UserService {
    List<UserAccountEntity> selectUserAccountList();
}
