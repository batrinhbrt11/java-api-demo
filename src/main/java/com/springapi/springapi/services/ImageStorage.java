package com.springapi.springapi.services;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import com.springapi.springapi.IStorage;

import org.apache.commons.io.FilenameUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ImageStorage implements IStorage{
    private final Path storageFolder = Paths.get("uploads");
    public ImageStorage(){
        try {
            Files.createDirectories(storageFolder);
        } catch (Exception e) {
            throw new RuntimeException("cannot initialize storage",e);
        }
    }
    
    @Override
    public void deleteAllFiles() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Stream<Path> loadAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public byte[] readFileContent(String fileName) {
        // TODO Auto-generated method stub
        return null;
    }
    private boolean isImageFile(MultipartFile file){
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png","jpg","jpeg","bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }
    @Override
    public String storeFile(MultipartFile file) {
        try {
            if(file.isEmpty()){
                throw new RuntimeException("Failed to store");
            }
            if(!isImageFile(file)){
                throw new RuntimeException("You can only upload image file");
            }
            float fileSize = file.getSize()/1000000.0f;
            if(fileSize>5.0f){
                throw new RuntimeException("file must be <= 5Mb");
            }
            //doi ten file 
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName = generatedFileName +"." +fileExtension;
            Path destinationFilePath = this.storageFolder.resolve(Paths.get(generatedFileName)).normalize().toAbsolutePath();
            if(!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())){
                throw new RuntimeException("cannot store file ");
            }
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        } catch (Exception e) {
            throw new RuntimeException("failed to store file ");
        }
    }
    
}
