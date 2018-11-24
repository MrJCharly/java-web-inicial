package tp_01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import tp_03.Producto;

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
  
  public List<Factura> searchNro(Factura[] facturas, int nro) {
    return this.search(new ArrayList<Factura>(Arrays.asList(facturas)), new NroCondition(nro));
  }
  
  public List<Factura> searchAnioEmision(Factura[] facturas, int anioEmision) {
    return this.search(new ArrayList<Factura>(Arrays.asList(facturas)), new AnioEmisionCondition(anioEmision));
  }
  
  /**
   * Buscar facturas por tipo.
   * @param facturas
   * @param tipo
   * @return Facturas del tipo indicado.
   */
  public List<Factura> searchTipo(List<Factura> facturas, String tipo) {
    return this.search(facturas, new TipoCondition(tipo));
  } 
  
  /**
   * Buscar facturas por id cliente.
   * @param facturas
   * @param idCliente
   * @return facturas pertenecientes al cliente indicado.
   */
  public List<Factura> searchByClienteId(List<Factura> facturas, int idCliente) {
    return this.search(facturas, new ClienteCondition(idCliente));
  }
  
  public List<Factura> search (List<Factura> facturas, Condition condition) {
    ArrayList<Factura> result = new ArrayList<Factura>();
    
    for (Factura factura : facturas) {
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

  public double calcularAcumulado(List<Factura> result) {
    double acumulado = 0;
    
    for (Factura factura: result) {
      acumulado += factura.getImporte();
    }
    
    return acumulado;
  }

  public List<Producto> getProductos(int n) {
    List<Producto> productos = new ArrayList<Producto>();
    
    for (int i = 0; i < n; i++) {
      productos.add(new Producto(i + 1, "PRD", "Producto", 100.5));
    }
    
    return productos;
  }

}

interface Condition {
  public boolean evaluate(Factura factura);
}

/**
 * Condición para determinar si una factura tiene un nro en particular.
 */
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

/**
 * COndición para determinar si una factura es de un anio en particular.
 */
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

/**
 * Condición para determinar si una factura es de un determinado tipo.
 */
class TipoCondition implements Condition {
  protected String tipo;
  
  public TipoCondition(String tipo) {
    this.tipo = tipo;
  }

  @Override
  /**
   * Evalúa el tipo de una factura.
   * @param factura a evaular
   * @return true si la factura es del tipo indicado.
   */
  public boolean evaluate(Factura factura) {
    return factura.getClass().getSimpleName().equals(this.tipo);
  }
}

/**
 * Condición para determinar si una factura es de un determinado tipo.
 */
class ClienteCondition implements Condition {
  protected int idCliente;
  
  public ClienteCondition(int idCliente) {
    this.idCliente = idCliente;
  }

  @Override
  /**
   * Evalúa el cliente de una factura.
   * @param factura a evaular
   * @return true si la factura pertenece al cliente indicado.
   */
  public boolean evaluate(Factura factura) {
    return factura.getCliente().getId() == this.idCliente;
  }
}