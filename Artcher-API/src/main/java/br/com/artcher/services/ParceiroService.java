package br.com.artcher.services;

import br.com.artcher.model.ParceiroModel;
import br.com.artcher.repositories.ParceiroRepository;
import org.springframework.stereotype.Service;

@Service
public class ParceiroService extends MyService<ParceiroModel, Long>{

    public ParceiroService(ParceiroRepository repository) {super(repository);}
}
