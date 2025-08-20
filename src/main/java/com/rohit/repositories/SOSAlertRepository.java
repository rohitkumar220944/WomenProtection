package com.rohit.repositories;

import com.rohit.entity.SOSAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SOSAlertRepository extends JpaRepository<SOSAlert, Long> {
    List<SOSAlert> findByUserId(Long userId);
    Optional<SOSAlert> findFirstByUserIdAndStatus(Long userId, String status);
}
