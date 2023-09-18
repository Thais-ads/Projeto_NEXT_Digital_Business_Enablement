package br.com.artcher.services;

import br.com.artcher.model.EnderecoModel;
import br.com.artcher.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends MyService<EnderecoModel, Long>{

    public EnderecoService(EnderecoRepository repository) {super(repository);}
}