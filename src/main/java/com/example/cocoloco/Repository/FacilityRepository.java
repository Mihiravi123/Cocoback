package com.example.cocoloco.Repository;

import com.example.cocoloco.Model.Facility;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacilityRepository extends MongoRepository<Facility, String> {
}