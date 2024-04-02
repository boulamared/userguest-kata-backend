package org.amine.hnms.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(indexes = {
        @Index(name = "index_impression_notification", columnList = "notification_id")})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Impression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notification notification;

}
