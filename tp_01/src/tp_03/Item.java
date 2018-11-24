package tp_03;

public class Item {
  protected int id;
  protected Producto producto;
  protected int cantidad;
  
  public Item(int id, Producto producto, int cantidad) {
    super();
    this.id = id;
    this.producto = producto;
    this.cantidad = cantidad;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Producto getProducto() {
    return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public double getPrecioUnitario() {
    return this.producto != null ? this.producto.getPrecioUnitario() : 0;  
  }

  public double getSubtotal() {
    return this.getPrecioUnitario() * this.cantidad;
  }
  
  
}
