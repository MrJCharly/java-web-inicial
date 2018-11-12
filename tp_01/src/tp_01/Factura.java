package tp_01;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tp_02.RespInscripto;

public class Factura {
  // TP 01
  protected int id;
  protected int nro;
  protected String razon_social;
  protected Date fecha_emision;
  protected double importe;
  
  // TP 02
  public static double ALICUOTA_IVA = .21;
  
  protected Agente emisor;
  protected Agente cliente;
  protected double iva = 0;
  
  public Factura(int id, int nro, String razon_social, Date fecha_emision, double importe) {
    this.id = id;
    this.nro = nro;
    this.razon_social = razon_social;
    this.fecha_emision = fecha_emision;
    this.importe = importe;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    sb
      .append(this.getFormattedNro()).append(" - ")
      .append(this.getRazon_social()).append(" - ")
      .append(this.getFormattedFechaEmision()).append(" - ")
      .append(this.getImporte());
    
    return sb.toString();
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNro() {
    return nro;
  }

  public String getFormattedNro() {
    return String.format("%010d", this.nro);
  }
  
  public void setNro(int nro) {
    this.nro = nro;
  }

  public String getRazon_social() {
    return razon_social;
  }

  public void setRazon_social(String razon_social) {
    this.razon_social = razon_social;
  }

  public Date getFecha_emision() {
    return fecha_emision;
  }

  public String getFormattedFechaEmision() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    return formatter.format(this.fecha_emision);
  }
  
  public void setFecha_emision(Date fecha_emision) {
    this.fecha_emision = fecha_emision;
  }

  public int getAnioEmision() {
	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(this.fecha_emision);
	  
	  return calendar.get(Calendar.YEAR);
  }
  
  public double getImporte() {
    return importe;
  }

  public void setImporte(double importe) {
    this.importe = importe;
  }

  // TP 02
  
  public Factura(int id, int nro, Agente emisor, Agente cliente, Date fecha_emision, double importe) {
    this.id = id;
    this.nro = nro;
    this.emisor = emisor; // Reemplaza a razon_social.
    this.cliente = cliente;
    this.fecha_emision = fecha_emision;
    this.importe = importe;
  }
  
  public Agente getEmisor() {
    return emisor;
  }
  
  public void setEmisor(Agente emisor) {
    this.emisor = emisor;
  }
  
  public Agente getCliente() {
    return cliente;
  }
  
  // SetCliente no es conveniente en este caso, ya que cada subtipo 
  // de factura tiene restricciones diferentes para esa propiedad.
  
  /**
   * Cada subtipo debe indicar explícitamente si calcula el valor del iva.
   * @return Por defecto, no se calcula este valor sobre el total facturado.
   */
  public boolean calculaIva() {
    return false;
  }
  
  /**
   * Calcula el total facturado, incluyendo el iva si corresponde.
   * @return
   */
  public double calcularTotal() {
    this.iva = this.calculaIva() ? this.importe * Factura.ALICUOTA_IVA : 0;
    return this.importe + this.iva;
  }
}
