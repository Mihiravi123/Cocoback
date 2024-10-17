package com.example.cocoloco.Repository;

import com.example.cocoloco.Model.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends MongoRepository<Service, String> {
    // Add custom query methods if needed, otherwise basic CRUD methods are provided by default
}
