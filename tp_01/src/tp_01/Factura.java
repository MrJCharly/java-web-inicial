package tp_01;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Factura {
  private int id;
  private int nro;
  private String razon_social;
  private Date fecha_emision;
  private double importe;
  
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

}
