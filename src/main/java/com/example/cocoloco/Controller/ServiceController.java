package com.example.cocoloco.Controller;


import com.example.cocoloco.Model.Service;
import com.example.cocoloco.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    // Get all services
    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    // Get service by ID (e.g., '/service/{id}')
    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable String id) {
        Optional<Service> service = serviceService.getServiceById(id);
        return service.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new service (POST request to '/service')
    @PostMapping
    public Service addService(@RequestBody Service service) {
        return serviceService.addService(service);
    }


    // Update an existing service by ID (PUT request to '/service/{id}')
    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable String id, @RequestBody Service service) {
        Optional<Service> existingService = serviceService.getServiceById(id);
        if (existingService.isPresent()) {
            return ResponseEntity.ok(serviceService.updateService(id, service));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a service by ID (DELETE request to '/service/{id}')
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable String id) {
        Optional<Service> existingService = serviceService.getServiceById(id);
        if (existingService.isPresent()) {
            serviceService.deleteService(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

