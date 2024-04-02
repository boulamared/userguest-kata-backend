package org.amine.hnms.repository;

import org.amine.hnms.domain.Hotel;
import org.amine.hnms.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {

    Optional<List<Notification>> findAllByHotel(Hotel hotel);
}
