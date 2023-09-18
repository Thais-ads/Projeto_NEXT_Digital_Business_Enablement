package br.com.artcher.dtos;

import br.com.artcher.model.ParceiroModel;
import br.com.artcher.model.UsuarioModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AvaliacaoDto {

    @Schema(example = "4.5")
    @NotNull(message = "Avalição obrigatória")
    private float avaliacao;

    @Schema(example = "Ótima experiência")
    private String comentario;

    private UsuarioModel usuario;

    private ParceiroModel parceiro;
}


