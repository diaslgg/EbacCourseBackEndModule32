package br.com.diaslgg.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @SequenceGenerator(name = "produto_seq", sequenceName = "sq_produto", initialValue = 1 , allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 150, nullable = false, unique = false)
    private String name;

    @Column(name = "CODE", length = 10, nullable = false, unique = true)
    private String code;

    @Column(name = "TOTAL_VALUE", nullable = false)
    private Double totalValue;

    public Produto(String name, String code, Double totalValue) {
        this.name = name;
        this.code = code;
        this.totalValue = totalValue;
    }
}
