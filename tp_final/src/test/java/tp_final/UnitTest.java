package tp_final;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ar.edu.unju.virtual.exceptions.ExtraccionException;
import ar.edu.unju.virtual.manager.CuentaManager;
import ar.edu.unju.virtual.model.dao.impl.ClienteDaoImpl;
import ar.edu.unju.virtual.model.dao.impl.CuentaDaoImpl;
import ar.edu.unju.virtual.model.dao.impl.MovimientoDaoImpl;
import ar.edu.unju.virtual.model.domain.Cliente;
import ar.edu.unju.virtual.model.domain.Cuenta;
import ar.edu.unju.virtual.model.domain.Movimiento;

public class UnitTest {

  @Test
  public void _1_GestionDeClientesYAdherentes() {
    ClienteDaoImpl clienteDao = new ClienteDaoImpl();
    
    // Crear cliente.
    Cliente cliente = getNewCliente();
    clienteDao.create(cliente);
    
    // Buscar cliente por dni.
    Cliente example = new Cliente();
    
    example.setDni(cliente.getDni());
    List<Cliente> results = clienteDao.findAll(example);
    
    // Debe retornar solo un resultado.
    assertEquals(1, results.size());
    
    // Y debe ser el cliente creado.
    assertEquals(cliente.getId(), results.get(0).getId());    
    
    // Crear adherente.
    Cliente adherente = getNewAdherente(cliente);
    clienteDao.create(adherente);
    
    // Buscar adherente por dni.
    example = new Cliente();
    
    example.setDni(adherente.getDni());
    results = clienteDao.findAll(example);
    
    // Debe retornar solo un resultado.
    assertEquals(1, results.size());
    
    // Y debe ser el adherente creado.
    assertEquals(adherente.getId(), results.get(0).getId());
    
    // Además comprobar que pertenece al cliente titular de la cuenta.
    assertEquals(cliente.getId(), results.get(0).getCliente().getId());
    
    // Finalmente, eliminar el adherente.
    clienteDao.delete(adherente);
    
    // Buscar cliente para comprobar eliminación.            
    example = new Cliente();
    
    example.setDni(adherente.getDni());
    results = clienteDao.findAll(example);
    
    // No debe retornar ningún resultado.
    assertEquals(0, results.size());
    
    // Por último, eliminar cliente.
    clienteDao.delete(cliente);
    
    // Buscar cliente para comprobar eliminación.            
    example = new Cliente();
    
    example.setDni(cliente.getDni());
    results = clienteDao.findAll(example);
    
    // No debe retornar ningún resultado.
    assertEquals(0, results.size());
    
  }

  private Cliente getNewAdherente(Cliente cliente) {
    return new Cliente(cliente, "Jane Doe", 87654321, "321 Fake Str.", "jane.doe@hotmail.com", "jane.doe", "jane.doe", Cliente.HABILITADO);
  }

  private Cliente getNewCliente() {
    return new Cliente("John Doe", 12345678, "123 Fake Str.", "john.doe@hotmail.com", "john.doe", "john.doe", Cliente.HABILITADO);
  }

  @Test
  public void _2_GestionCuentasBancarias() {
    CuentaDaoImpl cuentaDao = new CuentaDaoImpl();
    ClienteDaoImpl clienteDao = new ClienteDaoImpl();
    Cliente titular = getNewCliente();    
    
    // Crear titular.
    clienteDao.create(titular);
    
    // Crear cuenta.
    Cuenta cuenta = getNewCuenta(titular);
    cuentaDao.create(cuenta);
    
    // Buscar cuenta por nro.
    Cuenta example = new Cuenta();
    
    example.setNumero(cuenta.getNumero());
    List<Cuenta> results = cuentaDao.findAll(example);
    
    // Debe retornar un único resultado.
    assertEquals(1, results.size());
    
    // Y debe ser la cuenta creada.
    assertEquals(cuenta.getId(), results.get(0).getId());
    
    // Modificar estado.
    cuenta.setEstado(Cuenta.INHABILITADO);
    cuentaDao.update(cuenta);
    
    // Buscar nuevamente la cuenta y verificar estado.
    results = cuentaDao.findAll(example);
    
    assertEquals(Cuenta.INHABILITADO, results.get(0).getEstado());
    
    // Eliminar cuenta.
    cuentaDao.delete(cuenta);
    
    // Eliminar titular.
    clienteDao.delete(titular);
  }

  private Cuenta getNewCuenta(Cliente titular) {
    Date fecha = new Date();
    return new Cuenta(titular, "1111111111", fecha, 10000f, 3000l, Cuenta.HABILITADO);
  }
  
