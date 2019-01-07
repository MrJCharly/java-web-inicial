package ar.edu.unju.virtual.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import ar.edu.unju.virtual.model.dao.MovimientoDao;
import ar.edu.unju.virtual.model.domain.Movimiento;
import ar.edu.unju.virtual.model.hibernate.util.BaseHibernate;

public class MovimientoDaoImpl extends BaseHibernate implements MovimientoDao {

  public Movimiento findById(Long id) {
    return (Movimiento) getSession().get(Movimiento.class, id);
  }
  
  public List<Movimiento> findAll() {
    Criteria criteria = getSession().createCriteria(Movimiento.class);
    return criteria.list();
  }

  public List<Movimiento> findAll(Movimiento example) {
    Criteria criteria = getSession().createCriteria(Movimiento.class);
    criteria.add(Example.create(example).excludeZeroes());
    
    return criteria.list();
  }

  public Long create(Movimiento mov) {
    Transaction tx = getSession().beginTransaction();
    Long id = (Long) getSession().save(mov);
    
    tx.commit();
    return id;
  }
  
  public void update(Movimiento mov) {
    Transaction tx = getSession().beginTransaction();
    getSession().update(mov);
    
    tx.commit();    
  }

  public void delete(Movimiento mov) {
    Transaction tx = getSession().beginTransaction();
    getSession().delete(mov);
    
    tx.commit();
  }

}
