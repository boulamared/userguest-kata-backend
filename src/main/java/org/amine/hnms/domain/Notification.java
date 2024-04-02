package org.amine.hnms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JsonIgnore
    private Hotel hotel;
    private String message;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Impression> impressions;
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Click> clicks;
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Conversion> conversions;

}
