package br.com.artcher.controllers;

import br.com.artcher.dtos.UsuarioDto;
import br.com.artcher.model.UsuarioModel;
import br.com.artcher.services.UsuarioService;
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

@Tag(name = "Usuario", description = "API para gerenciamento de usuarios")
@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController extends GenericController{

    final
    UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {this.usuarioService = usuarioService;}

    @Operation(summary = "Lista todos os Usuarios", description = "Lista todos os Usuarios")
    @GetMapping("/")
    public ResponseEntity<Object> Get(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAll());
    }


    @Operation(summary = "Recupera um usuario pelo ID", description = "Recupera os dados de um usuario a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getById(@PathVariable Long id) {
        Optional<UsuarioModel> optionalUsuario = Optional.ofNullable(usuarioService.findById(id));

        if (optionalUsuario.isPresent()) {
            return ResponseEntity.ok(optionalUsuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Salva o usuario e envia email de boas vindas", description = "Salva o usuario")
    @ApiResponse(responseCode = "201", description = "Usuario salvo com sucesso", content = @Content(schema = @Schema(implementation = UsuarioModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody @Valid UsuarioDto usuarioDto){
        var usuario = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }



    @Operation(summary = "Altera um usuario pelo ID", description = "Altera os dados de um usuario a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Usuario alterado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid UsuarioDto usuarioDto) {
        Optional<UsuarioModel> optionalUsuario = Optional.ofNullable(usuarioService.findById(id));
        if (!optionalUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        var usuario = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuario);
        usuario.setId(optionalUsuario.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }


    @Operation(summary = "Exclui um usuario pelo Id" , description = "Exclui um usuario a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "usuario excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}