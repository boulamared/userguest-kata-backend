package org.amine.hnms.repository;

import org.amine.hnms.domain.Impression;
import org.amine.hnms.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ImpressionRepository extends JpaRepository<Impression,Integer> {

    List<Impression> findByNotificationAndCreatedAtBetween(Notification notification, LocalDateTime startDate, LocalDateTime endDate);
}
