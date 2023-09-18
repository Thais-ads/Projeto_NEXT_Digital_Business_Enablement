package br.com.artcher.controllers;

import br.com.artcher.dtos.LoginDto;
import br.com.artcher.model.LoginModel;
import br.com.artcher.services.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        var login = new LoginModel();
        BeanUtils.copyProperties(loginDto, login);
        String email = login.getEmail();
        String password = login.getPassword();

        try {
            UserDetails userDetails = loginService.loadUserByUsername(email);
            if (password.equals(userDetails.getPassword())) {
                return ResponseEntity.ok("Login bem-sucedido");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }
    }
}
