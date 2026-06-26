package ite_istad_product.product_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class ProductDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDemoApplication.class, args);
	}

}
