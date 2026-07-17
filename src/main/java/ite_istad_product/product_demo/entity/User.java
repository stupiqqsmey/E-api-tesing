package ite_istad_product.product_demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "user_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;

    private String username;
    // must include this
//    private String keycloakUserId;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @Column(unique = true, nullable = false)
    private String keycloakId;
}
