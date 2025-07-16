package bitc.full502.sceneshare.domain.repository.admin;

import bitc.full502.sceneshare.domain.entity.admin.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {

    NoticeEntity findByNoticeId(int noticeId);

    List<NoticeEntity> findAllByOrderByNoticeIdDesc();

    List<NoticeEntity> findByTitleContainingOrContentsContainingOrderByNoticeIdDesc(String title, String contents);
}
