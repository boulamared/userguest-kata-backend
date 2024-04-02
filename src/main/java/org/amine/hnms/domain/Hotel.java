package org.amine.hnms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "index_hotel_key", columnList = "\"key\""),
        @Index(name = "index_hotel_name", columnList = "name")})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true,name = "\"key\"")
    private String key;

    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private List<Notification> notifications;
}
