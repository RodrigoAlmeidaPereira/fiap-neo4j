package br.com.fiap.rodrigo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
@EntityScan("br.com.fiap.rodrigo")
@EnableNeo4jRepositories(basePackageClasses = PessoaRepository.class)
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner demo(final PessoaRepository pessoaRepository) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                pessoaRepository.deleteAll();
                Pessoa p1 = new Pessoa("Rafael");
                Pessoa p2 = new Pessoa("Zangief");
                Pessoa p3 = new Pessoa("Blanka");
                List<Pessoa> time = Arrays.asList(p1, p2, p3);

                time.stream().forEach(System.out::println);

                pessoaRepository.save(p1);
                pessoaRepository.save(p2);
                pessoaRepository.save(p3);

                p1 = pessoaRepository.findByNome(p1.getNome());
                p1.worksWith(p2);
                p1.worksWith(p3);
                pessoaRepository.save(p1);
                p2 = pessoaRepository.findByNome(p2.getNome());
                p2.worksWith(p3);
                pessoaRepository.save(p2);
                time.forEach(pessoa -> System.out.println(pessoaRepository.findByNome(pessoa.getNome())));
            }
        };
    }
}
