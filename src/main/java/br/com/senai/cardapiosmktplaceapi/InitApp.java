package br.com.senai.cardapiosmktplaceapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import br.com.senai.cardapiosmktplaceapi.entity.Categoria;
import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.repository.CategoriaRepository;
import br.com.senai.cardapiosmktplaceapi.repository.RestauranteRepository;

@SpringBootApplication
public class InitApp {

	@Autowired
	private CategoriaRepository categoriasRepository;
	
	@Autowired
	private RestauranteRepository restaurantesRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			Categoria categoria = categoriasRepository.buscarPor(39);
			List<Restaurante> restaurantes = restaurantesRepository.listarPor("%rest%", categoria, PageRequest.of(0, 15)).getContent();
			for (Restaurante r : restaurantes) {
				System.out.println(r.getNome());
			}
		};
	}
	
}
