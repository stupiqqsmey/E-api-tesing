package ite_istad_product.product_demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "profile_tbl")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    private String bio;
    private String firstName;
    private String lastName;
    private String gender;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, referencedColumnName = "id")
    private User user;

}
