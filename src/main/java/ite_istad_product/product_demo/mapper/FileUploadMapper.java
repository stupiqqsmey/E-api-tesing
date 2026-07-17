package ite_istad_product.product_demo.mapper;

import ite_istad_product.product_demo.dto.file.FileResponse;
import ite_istad_product.product_demo.entity.FileUpload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public abstract class FileUploadMapper {

    @Value("${file.base-url}")
    protected String baseUrl;

    @Mapping(target = "url", expression = "java(generateUrl(fileUpload))")
    public abstract FileResponse mapToResponse(FileUpload fileUpload);

    protected String generateUrl(FileUpload fileUpload) {
        return baseUrl
                + fileUpload.getName()
                + "."
                + fileUpload.getExtension();
    }
}