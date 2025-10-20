package br.com.fiap.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "TDS_TB_BRINQUEDO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brinquedo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 50, message = "Tipo deve ter no máximo 50 caracteres")
    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @NotBlank(message = "Classificação é obrigatória")
    @Size(max = 50, message = "Classificação deve ter no máximo 50 caracteres")
    @Column(name = "CLASSIFICACAO", nullable = false, length = 50)
    private String classificacao;

    @NotBlank(message = "Tamanho é obrigatório")
    @Size(max = 50, message = "Tamanho deve ter no máximo 50 caracteres")
    @Column(name = "TAMANHO", nullable = false, length = 50)
    private String tamanho;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    @Column(name = "PRECO", nullable = false)
    private Float preco;
}

