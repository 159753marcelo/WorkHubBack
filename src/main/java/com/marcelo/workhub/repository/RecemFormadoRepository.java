package com.marcelo.workhub.repository;

import com.marcelo.workhub.model.Empresa;
import com.marcelo.workhub.model.RecemFormado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecemFormadoRepository extends JpaRepository<RecemFormado,Long> {

    @Query("SELECT r FROM RecemFormado r WHERE r.curriculo LIKE %:qualificacao%")
    List<RecemFormado> findByCurriculo(String qualificacao );
}
