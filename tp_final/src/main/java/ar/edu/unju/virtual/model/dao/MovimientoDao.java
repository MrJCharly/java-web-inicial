package ar.edu.unju.virtual.model.dao;

import java.util.List;

import ar.edu.unju.virtual.model.domain.Movimiento;

public interface MovimientoDao {
  Movimiento findById(Long id);
  List<Movimiento> findAll();
  List<Movimiento> findAll(Movimiento example);
  Long create(Movimiento mov);
  void update(Movimiento mov);
  void delete(Movimiento mov);
}
