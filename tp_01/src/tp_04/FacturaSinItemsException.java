package tp_04;

@SuppressWarnings("serial")
public class FacturaSinItemsException extends Exception {
	public FacturaSinItemsException() {
		super("No es posible generar archivo para facturas sin ítems.");
	}
}