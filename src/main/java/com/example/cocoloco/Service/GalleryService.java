package com.example.cocoloco.Service;

import com.example.cocoloco.Model.Gallery;
import com.example.cocoloco.Model.Gallery.Item;
import com.example.cocoloco.Repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    public Optional<Gallery> getGalleryByName(String name) {
        return galleryRepository.findByName(name); // Assuming you have a method findByName in your repository
    }

    // Get all galleries
    public List<Gallery> getAllGalleries() {
        return galleryRepository.findAll();
    }

    // Get a single gallery by ID
    public Optional<Gallery> getGalleryById(String id) {
        return galleryRepository.findById(id);
    }

    // Add a new gallery
    public Gallery addGallery(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    // Update an existing gallery
    public Gallery updateGallery(String id, Gallery gallery) {
        if (galleryRepository.existsById(id)) {
            gallery.setId(id);
            return galleryRepository.save(gallery);
        }
        return null;
    }

    // Delete a gallery by ID
    public void deleteGallery(String id) {
        galleryRepository.deleteById(id);
    }

    // Add an item to a gallery
    public Gallery addItemToGallery(String galleryId, Item item) {
        Optional<Gallery> galleryOpt = galleryRepository.findById(galleryId);
        if (galleryOpt.isPresent()) {
            Gallery gallery = galleryOpt.get();
            gallery.getImages().add(item);
            return galleryRepository.save(gallery);
        }
        return null;
    }

    // Update an item in a gallery
    public Gallery updateItemInGallery(String galleryId, String itemId, Item newItem) {
        Optional<Gallery> galleryOpt = galleryRepository.findById(galleryId);
        if (galleryOpt.isPresent()) {
            Gallery gallery = galleryOpt.get();
            List<Item> items = gallery.getImages();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getId().equals(itemId)) {
                    items.set(i, newItem);
                    return galleryRepository.save(gallery);
                }
            }
        }
        return null;
    }

    // Delete an item from a gallery
    public Gallery deleteItemFromGallery(String galleryId, String itemId) {
        Optional<Gallery> galleryOpt = galleryRepository.findById(galleryId);
        if (galleryOpt.isPresent()) {
            Gallery gallery = galleryOpt.get();
            List<Item> items = gallery.getImages();
            items.removeIf(item -> item.getId().equals(itemId));
            return galleryRepository.save(gallery);
        }
        return null;
    }

    public Gallery addItemsToGallery(String galleryId, List<Item> items) {
        Optional<Gallery> galleryOpt = galleryRepository.findById(galleryId);
        if (galleryOpt.isPresent()) {
            Gallery gallery = galleryOpt.get();
            gallery.getImages().addAll(items);
            return galleryRepository.save(gallery);
        }
        return null;
    }
}
