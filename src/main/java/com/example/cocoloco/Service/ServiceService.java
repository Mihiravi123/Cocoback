package com.example.cocoloco.Service;


import com.example.cocoloco.Model.Service;
import com.example.cocoloco.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    // Get all services
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    // Get a specific service by ID
    public Optional<Service> getServiceById(String id) {
        return serviceRepository.findById(id);
    }

    // Add a new service
    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    // Update an existing service
    public Service updateService(String id, Service newServiceData) {
        return serviceRepository.findById(id).map(service -> {
            service.setHeading(newServiceData.getHeading());
            service.setDescription(newServiceData.getDescription());
            service.setDescription2(newServiceData.getDescription2());
            service.setDescription3(newServiceData.getDescription3());
            service.setImages(newServiceData.getImages());
            return serviceRepository.save(service);
        }).orElseGet(() -> {
            newServiceData.setId(id);
            return serviceRepository.save(newServiceData);
        });
    }

    // Delete a service by ID
    public void deleteService(String id) {
        serviceRepository.deleteById(id);
    }
}
