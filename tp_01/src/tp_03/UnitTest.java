package tp_03;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tp_01.Factura;
import tp_01.FacturaManager;

class UnitTest {
  protected FacturaManager fm = new FacturaManager();

	@Test
	void _01_AgregarItems() {
	  List<Producto> productos = fm.getProductos();
	  List<Item> items = new ArrayList<Item>();
	  Factura[] facturas = fm.createMany(1, 1);
	  Factura factura = facturas[0];
	  
	  // Crear items.
	  items.add(new Item(1, productos.get(0), 1));
	  items.add(new Item(2, productos.get(1), 2));
	  items.add(new Item(3, productos.get(2), 3));
	  
	  // Chequear totales y subtotales.
	  double total = 0;
	  double subtotal = 0;
	  
	  for (Item item : items) {
      subtotal = item.getPrecioUnitario() * item.getCantidad();
      total += subtotal;
      assertEquals(subtotal, item.getSubtotal());
    }
	  
	  // Asignar items a la factura.
	  factura.addItems(items);
	  
	  // Chequear total.
	  assertEquals(total, factura.calcularTotal());
	}

}
