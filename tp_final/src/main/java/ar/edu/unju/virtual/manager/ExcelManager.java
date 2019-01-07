package ar.edu.unju.virtual.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ar.edu.unju.virtual.model.domain.Cuenta;
import ar.edu.unju.virtual.model.domain.Movimiento;

public class ExcelManager {

  public void export(Cuenta cuenta, List<Movimiento> movs, String file_path) throws IOException {        
    XSSFWorkbook wb = new XSSFWorkbook();
    XSSFSheet sheet = wb.createSheet("Hoja 1");
    XSSFRow row;
    
    // Título.
    row = sheet.createRow(0);
    this.createTitleCell(wb, row, 0, "Movimientos de cuenta bancaria");  
    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
    
    // Cuenta.
    row = sheet.createRow(1);
    this.createTitleCell(wb, row, 0, "Cuenta");
    row.createCell(1).setCellValue(cuenta.getNumero());
    
    // Titular.
    row = sheet.createRow(2);
    this.createTitleCell(wb, row, 0, "Titular");
    row.createCell(1).setCellValue(cuenta.getCliente().getNombre());

    // Movimientos.
    row = sheet.createRow(4);
    this.createTitleCell(wb, row, 0, "Fecha");
    this.createTitleCell(wb, row, 1, "Cliente");
    this.createTitleCell(wb, row, 2, "Débito");
    this.createTitleCell(wb, row, 3, "Crédito");
    this.createTitleCell(wb, row, 4, "Saldo");
    
    int count = movs.size();
    Movimiento mov;
    
    for (int i = 0; i < count; i++) {
      mov = movs.get(i);
      row = sheet.createRow(i + 6);
      
      row.createCell(0).setCellValue(getFormattedDate(mov.getFecha()));
      row.createCell(1).setCellValue(mov.getCliente().getNombre());
      this.createCurrencyCell(wb, row, 2, mov.getDebito());
      this.createCurrencyCell(wb, row, 3, mov.getCredito());
      this.createCurrencyCell(wb, row, 4, mov.getSaldo());
    }
        
    // Ajustar tamaño de columnas.
    for (int i = 0; i < 2; i++) {
      sheet.autoSizeColumn(i);
    }
    
    // Exportar.
    File file = new File(file_path);
    FileOutputStream outputStream = new FileOutputStream(file);
    wb.write(outputStream);
    wb.close();
  }

  private String getFormattedDate(Date fecha) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");    
    return formatter.format(fecha);
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
