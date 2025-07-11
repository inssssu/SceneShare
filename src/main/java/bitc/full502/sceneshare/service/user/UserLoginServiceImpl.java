package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.UserEntity;
import bitc.full502.sceneshare.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int isUserInfo(String userId, String userPw) throws Exception {
        return userRepository.countByUserIdAndUserPw(userId, userPw);
    }

    @Override
    public UserEntity selectUserInfo(String userId) throws Exception {
        return userRepository.findByUserId(userId);
    }
}
