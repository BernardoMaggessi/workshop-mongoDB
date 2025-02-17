package com.bernardomaggessi.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardomaggessi.workshopmongo.domain.Post;
import com.bernardomaggessi.workshopmongo.repository.PostRepository;
import com.bernardomaggessi.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;

	public List<Post> findAll(){
		return repo.findAll();
	}
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	//http://localhost:8080/posts/titlesearch?text=bom%20dia
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
		//return repo.findByTitleContainingIgnoreCase(text);
	}
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() +24*60*60*1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
	public List<Post> searchByAuthor(String txt){
		return repo.searchByAuthor(txt);
	}
}
