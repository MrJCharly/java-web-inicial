package ar.edu.unju.virtual.manager;

import ar.edu.unju.virtual.model.dao.impl.CuentaDaoImpl;
import ar.edu.unju.virtual.model.domain.Cliente;
import ar.edu.unju.virtual.model.domain.Cuenta;

public class CuentaManager {
  private CuentaDaoImpl cuentaDao;
  private Cuenta cuenta;
  
  public CuentaManager(CuentaDaoImpl cuentaDao, Cuenta cuenta) {
    this.cuentaDao = cuentaDao;
    this.cuenta = cuenta;
  }
  
  public void realizarDeposito(Cliente cliente, Float deposito) {
    cuenta.realizarDeposito(deposito);
    cuentaDao.update(cuenta);
  }

  public void realizarExtraccion(Cliente cliente, Float extraccion) {
    cuenta.realizarExtraccion(extraccion);
    cuentaDao.update(cuenta);
  }
}
