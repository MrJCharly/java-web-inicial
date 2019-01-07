package ar.edu.unju.virtual.model.dao;

import java.util.List;

import ar.edu.unju.virtual.model.domain.Cuenta;

public interface CuentaDao {
  List<Cuenta> findAll();
  List<Cuenta> findAll(Cuenta example);
  Long create(Cuenta cuenta);
  void update(Cuenta cuenta);
  void delete(Cuenta cuenta);
}
