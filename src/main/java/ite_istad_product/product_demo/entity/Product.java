package ite_istad_product.product_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Product {
    private int id;
    private String  name;
    private String  description;
    private double price;
    private int userId;
}
