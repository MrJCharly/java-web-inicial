package tp_02;

import java.util.Date;

import tp_01.Agente;
import tp_01.Factura;

public class FacturaC extends Factura {
	
	// Monotributista a Agente.
	public FacturaC(int id, int nro, Monotributista emisor, Agente cliente, Date fecha_emision, double importe) {
		super(id, nro, emisor, cliente, fecha_emision, importe);
	}

	// Exento a Agente.
	public FacturaC(int id, int nro, Exento emisor, Agente cliente, Date fecha_emision, double importe) {
		super(id, nro, emisor, cliente, fecha_emision, importe);
	}
	
}
