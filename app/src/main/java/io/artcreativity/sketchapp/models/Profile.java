package io.artcreativity.sketchapp.models;

import java.io.Serializable;

public class Profile implements Serializable {

    public static final String TABLE = "profiles";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS profiles (" +
            "id Integer PRIMARY KEY AUTOINCREMENT," +
            "first_name TEXT NOT NULL," +
            "last_name TEXT NOT NULL," +
            "image TEXT)";
    public String firstName;
    public String lastName;
    public String image;
    public Long id;

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
