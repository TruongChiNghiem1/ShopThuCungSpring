package iuh.fit.se.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iuh.fit.se.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
