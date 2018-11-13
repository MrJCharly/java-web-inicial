package tp_02;

import java.util.Date;

import tp_01.Factura;

public class FacturaB extends Factura {

	// RespInscripto a Exento.
	public FacturaB(int id, int nro, RespInscripto emisor, Exento cliente, Date fecha_emision, double importe) {
		super(id, nro, emisor, cliente, fecha_emision, importe);
	}

	// RespInscripto a Monotributista.
	public FacturaB(int id, int nro, RespInscripto emisor, Monotributista cliente, Date fecha_emision, double importe) {
		super(id, nro, emisor, cliente, fecha_emision, importe);
	}
	
	// RespInscripto a ConsFinal.
	public FacturaB(int id, int nro, RespInscripto emisor, ConsFinal cliente, Date fecha_emision, double importe) {
		super(id, nro, emisor, cliente, fecha_emision, importe);
	}
	
}
