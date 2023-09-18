package br.com.artcher.services;

import br.com.artcher.model.AvaliacaoModel;
import br.com.artcher.repositories.AvaliacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService extends MyService<AvaliacaoModel, Long>{

    public AvaliacaoService(AvaliacaoRepository repository) {super(repository);}
}