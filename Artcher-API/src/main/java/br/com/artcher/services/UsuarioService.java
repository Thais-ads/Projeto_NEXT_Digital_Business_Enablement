package br.com.artcher.services;

import br.com.artcher.model.UsuarioModel;
import br.com.artcher.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends MyService<UsuarioModel, Long>{

    public UsuarioService(UsuarioRepository repository) {super(repository);}
}