package br.com.artcher.controllers;

import br.com.artcher.dtos.AvaliacaoDto;
import br.com.artcher.model.AvaliacaoModel;
import br.com.artcher.services.AvaliacaoService;
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

@Tag(name = "Avaliacao", description = "API para gerenciamento de avaliações")
@RestController
@RequestMapping("/avaliacao")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AvaliacaoController extends GenericController{

    final
    AvaliacaoService avaliacaoService;


    public AvaliacaoController(AvaliacaoService avaliacaoService) {this.avaliacaoService = avaliacaoService;}

    @Operation(summary = "Lista todos as avaliações", description = "Lista todos as avaliações")
    @GetMapping("/")
    public ResponseEntity<Object> Get(){
        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoService.getAll());
    }


    @Operation(summary = "Recupera uma avaliação pelo ID", description = "Recupera os dados de uma avaliação a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Avaliação encontrada com sucesso", content = @Content(schema = @Schema(implementation = AvaliacaoModel.class)))
    @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoModel> getById(@PathVariable Long id) {
        Optional<AvaliacaoModel> optionalAvaliacao = Optional.ofNullable(avaliacaoService.findById(id));

        if (optionalAvaliacao.isPresent()) {
            return ResponseEntity.ok(optionalAvaliacao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Salva uma avaliação", description = "Salva uma avaliação")
    @ApiResponse(responseCode = "201", description = "Avaliação salva com sucesso", content = @Content(schema = @Schema(implementation = AvaliacaoModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody @Valid AvaliacaoDto avaliacaoDto){
        var avaliacao = new AvaliacaoModel();
        BeanUtils.copyProperties(avaliacaoDto, avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.save(avaliacao));
    }



    @Operation(summary = "Altera uma avaliação pelo ID", description = "Altera os dados de uma avaliação a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Avaliação alterada com sucesso", content = @Content(schema = @Schema(implementation = AvaliacaoModel.class)))
    @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid AvaliacaoDto avaliacaoDto) {
        Optional<AvaliacaoModel> optionalAvaliacao = Optional.ofNullable(avaliacaoService.findById(id));
        if (!optionalAvaliacao.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada!");
        }
        var avaliacao = new AvaliacaoModel();
        BeanUtils.copyProperties(avaliacaoDto, avaliacao);
        avaliacao.setId(optionalAvaliacao.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoService.save(avaliacao));
    }


    @Operation(summary = "Exclui uma avaliação pelo Id" , description = "Exclui uma avaliação a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Avaliação excluida com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        avaliacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}