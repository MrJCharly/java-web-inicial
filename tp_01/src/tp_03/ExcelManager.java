package tp_03;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tp_01.Factura;

public class ExcelManager {

  public void export(Factura factura, String file_path) throws IOException {
    XSSFWorkbook wb = new XSSFWorkbook();
    XSSFSheet sheet = wb.createSheet("Hoja 1");
    XSSFRow row;
    Cell cell;
    
    // Nro.
    row = sheet.createRow(0);
    this.createTitleCell(wb, row, 0, "Nro");
    row.createCell(1).setCellValue(factura.getFormattedNro());
    
    // Fecha.
    row = sheet.createRow(1);
    this.createTitleCell(wb, row, 0, "Fecha");
    row.createCell(1).setCellValue(factura.getFormattedFechaEmision());
    
    // Razón social.
    row = sheet.createRow(2);
    this.createTitleCell(wb, row, 0, "Razón Social");
    row.createCell(1).setCellValue(factura.getRazon_social());

    // Items.
    row = sheet.createRow(4);
    this.createTitleCell(wb, row, 0, "Producto");
    this.createTitleCell(wb, row, 1, "PU");
    this.createTitleCell(wb, row, 2, "Cantidad");
    this.createTitleCell(wb, row, 3, "Subtotal");
    
    int count = factura.getItems() != null ? factura.getItems().size() : 0;
    Item item;
    
    for (int i = 0; i < count; i++) {
      item = factura.getItems().get(i);
      row = sheet.createRow(i + 5);
      row.createCell(0).setCellValue(item.getProducto().getDescripcion());
      this.createCurrencyCell(wb, row, 1, item.getPrecioUnitario());
      row.createCell(2).setCellValue(item.getCantidad());
      this.createCurrencyCell(wb, row, 3, item.getSubtotal());
    }
    
    // Total.
    row = sheet.createRow(4 + count + 1);
    this.createTitleCell(wb, row, 0, "Total");
    this.createCurrencyCell(wb, row, 3, factura.calcularTotal());
    
    for (int i = 0; i < 2; i++) {
      sheet.autoSizeColumn(i);
    }
    
    File file = new File(file_path);
    FileOutputStream outputStream = new FileOutputStream(file);
    wb.write(outputStream);
    wb.close();
  }

  // Crear celda con formato de título.
  private void createTitleCell(XSSFWorkbook wb, XSSFRow row, int column, String title) {
    Cell cell;
    XSSFFont font = wb.createFont();
    CellStyle style = wb.createCellStyle();
    
    cell = row.createCell(column);
    font.setFontHeightInPoints((short)10);
    font.setBold(true);
    font.setItalic(false);
    style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setFont(font);
    
    cell.setCellValue(title);
    cell.setCellStyle(style);
  }

  // Crear celda con formato moneda.
  private void createCurrencyCell(XSSFWorkbook wb, XSSFRow row, int column, double value) {
    CellStyle style = wb.createCellStyle();
    style.setDataFormat((short)0x7);
    Cell cell;
    
    cell = row.createCell(column);
    cell.setCellValue(value);
    cell.setCellStyle(style);
  }

}
