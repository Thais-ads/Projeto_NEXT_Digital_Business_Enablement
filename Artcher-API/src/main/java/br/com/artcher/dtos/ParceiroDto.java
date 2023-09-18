package br.com.artcher.dtos;

import br.com.artcher.annotations.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ParceiroDto {

    @Schema(example = "Restaurante ltda")
    @NotBlank(message = "Razão social é obrigatória")
    private String razaoSocial;

    @Schema(example = "Restaurante do jose")
    @NotBlank(message = "O nomefantasia é obrigatório")
    private String nomeFantasia;

    @Schema(example = "13.021.259/0001-39")
    @NotBlank(message = "O CNPJ é obrigatório")
    private long cnpj;

    private long inscricaoEstadual;

    @Schema(example = "restaurantedojose@gmail.com")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotNull(message = "A senha é obrigatória")
    private String senha;

    @NotNull(message = "O telefone é obrigatório")
    @Phone
    private String telefone1;

    @Phone
    private String telefone2;

    @NotNull(message = "O endereço é obrigatório")
    private EnderecoDto endereco;

    public List<EnderecoDto> getEndereco() {
        return (List<EnderecoDto>) endereco;
    }

}
