// Classes where services are mapped to their corresponding and HTTP method via @RequestMapping annotation
package com.capstone.collectify.controllers;

import com.capstone.collectify.models.Post;
import com.capstone.collectify.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// Enables cross origin request via @CrossOrigin.
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;

    // Create Post
    @RequestMapping(value = "/posts",method = RequestMethod.POST)
    public ResponseEntity<Object> createPost(@RequestBody Post post){
        postService.createPost(post);
        return new ResponseEntity<>("Post created Successfully", HttpStatus.CREATED);
    }
    //  Get all post
    @RequestMapping(value = "/posts" , method = RequestMethod.GET)
    public ResponseEntity<Object> getPost() {
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }
    // Delete a post
    @RequestMapping (value = "/posts/{postid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePosts(@PathVariable Long postid){
        return postService.deletePost(postid);
    }
    // Update a post
    @RequestMapping (value = "/posts/{postid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePost(@PathVariable Long postid, @RequestBody Post post){
        return postService.updatePost(postid,post);
    }


}