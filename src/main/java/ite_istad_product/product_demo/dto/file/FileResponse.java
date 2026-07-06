package ite_istad_product.product_demo.dto.file;


import lombok.Builder;

@Builder
public record FileResponse(
        String name,
        String caption,
        String extension,
        Long size,
        String url
        ) {
}
