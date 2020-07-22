package com.example.demo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.bean.City;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long>{

	@Query
			("SELECT cidade FROM Cidade cidade WHERE cidade.nome LIKE ?1%")
	public List<City> listarPorLetraEspecifica(String name);

	@Query
			("SELECT cidade FROM Cidade cidade WHERE cidade.latitude = ?1 AND cidade.longitude = ?2")
	public List<City> listarPorLatitudeLongitude(String latitude, String longitude);

}
