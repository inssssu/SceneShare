package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.user.UserAccountEntity;
import bitc.full502.sceneshare.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserAccountEntity> selectUserAccountList() {
        return userRepository.findAll();
    }
}
