package ar.edu.unju.virtual.manager;

import java.util.Date;

import ar.edu.unju.virtual.exceptions.ExtraccionException;
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

  // Realizar extracciones y generar movimientos, verificando condiciones.
  public Movimiento realizarExtraccion(Cliente cliente, Float extraccion) throws ExtraccionException {
    // Verificar condiciones de extracción.
    
    // Importe > 0.
    if (extraccion <= 0) {
      throw new ExtraccionException("Importe debe ser positivo");
    }
    
    // Importe < limite de extracción.
    if (extraccion > cuenta.getLimiteExtraccion()) {
      throw new ExtraccionException("Importe debe ser menor al límite: " + cuenta.getLimiteExtraccion());
    }
    
    // Importe > saldo actual.
    if (extraccion > cuenta.getSaldoActual()) {
      throw new ExtraccionException("Importe debe ser menor al saldo actual: " + cuenta.getSaldoActual());
    }
    
    // Cuenta inhabilitada.
    if (cuenta.getEstado().equals(Cuenta.INHABILITADO)) {
      throw new ExtraccionException("La cuenta debe estar habilitada");
    }
    
    // Realizar extracción.
    cuenta.realizarExtraccion(extraccion);
    cuentaDao.update(cuenta);
    
    // Generar movimiento.
    Movimiento mov = new Movimiento();
    
    mov.setCuenta(cuenta);
    mov.setIdCliente(cliente.getId());
    mov.setCredito(0f);
    mov.setDebito(extraccion);
    mov.setFecha(new Date());
    mov.setSaldo(cuenta.getSaldoActual());
    
    movDao.create(mov);
    
    return mov;
  }
}
