package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.user.UserEntity;
import bitc.full502.sceneshare.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;

    // 사용자 리스트
    @Override
    public List<UserEntity> selectUserAccountList() {
        return userRepository.findAllByOrderByUserIdxDesc();
    }

    // 사용자 상세
    @Override
    public UserEntity selectUserAccountDetail(int userIdx) {

        UserEntity user = userRepository.findByUserIdx(userIdx);

        return user;
    }

    // 사용자 검색
    @Override
    public List<UserEntity> searchUserByKeyword(String keyword) {
        return userRepository.findByUsernameContainingOrNameContaining(keyword, keyword);
    }
}
