package tp_01;

public class Agente {
  protected int id;
  protected String cuit;
  protected String razon_social;
  
  public Agente (int id, String cuit, String razon_social) {
    this.id = id;
    this.cuit = cuit;
    this.razon_social = razon_social;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCuit() {
    return cuit;
  }

  public void setCuit(String cuit) {
    this.cuit = cuit;
  }

  public String getRazon_social() {
    return razon_social;
  }

  public void setRazon_social(String razon_social) {
    this.razon_social = razon_social;
  }

  
}
