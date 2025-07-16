package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.UserEntity;
import bitc.full502.sceneshare.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository userRepository;

    @Override
    public int isUserInfo(String userId, String userPw) throws Exception {
        return userRepository.countByUserIdAndUserPw(userId, userPw);
    }

    @Override
    public UserEntity selectUserInfo(String userId) throws Exception {
        return userRepository.findByUserId(userId);
    }

    @Override
    public int selectOnlyUsers(String userId) throws Exception {
        return userRepository.countByUserIdContaining(userId);
    }
}
