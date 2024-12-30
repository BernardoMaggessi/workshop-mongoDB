package com.bernardomaggessi.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bernardomaggessi.workshopmongo.DTO.AuthorDto;
import com.bernardomaggessi.workshopmongo.domain.Post;
import com.bernardomaggessi.workshopmongo.domain.User;
import com.bernardomaggessi.workshopmongo.repository.PostRepository;
import com.bernardomaggessi.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown","Maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",new AuthorDto(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDto(maria));

		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
	}
	

}
