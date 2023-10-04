package com.capstone.collectify.controllers.filehandling;

import java.util.List;
import java.util.stream.Collectors;

import com.capstone.collectify.messages.ResponseFile;
import com.capstone.collectify.messages.ResponseMessage;
import com.capstone.collectify.models.FileDB;
import com.capstone.collectify.services.filehandling.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@CrossOrigin
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("base64Image") String base64Image, @RequestParam("fileName") String fileName, @RequestParam("contentType") String contentType) {
        String message = "";
        try {
            storageService.store(base64Image, fileName, contentType);
            message = "Uploaded the file successfully: " + fileName;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + fileName + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDB.getType()))
                .body(fileDB.getData());
        /*return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());*/
    }

    // New method to get a file by filename
    @GetMapping("/filename/{filename}")
    public ResponseEntity<FileDB> getFileByFilename(@PathVariable String filename) {
        FileDB file = storageService.getByFilename(filename);

        if (file != null) {
            return ResponseEntity.ok()
                    .body(file);
        } else {
            // Handle the case where the file does not exist
            return ResponseEntity.notFound().build();
        }
    }



}

