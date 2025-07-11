package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.UserEntity;
import bitc.full502.sceneshare.domain.repository.user.UserRepository;

public interface UserLoginService {

    int isUserInfo(String userId, String userPw) throws Exception;

    UserEntity selectUserInfo(String userId) throws Exception;
}
