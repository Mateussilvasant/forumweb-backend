package br.com.mateussilvasant.forumweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.mateussilvasant.forumweb.api.configurations.repository.JpaCustomRepository;

@SpringBootApplication
@ComponentScan(basePackages = { "br.com.mateussilvasant.forumweb.api" })
@EnableJpaRepositories(repositoryBaseClass = JpaCustomRepository.class)
public class ForumWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumWebApplication.class, args);
	}

}
