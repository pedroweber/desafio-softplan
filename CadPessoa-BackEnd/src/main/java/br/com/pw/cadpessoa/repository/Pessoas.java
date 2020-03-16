package br.com.pw.cadpessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pw.cadpessoa.entity.Pessoa;

@Repository
public interface Pessoas extends JpaRepository<Pessoa, String> { }