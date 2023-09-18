package br.com.artcher.dtos;

import br.com.artcher.model.ParceiroModel;
import br.com.artcher.model.UsuarioModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnderecoDto {

    @Schema(example = "23970.000")
    @NotNull(message = "Cep é obrigatório")
    private int cep;

    @Schema(example = "Rua Brasil")
    @NotBlank(message = "O logradouro é obrigatório")
    private String logradouro;

    @Schema(example = "13.021.259/0001-39")
    @NotBlank(message = "O CNPJ é obrigatório")
    private String numero;

    private String complemento;

    @Schema(example = "Centro")
    @NotBlank(message = "O email é obrigatório")
    private String bairro;

    @Schema(example = "São Paulo")
    @NotNull(message = "A cidade é obrigatporia")
    private String cidade;

    @Schema(example = "São Paulo")
    @NotNull(message = "O estado é obrigatório")
    private String estado;

    private UsuarioModel usuario;

    private ParceiroModel parceiro;
}
