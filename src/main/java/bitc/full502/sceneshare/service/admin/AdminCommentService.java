package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.user.CommentEntity;

import java.util.List;

public interface AdminCommentService {

    List<CommentEntity> selectCommentList();

    CommentEntity selectCommentDetail(int userIdx);
}
