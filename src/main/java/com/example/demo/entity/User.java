package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class User {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private int age;
    private String biography;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name = "userimage_id", referencedColumnName = "id")
    private UserImage userImage;

    public User(String username, int age, String biography, UserImage userImage) {
        this.username = username;
        this.age = age;
        this.biography = biography;
    }

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public UserImage getUserImage() {
        return userImage;
    }

    public void setUserImage(UserImage userImage) {
        this.userImage = userImage;
    }
}
