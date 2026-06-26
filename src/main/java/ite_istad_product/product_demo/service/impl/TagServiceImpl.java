package ite_istad_product.product_demo.service.impl;


import ite_istad_product.product_demo.dto.tag.TagRequest;
import ite_istad_product.product_demo.dto.tag.TagResponse;
import ite_istad_product.product_demo.entity.Tag;
import ite_istad_product.product_demo.mapper.TagMapper;
import ite_istad_product.product_demo.repository.TagRepository;
import ite_istad_product.product_demo.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TagServiceImpl implements TagService {
    private final TagMapper tagMapper;
    private final TagRepository tagRepository;

    @Override
    public TagResponse createTag(TagRequest tagRequest){
        Tag tag  = tagMapper.toEntity(tagRequest);
        return tagMapper.toResponse(tagRepository.save(tag));
    }

    @Override
    public Page<TagResponse> getAllTags(Pageable pageable) {
        return tagRepository
                .findAll(pageable)
                .map(tagMapper::toResponse);
    }

}
