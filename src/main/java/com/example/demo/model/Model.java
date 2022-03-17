package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name= "tb_peliculas")

public class Model {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_pelicula;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@Column(name = "genero", length = 50, nullable = false)
	private String genero; 
	
	@Column(name = "anio", length = 50, nullable = false)
	private String anio;
	
	@Column(name = "autor", length = 50, nullable = false)
	private String autor;
	
	@Column(name = "datetime")
	private String datetime;

	public long getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(long id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Model(String nombre, String genero, String anio, String autor, String datetime) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.anio = anio;
		this.autor = autor;
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "model [id_pelicula=" + id_pelicula + ", nombre=" + nombre + ", genero=" + genero + ", anio=" + anio
				+ ", autor=" + autor + ", datetime=" + datetime + "]";
	}

	public Model() {
		super();
	}
	
	
	
	
}
