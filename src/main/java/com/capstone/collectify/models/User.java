package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;

// Marks this java object as a representation of an entity/record from the database table "post"

@Entity
@Table(name = "users")
public class User {

    // Properties
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    // Establishes the relationship of the property to the "user" model;
    @OneToMany(mappedBy = "user")
    // Prevents infinite recursion with bidirectional relationship/\.
    @JsonIgnore
    // Collection that contains no duplicate elements.
    private Set<Post> posts;
    // Constructors
    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Post> getPosts(){
        return posts;
    }
    public void setPosts(Set<Post> posts){
        this.posts=posts;
    }


}
