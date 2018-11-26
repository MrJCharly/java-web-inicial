package tp_03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tp_01.Factura;
import tp_01.FacturaManager;
import tp_04.FacturaSinItemsException;

class UnitTest {
  protected FacturaManager fm = new FacturaManager();

	@Test
	void _01_AgregarItems() {
	  List<Producto> productos = fm.getProductos(3);
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
	  factura.setItems(items);
	  
	  // Chequear total.
	  assertEquals(total, factura.calcularTotal());
	}

	@Test
	void _02_exportarFacturaAExcel() {
	  ExcelManager em = new ExcelManager();
	  List<Producto> productos = fm.getProductos(3);
	  String current_path = System.getProperty("user.dir");
	  String file_path = current_path + "/factura.xlsx";
	  Factura[] facturas = fm.createMany(1, 1);
	  Factura factura = facturas[0];
	  List<Item> items = new ArrayList<Item>();
	  
	  // Crear items.
    items.add(new Item(1, productos.get(0), 1));
    items.add(new Item(2, productos.get(1), 2));
    items.add(new Item(3, productos.get(2), 3));
	  
    factura.setItems(items);
    
	  try {
      em.export(factura, file_path);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (FacturaSinItemsException fsie) {
    	fsie.printStackTrace();
    }
	  
	  // Chequear creación del archivo.
	  File file = new File(file_path);
	  assertEquals(true, file.exists());
	  //System.out.println(current_path);
	}
}
