package ite_istad_product.product_demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name ="product_tbl")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  name;
    private String  description;

    /// for currency
    private BigDecimal price;
    private Boolean isavailable=true;
    private Boolean isDeleted=false; /// for soft delete
    private String slug; /// for seo purpose
    private String thumbnail;
    private Integer quantity;


    @ManyToMany
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne (fetch = FetchType.LAZY)  /// many to one is eiger
    @JoinColumn(name = "category_id")
    private Category category;
}

