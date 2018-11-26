package tp_04;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tp_01.Factura;
import tp_01.FacturaManager;
import tp_03.ExcelManager;
import tp_03.Item;
import tp_03.Producto;

public class UnitTest {
	@Test
	void _01_CrearExcepcionPersonalizada() {
		FacturaManager fm = new FacturaManager();
		ExcelManager em = new ExcelManager();
	  String current_path = System.getProperty("user.dir");
	  String file_path = current_path + "/factura.xlsx";
	  Factura[] facturas = fm.createMany(1, 1);
	  Factura factura = facturas[0];
	  List<Item> items = new ArrayList<Item>();
	  boolean factura_sin_items_detectada = false;
	  
	  // Cargar lista vacía de ítems.
	  factura.setItems(items);
	  
	  // Intentar generar excel.
	  try {
			em.export(factura, file_path);
		} catch (IOException e) { 
			// Atender IOException.
		} catch (FacturaSinItemsException fsie) {
			factura_sin_items_detectada = true;
		}
	  
	  // Chequear lanzamiento de FacturaSinItemsException. 
	  assertTrue(factura_sin_items_detectada);
	}
}
