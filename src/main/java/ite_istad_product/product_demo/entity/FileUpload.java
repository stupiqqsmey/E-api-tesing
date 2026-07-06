package ite_istad_product.product_demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String caption;
    @Column(nullable = false)
    private Long size; // bytes
    @Column(nullable = false, length = 15)
    private String extension;
    @Column(nullable = false)// png
    private String mediaType; // file, images, documents
}

