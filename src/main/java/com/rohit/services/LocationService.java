package com.rohit.services;

import com.rohit.dto.LocationDTO;
import com.rohit.entity.Location;
import com.rohit.entity.User;
import com.rohit.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepo;

    // Save new location
    public Location saveLocation(LocationDTO dto) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Location location = new Location(dto, user);
            return locationRepo.save(location);
        } catch (Exception e) {
            return null;
        }
    }

    // Fetch all locations of current user
    public List<Location> getLocationHistory(User user) {
        try {
            return locationRepo.findByUserId(user.getId());
        } catch (Exception e) {
            return null;
        }
    }

    // Fetch last known location
    public Location getLastLocation(User user) {
        try {
            return locationRepo.findTopByUserIdOrderByCreatedAtDesc(user.getId());
        } catch (Exception e) {
            return null;
        }
    }
}
