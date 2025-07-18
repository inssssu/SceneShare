package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.user.CommentEntity;
import bitc.full502.sceneshare.domain.repository.admin.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommentServiceImpl implements AdminCommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<CommentEntity> selectCommentList() {
        return commentRepository.findAll();
    }

    @Override
    public CommentEntity selectCommentDetail(int userIdx) {
        return commentRepository.findById(userIdx).get();
    }
}
