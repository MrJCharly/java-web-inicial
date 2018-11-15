package tp_02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tp_01.Agente;
import tp_01.Factura;
import tp_01.FacturaManager;

public class UnitTest {
  protected FacturaManager fm = new FacturaManager();
  protected List<Agente> agentes = new ArrayList<Agente>();
  protected List<Factura> facturas = new ArrayList<Factura>();
  
  protected RespInscripto respInsc1;
  protected RespInscripto respInsc2;
  protected Exento exento1;
  protected Exento exento2;
  protected Monotributista monotributo1;
  protected Monotributista monotributo2;
  protected ConsFinal consfinal1;
  protected ConsFinal consfinal2;
  
  protected FacturaA fact1;
  protected FacturaA fact2;
  protected FacturaB fact3;
  protected FacturaB fact4;
  protected FacturaB fact5;
  protected FacturaC fact6;
  protected FacturaC fact7;
  protected FacturaC fact8;
  protected FacturaC fact9;
  protected FacturaC fact10;
  protected FacturaC fact11;
  protected FacturaC fact12;
  protected FacturaC fact13;
  
  @Before
  public void crearAgentesYFacturas() {
    // Crear agentes como emisores y clientes de facturas.
  	
    this.respInsc1 = new RespInscripto(1, "01-12345678-10", "Musimundo");
    this.respInsc2 = new RespInscripto(2, "02-12345678-20", "Ribeiro");
    this.exento1 = new Exento(3, "03-12345678-30", "Union Bus");
    this.exento2 = new Exento(4, "04-12345678-40", "Santa Ana");
    this.monotributo1 = new Monotributista(5, "05-12345678-50", "NeoTic Sistemas");
    this.monotributo2 = new Monotributista(6, "06-12345678-60", "MajoraTic Sistemas");
    this.consfinal1 = new ConsFinal(7, "Fernández", "Fernando");
    this.consfinal1 = new ConsFinal(8, "Martínez", "Martín");
    
    // Agregar a la colección.
    
    this.agentes.add(this.respInsc1);
    this.agentes.add(this.respInsc2);
    this.agentes.add(this.exento1);
    this.agentes.add(this.exento2);
    this.agentes.add(this.monotributo1);
    this.agentes.add(this.monotributo2);
    this.agentes.add(this.consfinal1);
    this.agentes.add(this.consfinal2);
    
    // Crear facturas.
    
    // Factura A.
    
    // RespInscripto a RespInscripto.
    this.fact1 = new FacturaA(1, 1, this.respInsc1, this.respInsc2, new Date(), 1000.5);
    this.fact2 = new FacturaA(2, 2, this.respInsc2, this.respInsc1, new Date(), 1500.5);
    
    // Factura B.
    
    // RespInscripto a Exento, Montributista y ConsFinal.
    this.fact3 = new FacturaB(3, 3, this.respInsc1, this.exento1, new Date(), 2000.5);
    this.fact4 = new FacturaB(4, 4, this.respInsc1, this.monotributo1, new Date(), 2500.5);
    this.fact5 = new FacturaB(5, 5, this.respInsc1, this.consfinal1, new Date(), 3000.5);
    
    // Factura C.
    
    // Emisor Monotributista a Agente.
    this.fact6 = new FacturaC(6, 6, this.monotributo1, this.respInsc1, new Date(), 3500.5);
    this.fact7 = new FacturaC(7, 7, this.monotributo1, this.exento1, new Date(), 4000.5);
    this.fact8 = new FacturaC(8, 8, this.monotributo1, this.monotributo2, new Date(), 4500.5);
    this.fact9 = new FacturaC(9, 9, this.monotributo1, this.consfinal1, new Date(), 5000.5);
    
    // Emisor Exento a Agente.
    this.fact10 = new FacturaC(10, 10, this.exento1, this.respInsc1, new Date(), 5500.5);
    this.fact11 = new FacturaC(11, 11, this.exento1, this.exento1, new Date(), 6000.5);
    this.fact12 = new FacturaC(12, 12, this.exento1, this.monotributo2, new Date(), 6500.5);
    this.fact13 = new FacturaC(13, 13, this.exento1, this.consfinal1, new Date(), 7000.5);
    
    // Agregar facturas a la colección.
    this.facturas .add(fact1);
    this.facturas .add(fact2);
    this.facturas .add(fact3);
    this.facturas .add(fact4);
    this.facturas .add(fact5);
    this.facturas .add(fact6);
    this.facturas .add(fact7);
    this.facturas .add(fact8);
    this.facturas .add(fact9);
    this.facturas .add(fact10);
    this.facturas .add(fact11);
    this.facturas .add(fact12);
    this.facturas .add(fact13);
  }

  @Test
  public void _01_crearFacturasYClientes() {
    // Probar contenido de agentes y facturas.
  	assertEquals(8, this.agentes.size());
  	assertEquals(13, this.facturas.size());
  }

  @Test
  public void _02_mostrarFacturasA() {
    List<Factura> result;
    double acumulado;
    
    // Realizar test sobre FacturaA.
    result = fm.searchTipo(this.facturas, FacturaA.class.getSimpleName());
    
    // Deben obtenerse 2 resultados, con ids 1 y 2.
    assertEquals(2, result.size());
    assertEquals(1, result.get(0).getId());
    assertEquals(2, result.get(1).getId());
    
    // Acumulado
    acumulado = this.fact1.getImporte() + this.fact2.getImporte();  
    assertEquals(acumulado, fm.calcularAcumulado(result));
    
    // Realizar test sobre FacturaC.
    result = fm.searchTipo(this.facturas, FacturaC.class.getSimpleName());
    
    // Debe obtenerse 8 resultados, con ids 6 a 13.
    assertEquals(8, result.size());
    
    for (int i = 0; i < 8; i++) {
      assertEquals(i + 6, result.get(i).getId());
    }
    
    // Acumulado
    acumulado = 
      this.fact6.getImporte() + this.fact7.getImporte() + this.fact8.getImporte() + 
      this.fact9.getImporte() + this.fact10.getImporte() + this.fact11.getImporte() +
      this.fact12.getImporte() + this.fact13.getImporte();
    assertEquals(acumulado, fm.calcularAcumulado(result));
  }
  
  @Test
  public void _03_buscarFacturasPorCliente() {
    List<Factura> result;
    int id = this.respInsc1.getId();
    
    // Buscar facturas del cliente respInscripto1.
    result = this.fm.searchByClienteId(this.facturas, this.respInsc1.getId());
    
    // Debe obtenerse 3 resultados.
    assertEquals(3, result.size());
    
    // El id del cliente en cada resultado debe ser id.
    for (Factura factura : result) {
      assertEquals(id, factura.getCliente().getId());
    }
    
    // Acumulado
    double acumulado = this.fact2.getImporte() + this.fact6.getImporte() + this.fact10.getImporte();  
    assertEquals(acumulado, this.fm.calcularAcumulado(result));
  }
}
