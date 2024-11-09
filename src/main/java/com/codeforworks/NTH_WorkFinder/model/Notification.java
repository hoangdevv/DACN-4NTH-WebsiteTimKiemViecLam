package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends Base{


    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    private String title;
    private String message;

    private Boolean isRead; // Đã đọc hay chưa

}
