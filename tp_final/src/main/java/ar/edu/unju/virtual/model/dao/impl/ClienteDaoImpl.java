package ar.edu.unju.virtual.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;

import ar.edu.unju.virtual.model.dao.ClienteDao;
import ar.edu.unju.virtual.model.domain.Cliente;
import ar.edu.unju.virtual.model.hibernate.util.BaseHibernate;

public class ClienteDaoImpl extends BaseHibernate implements ClienteDao {

  @Override
  public List<Cliente> findAll() {
    Criteria criteria = getSession().createCriteria(Cliente.class);
    return criteria.list();
  }

}
