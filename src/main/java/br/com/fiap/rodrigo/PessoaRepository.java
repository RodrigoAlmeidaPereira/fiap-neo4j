package br.com.fiap.rodrigo;

import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    Pessoa findByNome(String nome);
}
