package com.rohit.services;

import com.rohit.dto.SOSAlertDTO;
import com.rohit.entity.SOSAlert;
import com.rohit.entity.User;
import com.rohit.repositories.SOSAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class SOSAlertService {

    @Autowired
    private SOSAlertRepository sosRepo;

    // Create new SOS
    public SOSAlert createSOS(SOSAlertDTO dto) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SOSAlert alert = new SOSAlert(dto, user);
            alert.setCreatedAt(LocalDateTime.now());
            alert.setUpdatedAt(LocalDateTime.now());
            return sosRepo.save(alert);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update SOS (location or status)
    public SOSAlert updateSOS(Long sosId, SOSAlertDTO dto) {
        try {
            SOSAlert existing = sosRepo.findById(sosId).orElse(null);
            if (existing != null) {
                existing.updateFromDTO(dto);
                existing.setUpdatedAt(LocalDateTime.now());
                return sosRepo.save(existing);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // End SOS (mark as resolved)
    public SOSAlert endSOS(Long sosId) {
        try {
            SOSAlert existing = sosRepo.findById(sosId).orElse(null);
            if (existing != null) {
                existing.setStatus("RESOLVED");
                existing.setUpdatedAt(LocalDateTime.now());
                return sosRepo.save(existing);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get user’s SOS history
    public List<SOSAlert> getUserSOS(User user) {
        try {
            return sosRepo.findByUserId(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Get active SOS
    public SOSAlert getActiveSOS(User user) {
        try {
            return sosRepo.findFirstByUserIdAndStatus(user.getId(), "ACTIVE").orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
