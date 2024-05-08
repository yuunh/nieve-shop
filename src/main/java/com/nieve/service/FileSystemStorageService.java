package com.nieve.service;

import com.nieve.common.StorageException;
import com.nieve.common.StorageFileNotFoundException;
import com.nieve.config.storage.StorageProperties;
import com.nieve.entity.FileEntity;
import com.nieve.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

  private final Path rootLocation;
  @Autowired
  FileRepository fileRepository;

  @Autowired
  public FileSystemStorageService(StorageProperties properties) {

    if(properties.getLocation().trim().length() == 0){
      throw new StorageException("File upload location can not be Empty.");
    }

    this.rootLocation = Paths.get(properties.getLocation());
  }

  @Override
  public int store(MultipartFile file) {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file.");
      }
      String originalFileName = file.getOriginalFilename();
      String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
      String newFileName = UUID.randomUUID() + ext;

      Path destinationFile = this.rootLocation.resolve(
              Paths.get(newFileName))
          .normalize().toAbsolutePath();
      if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
        // This is a security check
        throw new StorageException(
            "Cannot store file outside current directory.");
      }
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile,
            StandardCopyOption.REPLACE_EXISTING);
      }
      FileEntity e = fileRepository.save(FileEntity.builder()
              .changeName(newFileName)
              .filePath(this.rootLocation.resolve(
                              Paths.get(newFileName))
                      .normalize().toString())
              .status("Y")
              .originName(file.getOriginalFilename())
              .uploadDate(LocalDateTime.now())
              .build());
      return e.getFileNo();
    }
    catch (IOException e) {
      throw new StorageException("Failed to store file.", e);
    }
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.rootLocation, 1)
          .filter(path -> !path.equals(this.rootLocation))
          .map(this.rootLocation::relativize);
    }
    catch (IOException e) {
      throw new StorageException("Failed to read stored files", e);
    }

  }

  @Override
  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      }
      else {
        throw new StorageFileNotFoundException(
            "Could not read file: " + filename);

      }
    }
    catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

  @Override
  public void init() {
    try {
      Files.createDirectories(rootLocation);
    }
    catch (IOException e) {
      throw new StorageException("Could not initialize storage", e);
    }
  }
}
