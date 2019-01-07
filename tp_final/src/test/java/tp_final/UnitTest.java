package tp_final;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import ar.edu.unju.virtual.model.dao.impl.ClienteDaoImpl;
import ar.edu.unju.virtual.model.dao.impl.CuentaDaoImpl;
import ar.edu.unju.virtual.model.domain.Cliente;
import ar.edu.unju.virtual.model.domain.Cuenta;

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
    Date fecha = new Date();
    
    // Crear titular.
    clienteDao.create(titular);
    
    // Crear cuenta.
    Cuenta cuenta = new Cuenta(titular, "1111111111", fecha, 10000f, 3000l, Cuenta.HABILITADO);
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
  
  
}