  @Test
  public void _3_GestionMovimientos() {
    CuentaDaoImpl cuentaDao = new CuentaDaoImpl();
    ClienteDaoImpl clienteDao = new ClienteDaoImpl();
    MovimientoDaoImpl movDao = new MovimientoDaoImpl();
    CuentaManager cm;
    
    // Crear titular.
    Cliente titular = getNewCliente();
    clienteDao.create(titular);
    
    // Crear adherente.
    Cliente adherente = getNewAdherente(titular);
    clienteDao.create(adherente);
    
    // Crear cuenta.
    Cuenta cuenta = getNewCuenta(titular);
    cuentaDao.create(cuenta);
    
    // Crear manager.
    cm = new CuentaManager(cuentaDao, movDao, cuenta);
    
    // Realizar depósito (titular).
    Float saldo_actual = cuenta.getSaldoActual();
    Float deposito = 10500f;
    
    Movimiento mov = cm.realizarDeposito(titular, deposito);
    
    // Comprobar saldo.
    assertEquals(saldo_actual + deposito, cuenta.getSaldoActual(), 0);            
    
    // Buscar por id movimiento.    
    Movimiento result_mov = movDao.findById(mov.getId());    
    
    // EL movimiento existe en DB.
    assertEquals(mov.getId(), result_mov.getId());
    
    // Y pertenece al cliente.
    assertEquals(mov.getIdCliente(), result_mov.getIdCliente());
        
    // COn el saldo actual de la cuenta.
    assertEquals(cuenta.getSaldoActual(), result_mov.getSaldo(), 0);
    
    // Realizar extracción (adherente).
    saldo_actual = cuenta.getSaldoActual();
    Float extraccion = 2500f;
    Movimiento mov_extraccion = null;
    
    try {
      mov_extraccion = cm.realizarExtraccion(adherente, extraccion);
    } catch (ExtraccionException e) {
      e.printStackTrace();
    }
    
    // Comprobar saldo.
    Cuenta example = new Cuenta();
    
    example.setNumero(cuenta.getNumero());
    List<Cuenta> results = cuentaDao.findAll(example);
    assertEquals(saldo_actual - extraccion, results.get(0).getSaldoActual(), 0);
    
    // Comprobar movimiento.
    Movimiento result_mov_extraccion = movDao.findById(mov_extraccion.getId());    
    
    // EL movimiento existe en DB.
    assertEquals(mov_extraccion.getId(), result_mov_extraccion.getId());
    
    // Y pertenece al cliente.
    assertEquals(mov_extraccion.getIdCliente(), result_mov_extraccion.getIdCliente());
        
    // COn el saldo actual de la cuenta.
    assertEquals(cuenta.getSaldoActual(), result_mov_extraccion.getSaldo(), 0);
    
    // Intentar extraer importe 0 o negativo.
    boolean controla_importe_cero = false;
    
    try {
      cm.realizarExtraccion(adherente, 0f);
    } catch (ExtraccionException e) {
      controla_importe_cero = true;      
    }
    
    assertEquals(true, controla_importe_cero);
    
    boolean controla_importe_negativo = false;
    
    try {
      cm.realizarExtraccion(adherente, 0f);
    } catch (ExtraccionException e) {
      controla_importe_negativo = true;
    }
    
    assertEquals(true, controla_importe_negativo);
    
    // Intentar extraer importe superior al permitido.
    boolean controla_importe_superior_al_permitido = false;
    
    try {
      cm.realizarExtraccion(adherente, cuenta.getLimiteExtraccion() + 1f);
    } catch (ExtraccionException e) {
      controla_importe_superior_al_permitido = true;
    }
    
    assertEquals(true, controla_importe_superior_al_permitido);
    
    // Intentar extraer un importe superior al existente.
    boolean controla_importe_superior_al_saldo = false;
    
    try {
      cm.realizarExtraccion(adherente, cuenta.getSaldoActual() + 1f);
    } catch (ExtraccionException e) {
      controla_importe_superior_al_saldo = true;
    }
    
    assertEquals(true, controla_importe_superior_al_saldo);
    
    //
    // Consultar movimientos por cuenta.
    //
    
    List<Movimiento> movs = cm.getMovimientos();
    
    // Se realizaron 2 movimientos.
    assertEquals(2, movs.size());    
    
    //
    // Exportar movimientos a Excel.
    //
    
    String current_path = System.getProperty("user.dir");
    String file_path = current_path + "/movimientos.xlsx";
    
    try {
      cm.toExcel(cuenta, movs, file_path);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // Chequear creación del archivo excel.
    File file = new File(file_path);
    assertEquals(true, file.exists());
    
    // Eliminar movimientos.
    movDao.delete(mov);
    movDao.delete(mov_extraccion);
    
    // Eliminar cuenta.
    cuentaDao.delete(cuenta);    
    
    // Eliminar titular Y adherente.
    clienteDao.delete(adherente);
    clienteDao.delete(titular);
    
  }
  
}
