package com.example.cocoloco.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "service")
public class Service {

    @Id
    private String id;  // MongoDB's ID

    private String heading;
    private String description;
    private String description2;
    private String description3;
    private List<String> images;

    public Service(String id, String heading, String description, String description2, String description3, List<String> images) {
        this.id = id;
        this.heading = heading;
        this.description = description;
        this.description2 = description2;
        this.description3 = description3;
        this.images = images;
    }
// Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

