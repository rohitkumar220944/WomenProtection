package com.rohit.controllers;

import com.rohit.dto.LocationDTO;
import com.rohit.entity.Location;
import com.rohit.entity.User;
import com.rohit.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // POST /location/update
    @PostMapping("/update")
    public ResponseEntity<?> updateLocation(@RequestBody LocationDTO dto) {
        Location saved = locationService.saveLocation(dto);
        return saved != null ? ResponseEntity.ok(saved) : ResponseEntity.badRequest().body("Error saving location");
    }

    // GET /location/history
    @GetMapping("/history")
    public ResponseEntity<?> getLocationHistory() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Location> history = locationService.getLocationHistory(user);
        return history != null ? ResponseEntity.ok(history) : ResponseEntity.badRequest().body("Error fetching history");
    }

    // GET /location/last
    @GetMapping("/last")
    public ResponseEntity<?> getLastLocation() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Location last = locationService.getLastLocation(user);
        return last != null ? ResponseEntity.ok(last) : ResponseEntity.badRequest().body("No location found");
    }
}
