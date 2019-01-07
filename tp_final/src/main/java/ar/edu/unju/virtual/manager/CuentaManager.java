package ar.edu.unju.virtual.manager;

import java.util.Date;

import ar.edu.unju.virtual.model.dao.impl.CuentaDaoImpl;
import ar.edu.unju.virtual.model.dao.impl.MovimientoDaoImpl;
import ar.edu.unju.virtual.model.domain.Cliente;
import ar.edu.unju.virtual.model.domain.Cuenta;
import ar.edu.unju.virtual.model.domain.Movimiento;

public class CuentaManager {
  private CuentaDaoImpl cuentaDao;
  private MovimientoDaoImpl movDao;
  private Cuenta cuenta;
  
  public CuentaManager(CuentaDaoImpl cuentaDao, MovimientoDaoImpl movDao, Cuenta cuenta) {
    this.cuentaDao = cuentaDao;
    this.movDao = movDao;
    this.cuenta = cuenta;
  }
  
  // Realizar depositos y generar los movimientos correspondientes.
  public Movimiento realizarDeposito(Cliente cliente, Float deposito) {
    // Realizar el depósito.
    cuenta.realizarDeposito(deposito);
    cuentaDao.update(cuenta);
    
    // Generar movimiento.
    Movimiento mov = new Movimiento();
    
    mov.setCuenta(cuenta);
    mov.setIdCliente(cliente.getId());
    mov.setCredito(deposito);
    mov.setDebito(0f);
    mov.setFecha(new Date());
    mov.setSaldo(cuenta.getSaldoActual());
    
    movDao.create(mov);
    
    return mov;
  }

  public void realizarExtraccion(Cliente cliente, Float extraccion) {
    cuenta.realizarExtraccion(extraccion);
    cuentaDao.update(cuenta);
  }
}
