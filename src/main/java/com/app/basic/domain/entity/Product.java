package com.app.basic.domain.entity;

import com.app.basic.domain.type.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
@Entity
@Table(name = "TBL_PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255) // NOT NULL, varchar2(255)
    private String productName;
    @ColumnDefault("0") // 숫자타입도 문자열로
    private Long productPrice;
    private String productBrand;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

//    LocalDate, LocalDateTime
//    private LocalDate productReleaseDate; // DATE
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime productReleaseDate; // TIMESTAMP


}
