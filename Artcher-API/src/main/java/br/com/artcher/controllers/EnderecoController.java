package br.com.artcher.controllers;

import br.com.artcher.dtos.EnderecoDto;
import br.com.artcher.dtos.ParceiroDto;
import br.com.artcher.model.EnderecoModel;
import br.com.artcher.model.ParceiroModel;
import br.com.artcher.services.EnderecoService;
import br.com.artcher.services.ParceiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Endereco", description = "API para gerenciamento de endereços")
@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnderecoController extends GenericController{

    final
    EnderecoService enderecoService;


    public EnderecoController(EnderecoService enderecoService) {this.enderecoService = enderecoService;}

    @Operation(summary = "Lista todos os Endereço", description = "Lista todos os Endereço")
    @GetMapping("/")
    public ResponseEntity<Object> Get(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAll());
    }


    @Operation(summary = "Recupera um Endereco pelo ID", description = "Recupera os dados de um Endereco a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Endereco encontrado com sucesso", content = @Content(schema = @Schema(implementation = EnderecoModel.class)))
    @ApiResponse(responseCode = "404", description = "Endereco não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> getById(@PathVariable Long id) {
        Optional<EnderecoModel> optionalEndereco = Optional.ofNullable(enderecoService.findById(id));

        if (optionalEndereco.isPresent()) {
            return ResponseEntity.ok(optionalEndereco.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Salva o Endereco", description = "Salva Endereco")
    @ApiResponse(responseCode = "201", description = "Endereco salvo com sucesso", content = @Content(schema = @Schema(implementation = EnderecoModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody @Valid EnderecoDto enderecoDto){
        var endereco = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(endereco));
    }



    @Operation(summary = "Altera um Endereco pelo ID", description = "Altera os dados de um Endereco a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Endereco alterado com sucesso", content = @Content(schema = @Schema(implementation = EnderecoModel.class)))
    @ApiResponse(responseCode = "404", description = "Endereco não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid EnderecoDto enderecoDto) {
        Optional<EnderecoModel> optionalEndereco = Optional.ofNullable(enderecoService.findById(id));
        if (!optionalEndereco.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }
        var endereco = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, endereco);
        endereco.setId(optionalEndereco.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(endereco));
    }


    @Operation(summary = "Exclui um Endereco pelo Id" , description = "Exclui um Endereco a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Endereco excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
