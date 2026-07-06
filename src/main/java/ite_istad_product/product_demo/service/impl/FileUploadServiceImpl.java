package ite_istad_product.product_demo.service.impl;

import ite_istad_product.product_demo.dto.file.FileResponse;
import ite_istad_product.product_demo.repository.FileRepository;
import ite_istad_product.product_demo.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor

public class FileUploadServiceImpl implements FileUploadService {

    private final FileRepository fileRepository;

    @Override
    public FileResponse upload(MultipartFile file) {
        return null;
    }

    @Override
    public FileResponse FindByName(String name) {
        return null;
    }

    @Override
    public Page<FileResponse> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public void deleteByName(String name) {

    }
}
