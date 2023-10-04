package com.capstone.collectify.repositories;



import com.capstone.collectify.models.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    @Query("SELECT f FROM FileDB f WHERE f.name = :filename")
    FileDB findByFilename(@Param("filename") String filename);
}