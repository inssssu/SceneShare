package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.admin.NoticeEntity;

import java.util.List;

public interface AdminNoticeService {

    void saveNotice(NoticeEntity notice);

    NoticeEntity selectNoticeDetail(int noticeId);

    List<NoticeEntity> selectNoticeList();

    List<NoticeEntity> searchByTitleOrContents(String keyword);
}
