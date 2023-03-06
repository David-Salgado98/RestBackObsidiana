package org.obisidiana.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dire;
    private Double cost;
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mat_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Material material;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typ_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Type type;



}
