package bitc.full502.sceneshare.domain.entity.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminIdx;
    private String adminName;
    private String adminPassword;

    // role - admin 추가 필요

}
