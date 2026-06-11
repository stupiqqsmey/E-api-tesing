package ite_istad_product.product_demo.repository;


import ite_istad_product.product_demo.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepository {
    private final List<Product> productLis = new ArrayList<>();
//    {{
////        add(new Product(1010, "coca", "Nice ",5.5,1));
//        add(new Product(1011, "pepsi", "Nice sweet ",5,2));
//        add(new Product(1012, "sting", "Yinsin sweet",3.5,3));
//        add(new Product(1013, "idol", "Idon iconvident",3.5,4));
//    }};
    public List<Product> getProductslist() {
        return productLis;
    }
    public Product createProduct(Product product) {
        productLis.add(product);
        return product;
    }
///    update
    public Product findProductById(Integer id) {
        return productLis.stream()
                .filter(product -> product.getId()==id)
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("Product not found...!!"));
    }
    public boolean deleteProductById(Integer id) {
        return productLis
                .removeIf(product -> product.getId()==id);
    }
    ///  up by Id

    public Product UpdateProduct(Product updateproduct) {
        for (int i=0 ; i<productLis.size(); i++){
            var product = productLis.get(i);
            if (product.getId()==updateproduct.getId()){
                productLis.set(i,updateproduct);
                return updateproduct;
            }

        }
        return null;
    }
}

