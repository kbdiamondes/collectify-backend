// Service is an interface that exposes the methods of an implementation whose details have been abstracted away.

package com.capstone.collectify.services;

import com.capstone.collectify.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {
    // Creating a post
    void createPost(Post post);
    // viewing all post
    Iterable<Post> getPosts();
    // Delete a post
    ResponseEntity deletePost(Long postid);
    // Update a post
    ResponseEntity updatePost(Long id, Post post);
}
