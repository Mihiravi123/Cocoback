package com.example.cocoloco.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "gallery")
public class Gallery {
    @Id
    private String id;
    private String name;
    private List<Item> images;

    public Gallery(String id, String name, List<Item> images) {
        this.id = id;
        this.name = name;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getImages() {
        return images;
    }

    public void setImages(List<Item> images) {
        this.images = images;
    }

    public static class Item {
        private String id;
        private String imageData;

        public Item(String id, String imageData) {
            this.id = id;
            this.imageData = imageData;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageData() {
            return imageData;
        }

        public void setImageData(String imageData) {
            this.imageData = imageData;
        }
    }
}
