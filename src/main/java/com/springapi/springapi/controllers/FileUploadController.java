package com.springapi.springapi.controllers;

import com.springapi.springapi.IStorage;
import com.springapi.springapi.model.ResponseObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(path="api/v1/FileUpload")
public class FileUploadController {
    @Autowired 
    private IStorage storage;
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            String generatedFileName = storage.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","upload File succes fully",generatedFileName)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("fail",e.getMessage(),"")
            );
        }
    }
   
}
