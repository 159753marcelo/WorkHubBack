package com.marcelo.workhub.repository;

import com.marcelo.workhub.model.Empresa;
import com.marcelo.workhub.model.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OportunidadeRepository extends JpaRepository<Oportunidade,Long> {


    List<Oportunidade> findByTitulo( String titulo);

}
