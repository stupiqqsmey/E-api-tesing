package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.file.FileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileResponse upload(MultipartFile file);
    FileResponse FindByName(String name);
    Page<FileResponse> findAll(int pageNumber, int pageSize);
    void deleteByName(String name);
}
