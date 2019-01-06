package ar.edu.unju.virtual.model.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseHibernate {
  private SessionFactory sessionFactory;
  private Session session;
  
  public BaseHibernate() {
    sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    session = sessionFactory.openSession();
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }
  
}
