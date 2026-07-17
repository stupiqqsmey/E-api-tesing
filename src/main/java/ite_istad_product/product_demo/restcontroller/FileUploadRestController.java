package ite_istad_product.product_demo.restcontroller;


import ite_istad_product.product_demo.dto.file.FileResponse;

import ite_istad_product.product_demo.service.FileUploadService;


import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;


import org.springframework.web.multipart.MultipartFile;


import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileUploadRestController {
    private final FileUploadService service;

    @PostMapping(
            consumes =
                    MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public FileResponse upload(
            @RequestPart MultipartFile file
    ){
        return service.upload(file);
    }

    @PostMapping(
            value="/multiple",
            consumes =
                    MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public List<FileResponse> uploadMultiple(
            @RequestPart List<MultipartFile> files
    ){
        return service.uploadMultipleFiles(files);
    }

    @GetMapping
    public Page<FileResponse> findAll(
            @RequestParam(defaultValue="0")
            int page,
            @RequestParam(defaultValue="10")
            int size
    ){return service.findAll(page,size);
    }
    @GetMapping("/{name}")
    public FileResponse findByName(
            @PathVariable String name
    ){
        return service.findByName(name);
    }

    @DeleteMapping("/{name}")
    public void delete(
            @PathVariable String name
    ){

        service.deleteByName(name);

    }

}