package tp_final;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import ar.edu.unju.virtual.model.dao.impl.ClienteDaoImpl;
import ar.edu.unju.virtual.model.domain.Cliente;

public class UnitTest {

  @Test
  public void test() {
    ClienteDaoImpl clienteDao = new ClienteDaoImpl();
    List<Cliente> clientes = clienteDao.findAll();
    
    assertEquals(6, clientes.size());
  }

}
