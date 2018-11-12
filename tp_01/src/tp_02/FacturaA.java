package tp_02;

import java.util.Date;

import tp_01.Factura;

public class FacturaA extends Factura {

  /**
   * Factura tipo A de RespInscripto a RespInscripto.
   * @param id
   * @param nro
   * @param emisor
   * @param cliente
   * @param fecha_emision
   * @param importe
   */
  public FacturaA(int id, int nro, RespInscripto emisor, RespInscripto cliente, Date fecha_emision, double importe) {
    super(id, nro, emisor, cliente, fecha_emision, importe);
  }

  @Override
  public boolean calculaIva() {
    return true;
  }
}
