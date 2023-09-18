package br.com.artcher.dtos;

import br.com.artcher.annotations.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UsuarioDto {

    @Schema(example = "Jose")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Schema(example = "Silva")
    @NotBlank(message = "O sobrenome é obrigatório")
    private String sobrenome;

    @Schema(example = "216322259-28")
    @NotBlank(message = "O CPF é obrigatório")
    private long cpf;

    @Schema(example = "jose@gmail.com")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotNull(message = "A senha é obrigatória")
    private String senha;

    @NotNull(message = "Data de nascimento obrigatória")
    private LocalDateTime dataNascimento;

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