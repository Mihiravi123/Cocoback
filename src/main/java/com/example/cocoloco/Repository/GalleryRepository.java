package com.example.cocoloco.Repository;

import com.example.cocoloco.Model.Gallery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GalleryRepository extends MongoRepository<Gallery, String> {
    Optional<Gallery> findByName(String name);
}
