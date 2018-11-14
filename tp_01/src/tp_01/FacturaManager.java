package tp_01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tp_02.FacturaA;

public class FacturaManager {
  
  /**
   * @param n La cantidad de facturas a crear.
   * @param nro El nro de la primera factura.
   * @return Array de facturas creadas.
   */
  public Factura[] createMany(int n, int nro) {
    Factura[] facturas = new Factura[n];
    int id = 1;
    String razon_social = "Razon Social";
    Date fecha_emision = new Date();
    double importe = 10.5d;
    
    for (int i = 0; i < n; i++) {
      facturas[i] = new Factura(id++, nro++, razon_social, fecha_emision, importe);
    }
    
    return facturas;
  }
  
  public ArrayList<Factura> searchNro(Factura[] facturas, int nro) {
    return this.search(facturas, new NroCondition(nro));
  }
  
  public ArrayList<Factura> searchAnioEmision(Factura[] facturas, int anioEmision) {
    return this.search(facturas, new AnioEmisionCondition(anioEmision));
  }
  
  public ArrayList<Factura> search (Factura[] facturas, Condition condition) {
    ArrayList<Factura> result = new ArrayList<Factura>();
    Factura factura = null;
    
    for (int i = 0; i < facturas.length; i++) {
      factura = facturas[i];
      
      if (condition.evaluate(factura)) {
        result.add(factura);
      }
    }
    
    return result;
  }

  /**
   * Crear facturas a partir de un conjunto de fechas. 
   * @param dates
   * @return
   * @throws ParseException 
   */
  public Factura[] createManyWithDates(String[] dates) throws ParseException {
	Factura[] facturas = new Factura[dates.length];
	Date date = null;
	
	for (int i = 0; i < dates.length; i++) {
	  date = new SimpleDateFormat("dd/MM/yyyy").parse(dates[i]);	
	  facturas[i] = new Factura(i + 1, i +1, "RS", date, 100.5);	
	}
	
	return facturas;
  }

  /**
   * Buscar facturas por tipo.
   * @param name Tipo de factura buscado.
   * @return
   */
  public List<Factura> searchFacturas(List<Factura> facturas, String tipo) {
    ArrayList<Factura> result = new ArrayList<Factura>();
    
    for (Factura factura : facturas) {
      if (factura.getClass().getSimpleName().equals(tipo)) {
        result.add(factura);
      }
    }
    
    return result;
  }

  public double calcularAcumulado(List<Factura> result) {
    double acumulado = 0;
    
    for (Factura factura: result) {
      acumulado += factura.getImporte();
    }
    
    return acumulado;
  }
}

interface Condition {
  public boolean evaluate(Factura factura);
}

class NroCondition implements Condition {
  protected int nro;
  
  public NroCondition(int nro) {
    this.nro = nro;
  }
  
  @Override
  public boolean evaluate(Factura factura) {
    return factura.getNro() == nro;
  }
}

class AnioEmisionCondition implements Condition {
  protected int anioEmision;
  
  public AnioEmisionCondition(int anioEmision) {
    this.anioEmision = anioEmision;
  }
  
  @Override
  public boolean evaluate(Factura factura) {
    return factura.getAnioEmision() == this.anioEmision;
  }
}