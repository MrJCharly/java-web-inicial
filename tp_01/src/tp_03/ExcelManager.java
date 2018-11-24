package tp_03;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tp_01.Factura;

public class ExcelManager {

  public void export(Factura factura, String file_path) throws IOException {
    XSSFWorkbook wb = new XSSFWorkbook();
    XSSFSheet sheet = wb.createSheet("Hoja 1");
    XSSFRow row;
    
    // Nro.
    row = sheet.createRow(0);
    row.createCell(0).setCellValue("Nro");
    row.createCell(1).setCellValue(factura.getFormattedNro());
    
    // Fecha.
    row = sheet.createRow(1);
    row.createCell(0).setCellValue("Fecha");
    row.createCell(1).setCellValue(factura.getFormattedFechaEmision());
    
    // Razón social.
    row = sheet.createRow(2);
    row.createCell(0).setCellValue("Razón");
    row.createCell(1).setCellValue(factura.getRazon_social());

    // Items.
    row = sheet.createRow(4);
    row.createCell(0).setCellValue("Producto");
    row.createCell(1).setCellValue("PU");
    row.createCell(2).setCellValue("Cantidad");
    
    int count = factura.getItems() != null ? factura.getItems().size() : 0;
    Item item;
    
    for (int i = 0; i < count; i++) {
      item = factura.getItems().get(i);
      row = sheet.createRow(i + 5);
      row.createCell(0).setCellValue(item.getProducto().getDescripcion());
      row.createCell(1).setCellValue(item.getPrecioUnitario());
      row.createCell(2).setCellValue(item.getCantidad());
    }
    
    for (int i = 0; i < 2; i++) {
      sheet.autoSizeColumn(i);
    }
    
    File file = new File(file_path);
    FileOutputStream outputStream = new FileOutputStream(file);
    wb.write(outputStream);
    wb.close();
  }

}
