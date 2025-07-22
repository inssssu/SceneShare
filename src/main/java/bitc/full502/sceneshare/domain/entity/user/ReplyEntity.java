package bitc.full502.sceneshare.domain.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyId;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private CommentEntity reply;

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    private UserEntity user;
}
