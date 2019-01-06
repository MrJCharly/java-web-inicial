package ar.edu.unju.virtual.model.dao;

import java.util.List;

import ar.edu.unju.virtual.model.domain.Cliente;

public interface ClienteDao {
  List<Cliente> findAll();
}
