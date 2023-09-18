package br.com.artcher.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name= "TB_AVALIACAO")
public class AvaliacaoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true, length = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private float avaliacao;


    private String comentario;


    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_USUARIO"))
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "parceiro_id", foreignKey = @ForeignKey(name = "FK_PARCEIRO"))
    private ParceiroModel parceiro;


}
