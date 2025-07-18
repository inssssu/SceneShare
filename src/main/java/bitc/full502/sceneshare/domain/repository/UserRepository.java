package bitc.full502.sceneshare.domain.repository;

import bitc.full502.sceneshare.domain.entity.user.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<UserEntity, Integer> {

    UserEntity findByUserIdx(int userIdx);

    List<UserEntity> findAllByOrderByUserIdxDesc();

    List<UserEntity> findByUsernameContainingOrNameContaining(String username, String name);
}
