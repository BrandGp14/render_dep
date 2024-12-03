package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.CourseRateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_course_rate")
public class TCourseRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "n_point", nullable = false, length = 4)
    private double points = 0d; // Cambiado a minúscula para seguir la convención

    @Column(name = "t_attempt", nullable = false)
    private int attempt = 0; // Cambiado a minúscula para seguir la convención

    @Column(name = "d_realization", nullable = false)
    private Instant dateRealization = Instant.now();

    @ManyToOne
    @JoinColumn(name = "id_evaluation", nullable = false)
    private TEvaluationEntity evaluation;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private TUserEntity user;

    public TCourseRateEntity(CourseRateDto courseRateDto, TEvaluationEntity evaluation, TUserEntity user) {
        this.points = courseRateDto.getPoints();
        this.attempt = courseRateDto.getAttempt();
        this.dateRealization = Instant.parse(courseRateDto.getDateRealization());
        this.evaluation = evaluation;
        this.user = user;
    }

    public CourseRateDto toDto() {
        CourseRateDto courseRateDto = new CourseRateDto();
        courseRateDto.setId(this.id);
        courseRateDto.setPoints(this.points);
        courseRateDto.setAttempt(this.attempt);
        courseRateDto.setDateRealization(this.dateRealization.toString());
        courseRateDto.setEvaluation(this.evaluation.toDto());
        courseRateDto.setUser(this.user.toDto());
        return courseRateDto;
    }

    public void update(CourseRateDto courseRateDto) {
        this.points = courseRateDto.getPoints();
        this.attempt = courseRateDto.getAttempt();
        this.dateRealization = Instant.parse(courseRateDto.getDateRealization());
    }
}
