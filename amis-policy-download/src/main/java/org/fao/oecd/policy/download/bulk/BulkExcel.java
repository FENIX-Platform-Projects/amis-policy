package org.fao.oecd.policy.download.bulk;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class BulkExcel {
    private enum Styles{
        bold, normal, centerNormal, centerBold, date
    }
    Map<Styles,CellStyle> stylesMap = new HashMap<>();

    public File createExcel(Iterator<Object[]> data, File destination, String title) throws Exception {
        //Create sheet
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            HSSFSheet sheet = wb.createSheet();
            //Create styles
            initStyles(wb);
            //Create sheet header
            Object[] headerData = data.next();
            createHeader(sheet, title, headerData);
            //Create sheet data section
            int rangeHeight = createData(sheet, data);
            //Create border
            int rangeWidth = headerData.length;
            CellRangeAddress range = new CellRangeAddress(0,rangeHeight-1, 0,rangeWidth-1);
            RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, range, sheet, wb);
            RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, range, sheet, wb);
            RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, range, sheet, wb);
            RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, range, sheet, wb);
            RegionUtil.setBottomBorderColor(IndexedColors.BLACK.getIndex(), range, sheet, wb);
            RegionUtil.setTopBorderColor(IndexedColors.BLACK.getIndex(), range, sheet, wb);
            RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), range, sheet, wb);
            RegionUtil.setRightBorderColor(IndexedColors.BLACK.getIndex(), range, sheet, wb);
            //resize columns
            sheet.setColumnWidth(0, 5000);
            for (int i=1; i<rangeHeight; i++)
                sheet.autoSizeColumn(i);

            //Create file
            FileOutputStream outputStream = new FileOutputStream(destination);
            try {
                wb.write(outputStream);
            } finally {
                outputStream.close();
            }
        } finally {
            wb.close();
        }
        return destination;
    }


    //Excel building methods

    private void createHeader (HSSFSheet sheet, String title, Object[] headerData) {
        Row row = sheet.createRow(0);
        newCell(row,0,Styles.bold).setCellValue("Source:");
        newCell(row,1,Styles.normal).setCellValue("AMIS Policy Database");

        row = sheet.createRow(1);
        newCell(row,0,Styles.bold).setCellValue("Date of Download:");
        newCell(row,1,Styles.date).setCellValue(new Date());

        row = sheet.createRow(3);
        newCell(row,0,Styles.bold).setCellValue(title);

        row = sheet.createRow(4);
        newCell(row,0,Styles.bold).setCellValue("Period");
        newCell(row,1,Styles.bold).setCellValue("Policy Type");
        newCell(row,2,Styles.bold).setCellValue("Sum of AMIS countries");
        for (int i=3; i<headerData.length; i++)
            newCell(row,i,Styles.centerBold).setCellValue((String)headerData[i]);
    }

    private int createData (HSSFSheet sheet, Iterator<Object[]> data) {
        int r=5;
        for (; data.hasNext(); r++) {
            Object[] dataRow = data.next();

            Row row = sheet.createRow(r);
            newCell(row,0,Styles.normal).setCellValue(formatMonthDate((Integer)dataRow[0]));
            newCell(row,1,Styles.normal).setCellValue((String)dataRow[1]);
            for (int i=2; i<dataRow.length; i++)
                newCell(row,i,Styles.centerNormal).setCellValue((Integer)dataRow[i]);
        }
        return r;
    }



    //Utils
    private Cell newCell(Row row, int index, Styles style) {
        Cell cell = row.createCell(index);
        cell.setCellStyle(stylesMap.get(style));
        return cell;
    }

    private void initStyles(HSSFWorkbook wb) {
        CellStyle style;
        //Fonts
        Font fontBold = wb.createFont();
        fontBold.setFontHeightInPoints((short)9);
        fontBold.setFontName("Arial");
        fontBold.setBold(true);
        Font fontNormal = wb.createFont();
        fontNormal.setFontHeightInPoints((short)9);
        fontNormal.setFontName("Arial");
        //Styles
        stylesMap.put(Styles.normal, style = wb.createCellStyle());
        style.setFont(fontNormal);

        stylesMap.put(Styles.centerNormal, style = wb.createCellStyle());
        style.setFont(fontNormal);
        style.setAlignment(CellStyle.ALIGN_CENTER);

        stylesMap.put(Styles.bold, style = wb.createCellStyle());
        style.setFont(fontBold);

        stylesMap.put(Styles.centerBold, style = wb.createCellStyle());
        style.setFont(fontBold);
        style.setAlignment(CellStyle.ALIGN_CENTER);

        stylesMap.put(Styles.date, style = wb.createCellStyle());
        style.setFont(fontNormal);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setDataFormat(wb.getCreationHelper().createDataFormat().getFormat("dd/mm/yyyy"));
    }

    private String formatMonthDate(Integer date) {
        if (date!=null) {
            int year = date/100;
            switch (date%100) {
                case 1: return "Jan "+year;
                case 2: return "Feb "+year;
                case 3: return "Mar "+year;
                case 4: return "Apr "+year;
                case 5: return "May "+year;
                case 6: return "Jun "+year;
                case 7: return "Jul "+year;
                case 8: return "Aug "+year;
                case 9: return "Sep "+year;
                case 10: return "Oct "+year;
                case 11: return "Nov "+year;
                case 12: return "Dec "+year;
            }
        }
        return null;
    }


}
