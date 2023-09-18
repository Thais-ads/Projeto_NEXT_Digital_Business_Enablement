package br.com.artcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_ENDERECO")
public class EnderecoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true, length = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 7)
    private int cep;

    @Column(nullable = false)
    @OrderColumn(name = "2")
    private String logradouro;

    @Column(nullable = false)
    @OrderColumn(name = "3")
    private String numero;

    @OrderColumn(name = "4")
    private String complemento;

    @Column(nullable = false)
    @OrderColumn(name = "5")
    private String bairro;

    @Column(nullable = false)
    @OrderColumn(name = "6")
    private String cidade;

    @Column(nullable = false)
    @OrderColumn(name = "7")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_USUARIO"))
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "parceiro_id", foreignKey = @ForeignKey(name = "FK_PARCEIRO"))
    private ParceiroModel parceiro;

}
