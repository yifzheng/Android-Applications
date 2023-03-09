package com.example.firemessage;

public class User {
    String id, firstName, lastName, email;

    public User() {};

    public User(String id, String fName, String lName, String email) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
