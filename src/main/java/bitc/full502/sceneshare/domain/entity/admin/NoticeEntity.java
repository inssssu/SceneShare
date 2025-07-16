package bitc.full502.sceneshare.domain.entity.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notice_board")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeId;
    private String title;
    private String contents;
    private String type;

}

//
//class ResponseDTO {
//    private int seq;
//    private List<NoticeEntity>  noticeList;
//    private List<MovieEntity>  movieList;
//    private String type;
//}