package kubertest.master.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "essence",schema = "yandex_master")
@NoArgsConstructor
@AllArgsConstructor
public class Essence {

    @Id
    @Column(name="id")
    private UUID id;

    @Column(name = "some_date")
    private LocalDate someDate;

    @Column(name = "some_time")
    private LocalTime someTime;

    @Column(name = "title")
    private String title;

    @Column(name = "email")
    private String email;

}
