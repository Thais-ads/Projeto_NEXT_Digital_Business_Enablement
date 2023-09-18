package br.com.artcher.model;

import br.com.artcher.annotations.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "TB_PARCEIRO")
public class ParceiroModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true, length = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String razaoSocial;

    @Column(nullable = false)
    private String nomeFantasia;

    @CNPJ
    @Column(unique = true, nullable = false)
    private long cnpj;

    @Column(unique = true)
    private long inscricaoEstadual;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Phone(message = "Telefone inválido")
    @Column(unique = true, nullable = false)
    private String telefone1;

    @Phone(message = "Telefone inválido")
    private String telefone2;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    @Column(nullable = false)
    private List<EnderecoModel> endereco = new ArrayList<>();
}
