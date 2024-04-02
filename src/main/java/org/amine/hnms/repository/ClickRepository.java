package org.amine.hnms.repository;

import org.amine.hnms.domain.Click;
import org.amine.hnms.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClickRepository extends JpaRepository<Click,Integer> {

    List<Click> findByNotificationAndCreatedAtBetween(Notification notification, LocalDateTime startDate, LocalDateTime endDate);
}
