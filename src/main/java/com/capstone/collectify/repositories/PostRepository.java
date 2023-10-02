package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Post is the data type of the data used in methods
// Object is the data type of the data returned from the database.
// An interface marked as @Repository contains methods for database manipulation

@Repository
public interface PostRepository extends CrudRepository<Post,Object>{

}
