package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.tag.TagRequest;
import ite_istad_product.product_demo.dto.tag.TagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface TagService {
    TagResponse createTag(TagRequest tagRequest);
    Page<TagResponse> getAllTags(Pageable pageable);
}
