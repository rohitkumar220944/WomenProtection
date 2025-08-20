package com.rohit.repositories;

import com.rohit.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByUserId(Long userId);
    Location findTopByUserIdOrderByCreatedAtDesc(Long userId);  // last location
}
