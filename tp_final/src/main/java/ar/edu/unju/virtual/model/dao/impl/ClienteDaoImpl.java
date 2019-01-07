package ar.edu.unju.virtual.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import ar.edu.unju.virtual.model.dao.ClienteDao;
import ar.edu.unju.virtual.model.domain.Cliente;
import ar.edu.unju.virtual.model.hibernate.util.BaseHibernate;

public class ClienteDaoImpl extends BaseHibernate implements ClienteDao {

  public List<Cliente> findAll() {
    Criteria criteria = getSession().createCriteria(Cliente.class);
    return criteria.list();
  }

  public List<Cliente> findAll(Cliente example) {
    Criteria criteria = getSession().createCriteria(Cliente.class);
    criteria.add(Example.create(example).excludeZeroes());
    
    return criteria.list();
  }
  
  public Long create(Cliente cliente) {
    Transaction tx = getSession().beginTransaction();
    Long id = (Long) getSession().save(cliente);
    
    tx.commit();
    return id;
  }

  public void delete(Cliente cliente) {
    Transaction tx = getSession().beginTransaction();
    getSession().delete(cliente);
    
    tx.commit();
  }

}
