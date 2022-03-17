package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Model;
import com.example.demo.repository.Repository;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")

public class Controlador {

	@Autowired
	Repository repo;

	@GetMapping("/peliculas")
	public ResponseEntity<List<Model>> getAllPeliculas(@RequestParam(required = false) String nombre) {
		try {
			List<Model> peliculas = new ArrayList<Model>();

			if (nombre == null)
				repo.findAll().forEach(peliculas::add);
			else
				repo.findByNombre(nombre).forEach(peliculas::add);

			if (peliculas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(peliculas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/peliculas/{id}")
	public ResponseEntity<Model> getPeliculaById(@PathVariable("id") long id_pelicula) {
		
		Optional<Model> Data = repo.findById(id_pelicula);

		if (Data.isPresent()) {
			return new ResponseEntity<>(Data.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/peliculas")
	public ResponseEntity<Model> createPelicula(@RequestBody Model pelicula) {
		try {
			Model _pelicula = repo.save(new Model(pelicula.getNombre(), pelicula.getGenero(), 
					pelicula.getAutor(), pelicula.getAnio(), pelicula.getDatetime()));
			return new ResponseEntity<>(_pelicula, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/peliculas/{id}")
	public ResponseEntity<Model> updatePelicula(@PathVariable("id") long id, @RequestBody Model pelicula) {
		Optional<Model> data = repo.findById(id);

		if (data.isPresent()) {
			Model _pelicula = data.get();
			_pelicula.setNombre(pelicula.getNombre());
			_pelicula.setGenero(pelicula.getGenero());
			_pelicula.setAnio(pelicula.getAnio());
			_pelicula.setAutor(pelicula.getAutor());
			_pelicula.setDatetime(pelicula.getDatetime());
			
			return new ResponseEntity<>(repo.save(_pelicula), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/peliculas/{id}")
	public ResponseEntity<HttpStatus> deletePelicula(@PathVariable("id") long id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/peliculas")
	public ResponseEntity<HttpStatus> deleteAllPeliculas() {
		try {
			repo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

}
