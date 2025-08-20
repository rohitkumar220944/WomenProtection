package com.rohit.controllers;

import com.rohit.dto.SOSAlertDTO;
import com.rohit.entity.SOSAlert;
import com.rohit.entity.User;
import com.rohit.services.SOSAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sos")
public class SOSAlertController {

    @Autowired
    private SOSAlertService sosService;

    // Create new SOS
    @PostMapping("/create")
    public ResponseEntity<?> createSOS(@RequestBody SOSAlertDTO dto) {
        try {
            SOSAlert alert = sosService.createSOS(dto);
            return ResponseEntity.ok(alert);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error creating SOS");
        }
    }

    // Update SOS (location/status)
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSOS(@PathVariable Long id, @RequestBody SOSAlertDTO dto) {
        try {
            SOSAlert alert = sosService.updateSOS(id, dto);
            if (alert != null) return ResponseEntity.ok(alert);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error updating SOS");
        }
    }

    // End SOS
    @PostMapping("/end/{id}")
    public ResponseEntity<?> endSOS(@PathVariable Long id) {
        try {
            SOSAlert alert = sosService.endSOS(id);
            if (alert != null) return ResponseEntity.ok(alert);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error ending SOS");
        }
    }

    // Get user SOS history
    @GetMapping("/history")
    public ResponseEntity<?> getUserSOSHistory() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<SOSAlert> history = sosService.getUserSOS(user);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error fetching history");
        }
    }

    // Get active SOS
    @GetMapping("/active")
    public ResponseEntity<?> getActiveSOS() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SOSAlert active = sosService.getActiveSOS(user);
            return ResponseEntity.ok(active);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error fetching active SOS");
        }
    }
}
