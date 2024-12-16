package com.bernardomaggessi.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardomaggessi.workshopmongo.DTO.UserDTO;
import com.bernardomaggessi.workshopmongo.domain.User;
import com.bernardomaggessi.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());//TRANSFORMA A LISTA DE USERS EM UMA LISTA DE USERSDTO
		return ResponseEntity.ok().body(listDto);
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User obj = service.fundById(id);
		return ResponseEntity.ok().body(new UserDTO(obj	));
	}
}
