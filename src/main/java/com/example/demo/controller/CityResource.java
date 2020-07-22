package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.bean.City;
import com.example.demo.model.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping ("/cidades")
public class CityResource {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping ("/cidades")
    public List <City> cidades () {
        return cityRepository.findAll();
    }

    @PostMapping ("/salvar")
    public ResponseEntity<City> registrarCidades (@RequestBody City paramCidade, HttpServletResponse response) {
        City city = cityRepository.save(paramCidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(city.getId()).toUri();
        return ResponseEntity.created(uri).body(city);
    }

    @GetMapping ("/primeiraLetra/{name}")
    public List<City> buscarNome (@PathVariable String name) {
        return cityRepository.listarPorLetraEspecifica(name);
    }

    @GetMapping ("/Coord/{latitude}/{longitude}")
    public List<City> buscarCoordenada (@PathVariable String latitude, @PathVariable String longitude) {
        return cityRepository.listarPorLatitudeLongitude(latitude, longitude);
    }

}