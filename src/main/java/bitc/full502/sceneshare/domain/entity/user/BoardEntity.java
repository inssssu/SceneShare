package bitc.full502.sceneshare.domain.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private String movie;

    @Column(nullable = false)
    private String title;

    @Column
    private String contents;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateDate;

    @Column(nullable = false)
    private String genre;

    @Column
    private String reply;
}
