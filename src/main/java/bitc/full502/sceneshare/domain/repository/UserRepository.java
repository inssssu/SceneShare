package bitc.full502.sceneshare.domain.repository;

import bitc.full502.sceneshare.domain.entity.user.UserAccountEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<UserAccountEntity, Integer> {


    List<UserAccountEntity> findAll();


}
