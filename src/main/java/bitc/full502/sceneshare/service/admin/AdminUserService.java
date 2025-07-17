package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.user.UserAccountEntity;

import java.util.List;

public interface AdminUserService {
    List<UserAccountEntity> selectUserAccountList();

    UserAccountEntity selectUserAccountDetail(int userIdx);

    List<UserAccountEntity> searchUserByKeyword(String keyword);
}
