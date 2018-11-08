package tp_01;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

public class UnitTest {
  private FacturaManager fm = new FacturaManager();
  
  @Test
  public void _01_crearNFacturas() {
    // Tests con diferentes combinaciones para n y nro.
    this.testCrearFacturas(10, 1);
    this.testCrearFacturas(1, 264);
    this.testCrearFacturas(120, 25);
    this.testCrearFacturas(250, 550);
  }
  
  /**
   * @param n Cantidad de facturas creadas.
   * @param nro Nro de la primera factura.
   */
  private void testCrearFacturas(int n, int nro) {
    Factura[] facturas = this.fm.createMany(n, nro);
    
    // Comprobar cantidad de facturas creadas.
    assertEquals(n, facturas.length);
    
    Factura primera = facturas[0];
    Factura ultima = facturas[n - 1];
    
    // Comprobar id consecutivo de facturas:
    // El id de la primera debe ser igual a 1.
    // El id de la última debe ser igual a n.
    assertEquals(1, primera.getId());
    assertEquals(n, ultima.getId());
    
    // Comprobar nro consecutivo de facturas:
    // El nro de la última factura debe ser igual al nro de la primera + n - 1.
    assertEquals(primera.getNro() + n - 1, ultima.getNro());
  }
  
  @Test
  public void testToString() {
    Factura factura1 = new Factura(1, 1, "RS", new Date(), 100.5);
    Factura factura2 = new Factura(1, 10, "RS", new Date(), 100.5);
    Factura factura3 = new Factura(1, 150, "RS", new Date(), 100.5);
    Factura factura4 = new Factura(1, 1000000000, "RS", new Date(), 100.5);
    
    assertEquals("0000000001", factura1.getFormattedNro());
    assertEquals("0000000010", factura2.getFormattedNro());
    assertEquals("0000000150", factura3.getFormattedNro());
    assertEquals("1000000000", factura4.getFormattedNro());
  }
  
  @Test
  public void _02_sobrescribirToString() {
    String date_string = "12/05/2018";
    Date date = null;
    
    try {
      date = new SimpleDateFormat("dd/MM/yyyy").parse(date_string);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    
    Factura factura1 = new Factura(1, 10, "UNJu", date, 1000.5);
    Factura factura2 = new Factura(1, 450, "RS", date, 20.45);
    
    assertEquals("0000000010 - UNJu - 12/05/2018 - 1000.5", factura1.toString());
    assertEquals("0000000450 - RS - 12/05/2018 - 20.45", factura2.toString());
  }
  
  @Test
  public void _03_buscarFacturaPorNro() {
    assertTrue(true);
  }
  
  @Test
  public void _04_buscarFacturaPorFecha() {
    assertTrue(true);
  }
}
