package tp_03;

public class Producto {
  protected int id;
  protected String codigo;
  protected String descripcion;
  protected double precioUnitario;
  
  public Producto(int id, String codigo, String descripcion, double precioUnitario) {
    super();
    this.id = id;
    this.codigo = codigo;
    this.descripcion = descripcion;
    this.precioUnitario = precioUnitario;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public double getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }
  
  
}
