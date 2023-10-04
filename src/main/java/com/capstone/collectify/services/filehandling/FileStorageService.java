package com.capstone.collectify.services.filehandling;

import com.capstone.collectify.models.FileDB;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileStorageService {
    FileDB store(String base64Data, String fileName, String contentType) throws IOException;

    FileDB getFile(String id);

    Stream<FileDB> getAllFiles();

    FileDB getByFilename(String filename);
}
