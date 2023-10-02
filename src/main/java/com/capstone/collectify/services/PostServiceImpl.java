// contains the business logic concerned with a particular object in the class
package com.capstone.collectify.services;


import com.capstone.collectify.models.Post;
import com.capstone.collectify.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// Will allow us to use the CRUD repository method inherited from the CRUDRepository
@Service
public class PostServiceImpl implements PostService{
    // An object cannot be instantiated from interfaces.
    // @Autowired allows us to use the interface as if it was an instance of an object and allows us to use the methods from the CRUDRespository
    @Autowired
    private PostRepository postRepository;
    // Create post
    public void createPost(Post post){
        postRepository.save(post);
    }
    // Get all post
    public Iterable<Post> getPosts(){
        return postRepository.findAll();
    }

    // Delete post
    public ResponseEntity deletePost(Long id){
       postRepository.deleteById(id);
       return new ResponseEntity<>("Post Deleted successfully", HttpStatus.OK);
    }

    // Update a post
    public ResponseEntity updatePost(Long id, Post post){
        // Find the post to update
        Post postForUpdate = postRepository.findById(id).get();
        // Updating the tittle and content
        postForUpdate.setTitle(post.getTitle());
        postForUpdate.setContent(post.getContent());
        // Saving and Updating a post
        postRepository.save(postForUpdate);
        return new ResponseEntity<>("Post updated Successfully",HttpStatus.OK);
    }
}
