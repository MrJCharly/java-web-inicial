package tp_02;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import tp_01.Agente;
import tp_01.Factura;

public class UnitTest_TP_02 {
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
  
  @BeforeClass
  public void preset() {
    // Crear agentes como emisores y clientes de facturas.
  	
    this.respInsc1 = new RespInscripto("01-12345678-10", "Musimundo");
    this.respInsc2 = new RespInscripto("02-12345678-20", "Ribeiro");
    this.exento1 = new Exento("03-12345678-30", "Union Bus");
    this.exento2 = new Exento("04-12345678-40", "Santa Ana");
    this.monotributo1 = new Monotributista("05-12345678-50", "NeoTic Sistemas");
    this.monotributo2 = new Monotributista("06-12345678-60", "MajoraTic Sistemas");
    this.consfinal1 = new ConsFinal("Fernández", "Fernando");
    this.consfinal1 = new ConsFinal("Martínez", "Martín");
    
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
    this.fact2 = new FacturaA(2, 2, this.respInsc2, this.respInsc1, new Date(), 1000.5);
    
    // Factura B.
    
    // RespInscripto a Exento, Montributista y ConsFinal.
    this.fact3 = new FacturaB(3, 3, this.respInsc1, this.exento1, new Date(), 1000.5);
    this.fact4 = new FacturaB(4, 4, this.respInsc1, this.monotributo1, new Date(), 1000.5);
    this.fact5 = new FacturaB(5, 5, this.respInsc1, this.consfinal1, new Date(), 1000.5);
    
    // Factura C.
    
    // Emisor Monotributista a Agente.
    this.fact6 = new FacturaC(6, 6, this.monotributo1, this.respInsc1, new Date(), 1000.5);
    this.fact7 = new FacturaC(7, 7, this.monotributo1, this.exento1, new Date(), 1000.5);
    this.fact8 = new FacturaC(8, 8, this.monotributo1, this.monotributo2, new Date(), 1000.5);
    this.fact9 = new FacturaC(9, 9, this.monotributo1, this.consfinal1, new Date(), 1000.5);
    
    // Emisor Exento a Agente.
    this.fact10 = new FacturaC(10, 10, this.exento1, this.respInsc1, new Date(), 1000.5);
    this.fact11 = new FacturaC(11, 11, this.exento1, this.exento1, new Date(), 1000.5);
    this.fact12 = new FacturaC(12, 12, this.exento1, this.monotributo2, new Date(), 1000.5);
    this.fact13 = new FacturaC(13, 13, this.exento1, this.consfinal1, new Date(), 1000.5);
    
    // Agregar facturas a la colección.
    this.facturas .add(fact1);
  }

  @Test
  public void _01_crearFacturasYClientes() {
    // Probar contenido de agentes y facturas.
  	
  }

}
