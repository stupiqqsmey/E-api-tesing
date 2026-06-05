package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.ProductRequest;
import ite_istad_product.product_demo.dto.UpdateProductRequest;
import ite_istad_product.product_demo.dto.ProductResponse;
import ite_istad_product.product_demo.entity.Product;
import ite_istad_product.product_demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
    ///  inject product hare
    private final ProductRepository productRepository;
    private Integer nextId = 1004;

    /// map to entity
    private Product mapToProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());

        return product;
    }
    /// maptoToresponse -> convert to Entity

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    @Override
    public ProductResponse createProduct(ProductRequest productrequest) {
        var product = mapToProduct(productrequest);
        product.getUserId();
        product.setId(nextId++);
        return mapToProductResponse(product);
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.getProductslist()
                .stream()
                .map(this::mapToProductResponse)
                .toList();    }

    @Override
    public ProductResponse findProductById(Integer id) {
        var product =   productRepository.findProductById(id);
        if(product == null) {
            // throw not found exception, but skip it for now
            log.info("Product with id {} not found", id);
            return null;
        }
        return mapToProductResponse(product);    }

    @Override
    public ProductResponse updateProduct(Integer id, UpdateProductRequest updateProductrequest) {
        var existingProduct = productRepository.findProductById(id);
        if(existingProduct == null) {
            log.info("No product found with id " + id);
            // throw exception
            return null;
        }
        if(updateProductrequest.name()!=null)
            existingProduct.setName(updateProductrequest.name());
        if(updateProductrequest.description()!=null)
            existingProduct.setDescription(updateProductrequest.description());
        if(updateProductrequest.price()!=null)
            existingProduct.setPrice(updateProductrequest.price());
        // update product
        productRepository.UpdateProduct(existingProduct);
        return mapToProductResponse(existingProduct);

    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }
}
