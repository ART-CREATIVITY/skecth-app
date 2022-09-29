package io.artcreativity.sketchapp.models;

import java.io.Serializable;

public class Profile implements Serializable {

    public String firstName;
    public String lastName;
    public String image;

    public Profile() {
    }

    public Profile(String firstName, String lastName, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    public String fullName() {
        return lastName + " " + firstName;
    }
}
