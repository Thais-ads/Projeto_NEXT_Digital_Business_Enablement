package br.com.artcher.controllers;

import br.com.artcher.dtos.ParceiroDto;
import br.com.artcher.model.ParceiroModel;
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

@Tag(name = "Parceiro", description = "API para gerenciamento de parceiros")
@RestController
@RequestMapping("/parceiro")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ParceiroController extends GenericController{

    final
    ParceiroService parceiroService;


    public ParceiroController(ParceiroService parceiroService) {this.parceiroService = parceiroService;}

    @Operation(summary = "Lista todos os Parceiros", description = "Lista todos os Parceiros")
    @GetMapping("/")
    public ResponseEntity<Object> Get(){
        return ResponseEntity.status(HttpStatus.OK).body(parceiroService.getAll());
    }


    @Operation(summary = "Recupera um parceiro pelo ID", description = "Recupera os dados de um parceiro a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Parceiro encontrado com sucesso", content = @Content(schema = @Schema(implementation = ParceiroModel.class)))
    @ApiResponse(responseCode = "404", description = "Parceiro não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ParceiroModel> getById(@PathVariable Long id) {
        Optional<ParceiroModel> optionalParceiro = Optional.ofNullable(parceiroService.findById(id));

        if (optionalParceiro.isPresent()) {
            return ResponseEntity.ok(optionalParceiro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Salva o parceiro", description = "Salva parceiro")
    @ApiResponse(responseCode = "201", description = "Parceiro salvo com sucesso", content = @Content(schema = @Schema(implementation = ParceiroModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody @Valid ParceiroDto parceiroDto){
        var parceiro = new ParceiroModel();
        BeanUtils.copyProperties(parceiroDto, parceiro);
        return ResponseEntity.status(HttpStatus.CREATED).body(parceiroService.save(parceiro));
    }



    @Operation(summary = "Altera um parceiro pelo ID", description = "Altera os dados de um parceiro a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Parceiro alterado com sucesso", content = @Content(schema = @Schema(implementation = ParceiroModel.class)))
    @ApiResponse(responseCode = "404", description = "Parceiro não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid ParceiroDto parceiroDto) {
        Optional<ParceiroModel> optionalParceiro = Optional.ofNullable(parceiroService.findById(id));
        if (!optionalParceiro.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parceiro não encontrado!");
        }
        var parceiro = new ParceiroModel();
        BeanUtils.copyProperties(parceiroDto, parceiro);
        parceiro.setId(optionalParceiro.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(parceiroService.save(parceiro));
    }


    @Operation(summary = "Exclui um Parceiro pelo Id" , description = "Exclui um Parceiro a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "Parceiro excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        parceiroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
