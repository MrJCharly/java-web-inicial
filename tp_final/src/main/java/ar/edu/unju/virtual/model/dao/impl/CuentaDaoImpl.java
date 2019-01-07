package ar.edu.unju.virtual.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import ar.edu.unju.virtual.model.dao.CuentaDao;
import ar.edu.unju.virtual.model.domain.Cuenta;
import ar.edu.unju.virtual.model.hibernate.util.BaseHibernate;

public class CuentaDaoImpl extends BaseHibernate implements CuentaDao {

  public List<Cuenta> findAll() {
    Criteria criteria = getSession().createCriteria(Cuenta.class);
    return criteria.list();
  }

  public List<Cuenta> findAll(Cuenta example) {
    Criteria criteria = getSession().createCriteria(Cuenta.class);
    criteria.add(Example.create(example).excludeZeroes());
    
    return criteria.list();
  }

  public Long create(Cuenta cuenta) {
    Transaction tx = getSession().beginTransaction();
    Long id = (Long) getSession().save(cuenta);
    
    tx.commit();
    return id;
  }
  
  public void update(Cuenta cuenta) {
    Transaction tx = getSession().beginTransaction();
    getSession().update(cuenta);
    
    tx.commit();    
  }

  public void delete(Cuenta cuenta) {
    Transaction tx = getSession().beginTransaction();
    getSession().delete(cuenta);
    
    tx.commit();
  }

}
