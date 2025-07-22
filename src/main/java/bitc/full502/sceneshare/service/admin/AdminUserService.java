package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.user.UserEntity;

import java.util.List;

public interface AdminUserService {
    List<UserEntity> selectUserAccountList();

    UserEntity selectUserAccountDetail(int userIdx);

    List<UserEntity> searchUserByKeyword(String keyword);
}
