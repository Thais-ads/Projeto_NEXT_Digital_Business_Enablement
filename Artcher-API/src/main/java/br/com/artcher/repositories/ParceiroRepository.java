package br.com.artcher.repositories;

import br.com.artcher.model.ParceiroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceiroRepository extends JpaRepository<ParceiroModel, Long> {
}
