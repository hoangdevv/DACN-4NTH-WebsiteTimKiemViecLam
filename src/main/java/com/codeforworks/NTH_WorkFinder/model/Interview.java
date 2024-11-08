package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interview")
public class Interview extends Base{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idInterview;

    @ManyToOne
    @JoinColumn(name = "id_application", nullable = false)
    private Application application;

    private Date interviewDate;

    private String location;

    @Enumerated(EnumType.STRING)
    private InterviewStatus status;

    private String feedback; // Đánh giá sau phỏng vấn

    public enum InterviewStatus {
        SCHEDULED,  //đã lên lịch
        COMPLETED,  //đã hoàn thành
        CANCELLED   //đã hủy
    }
}
