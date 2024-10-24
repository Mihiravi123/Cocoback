package com.example.cocoloco.Controller;

import com.example.cocoloco.Model.Facility;
import com.example.cocoloco.Service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://cocoadminpanel.onrender.com")
@RestController
@RequestMapping("/api/facility")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public List<Facility> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable String id) {
        return facilityService.getFacilityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Facility addFacility(@RequestBody Facility facility) {
        return facilityService.addFacility(facility);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facility> updateFacility(@PathVariable String id, @RequestBody Facility facilityDetails) {
        try {
            return ResponseEntity.ok(facilityService.updateFacility(id, facilityDetails));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Handle invalid ObjectId format
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFacility(@PathVariable String id) {
        try {
            facilityService.deleteFacility(id);
            return ResponseEntity.ok().body("Facility deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ObjectId format");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting facility: " + e.getMessage());
        }
    }
}

