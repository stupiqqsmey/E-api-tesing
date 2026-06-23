package ite_istad_product.product_demo.service.impl;

import ite_istad_product.product_demo.dto.ProductRequest;
import ite_istad_product.product_demo.dto.UpdateProductRequest;
import ite_istad_product.product_demo.dto.ProductResponse;
import ite_istad_product.product_demo.entity.Product;
import ite_istad_product.product_demo.mapper.ProductMapper;
import ite_istad_product.product_demo.repository.ProductRepository;
import ite_istad_product.product_demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productrequest) {
        Product product = productMapper.toEntity(productrequest);
        product.setUserId(1);
        Product savedProduct = productRepository.save(product);

        return productMapper.toProductResponse(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> findAllProducts(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;
        if (keyword != null && !keyword.trim().isEmpty()) {
            productPage = productRepository.findByNameContainingIgnoreCase(keyword, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }

        return productPage.map(productMapper::toProductResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            log.info("Product with id {} not found", id);
            return null;
        }
        return productMapper.toProductResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Integer id, UpdateProductRequest updateProductrequest) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if(existingProduct == null) {
            log.info("No product found with id " + id);
            return null;
        }

        if(updateProductrequest.name() != null)
            existingProduct.setName(updateProductrequest.name());
        if(updateProductrequest.description() != null)
            existingProduct.setDescription(updateProductrequest.description());
        if(updateProductrequest.price() != null)
            existingProduct.setPrice(updateProductrequest.price());
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toProductResponse(updatedProduct);
    }

    @Override
    @Transactional
    public Boolean deleteProduct(Integer id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}