package ite_istad_product.product_demo.service.impl;


import ite_istad_product.product_demo.dto.file.FileResponse;
import ite_istad_product.product_demo.entity.FileUpload;
import ite_istad_product.product_demo.mapper.FileUploadMapper;
import ite_istad_product.product_demo.repository.FileRepository;
import ite_istad_product.product_demo.service.FileUploadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {


    private final FileRepository fileRepository;

    private final FileUploadMapper fileUploadMapper;


    @Value("${file.storage-location}")
    private String fileStorageLocation;



    @Override
    public FileResponse upload(MultipartFile file) {

        return uploadFile(file);

    }



    @Override
    public List<FileResponse> uploadMultipleFiles(
            List<MultipartFile> files
    ) {

        return files.stream()
                .map(this::uploadFile)
                .collect(Collectors.toList());

    }



    @Override
    public FileResponse findByName(String name) {


        FileUpload file =
                fileRepository.findByName(name)
                        .orElseThrow(
                                () -> new NoSuchElementException(
                                        "File " + name + " not found"
                                )
                        );


        return fileUploadMapper.mapToResponse(file);

    }




    @Override
    public Page<FileResponse> findAll(
            int pageNumber,
            int pageSize
    ) {


        Pageable pageable =
                PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by(
                                Sort.Direction.DESC,
                                "id"
                        )
                );


        return fileRepository.findAll(pageable)
                .map(fileUploadMapper::mapToResponse);

    }




    @Override
    public void deleteByName(String name) {


        FileUpload file =
                fileRepository.findByName(name)
                        .orElseThrow(
                                () -> new NoSuchElementException(
                                        "File " + name + " not found"
                                )
                        );


        // Delete Database
        fileRepository.delete(file);



        // Delete Physical File

        String filename =
                file.getName()
                        + "."
                        + file.getExtension();



        Path path =
                Paths.get(
                        fileStorageLocation,
                        filename
                );



        try {

            Files.deleteIfExists(path);

            log.info(
                    "File deleted successfully {}",
                    filename
            );


        } catch(IOException e){

            log.error(
                    "Cannot delete file {}",
                    filename
            );

            throw new RuntimeException(
                    "Cannot delete file"
            );

        }

    }





    private FileResponse uploadFile(
            MultipartFile file
    ){


        if(file.isEmpty()){
            throw new IllegalArgumentException(
                    "File cannot be empty"
            );
        }



        // 1. Generate unique name

        String name =
                UUID.randomUUID()
                        .toString();



        // 2. Get extension

        String originalFilename =
                file.getOriginalFilename();



        if(originalFilename == null){
            throw new IllegalArgumentException(
                    "Invalid filename"
            );
        }



        String extension =
                originalFilename.substring(
                        originalFilename.lastIndexOf(".")+1
                );




        String filename =
                name
                        + "."
                        + extension;




        // 3. Create Path


        Path path =
                Paths.get(
                        fileStorageLocation,
                        filename
                );
        try {
            Files.createDirectories(
                    path.getParent()
            );
            // copy file
            Files.copy(
                    file.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING
            );



        }catch(IOException e){


            throw new RuntimeException(
                    "Upload file failed"
            );

        }
        // 4. Save database
        FileUpload fileUpload = new FileUpload();

        fileUpload.setName(name);
        fileUpload.setExtension(extension);
        fileUpload.setCaption("ISTAD media service");
        fileUpload.setSize(file.getSize());
        fileUpload.setMediaType(file.getContentType()
        );
        FileUpload saved = fileRepository.save(fileUpload);
        log.info("File uploaded successfully {}", filename);
        return fileUploadMapper
                .mapToResponse(saved);
    }
}