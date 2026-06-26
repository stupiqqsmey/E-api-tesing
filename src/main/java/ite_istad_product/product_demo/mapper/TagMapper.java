package ite_istad_product.product_demo.mapper;


import ite_istad_product.product_demo.dto.tag.TagRequest;
import ite_istad_product.product_demo.dto.tag.TagResponse;
import ite_istad_product.product_demo.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponse toResponse(Tag tag);
    Tag toEntity(TagRequest tagRequest);
}
