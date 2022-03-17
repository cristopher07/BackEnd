package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Model;

public interface Repository extends JpaRepository<Model, Long>{

	List<Model> findByNombre(String nombre);
}
