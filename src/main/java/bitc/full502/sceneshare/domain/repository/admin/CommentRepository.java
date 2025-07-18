package bitc.full502.sceneshare.domain.repository.admin;

import bitc.full502.sceneshare.domain.entity.user.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

}
