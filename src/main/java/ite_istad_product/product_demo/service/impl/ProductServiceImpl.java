package ite_istad_product.product_demo.service.impl;

import ite_istad_product.product_demo.dto.product.ProductFilter;
import ite_istad_product.product_demo.dto.product.ProductRequest;
import ite_istad_product.product_demo.dto.product.ProductResponse;
import ite_istad_product.product_demo.dto.product.UpdateProductRequest;
import ite_istad_product.product_demo.entity.Product;
import ite_istad_product.product_demo.entity.ProductSpecification;
import ite_istad_product.product_demo.entity.Tag;
import ite_istad_product.product_demo.mapper.ProductMapper;
import ite_istad_product.product_demo.repository.CategoryRepository;
import ite_istad_product.product_demo.repository.ProductRepository;
import ite_istad_product.product_demo.repository.TagRepository;
import ite_istad_product.product_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        Product product = productMapper.toEntity(request);

        var category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Category with id " + request.categoryId() + " not found."
                        ));

        product.setCategory(category);

        if (request.tagsIds() != null && !request.tagsIds().isEmpty()) {

            Set<Tag> tags = request.tagsIds()
                    .stream()
                    .map(tagId ->
                            tagRepository.findById(tagId)
                                    .orElseThrow(() ->
                                            new NoSuchElementException(
                                                    "Tag with id " + tagId + " not found."
                                            )))
                    .collect(Collectors.toSet());

            product.setTags(tags);
        }

        Product savedProduct = productRepository.save(product);

        log.info("Product created successfully. ID={}", savedProduct.getId());

        return productMapper.toProductResponse(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> findAllProducts(Pageable pageable,
                                                 ProductFilter filter) {

        Specification<Product> specification =
                ProductSpecification.filterProduct(filter);

        return productRepository.findAll(specification, pageable)
                .map(productMapper::toProductResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findProductById(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Product with id {} not found.", id);
                    return new NoSuchElementException(
                            "Product with id " + id + " not found."
                    );
                });

        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id,
                                         UpdateProductRequest request) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Product with id {} not found.", id);
                    return new NoSuchElementException(
                            "Product with id " + id + " not found."
                    );
                });

        if (request.name() != null) {
            existingProduct.setName(request.name());
        }

        if (request.description() != null) {
            existingProduct.setDescription(request.description());
        }

        if (request.price() != null) {
            existingProduct.setPrice(
                    BigDecimal.valueOf(request.price())
            );
        }

        Product updatedProduct = productRepository.save(existingProduct);

        log.info("Product updated successfully. ID={}", updatedProduct.getId());

        return productMapper.toProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Product with id {} not found.", id);
                    return new NoSuchElementException(
                            "Product with id " + id + " not found."
                    );
                });

        productRepository.delete(product);

        log.info("Product deleted successfully. ID={}", id);
    }

}