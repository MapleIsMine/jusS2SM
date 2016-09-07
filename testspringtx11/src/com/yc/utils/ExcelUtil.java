package com.yc.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Component;

@Component("excelUtil")
public class ExcelUtil {
	/**
	 * 
	 * @param list:
	 *            数据
	 * @param titles :用于规定这个数据的显示顺序
	 * @param map:  数据显示顺序与数据的对应关系
	 * @param output : 输出位置
	 * @throws IOException
	 */
	public  void createNewExcel(List<Map<String,String>> list, String columnTitles, Map<String,String> map, OutputStream output) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle style = workbook.createCellStyle();
		// 在excel中新建一个工作表，名字为jsp
		HSSFSheet sheet = workbook.createSheet("oprecord");
		/**
		 * 设置其它数据 设置风格
		 */
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单无格的边框为粗体
		style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setWrapText(true);// 文本区域随内容多少自动调整

		int cellNumber = 0;
		int rowNumber =0;
		if(  list!=null&& list.size()>0){
			cellNumber=list.get(0).size();  
			rowNumber= list.size();
		}
		
		// System.out.println("行数======" + rowNumber);
		// System.out.println("列数======" + cellNumber);
		// 创建第一行,第一行用于生成列名
		HSSFRow row1 = sheet.createRow(0);
		String[] title = columnTitles.split(",");
		for (short i = 0; i < title.length; i++) {
			HSSFCell cell = row1.createCell(i);
			String celltitle=title[i];   //  编号, 姓名
			cell.setCellValue(celltitle);
			cell.setCellStyle(style);
		}

		// 创建第一列
		//Map firstMap = list.get(0);
		//Iterator it = firstMap.keySet().iterator();
		for (int rown = 0; rown < rowNumber; rown++) {
			HSSFRow row = sheet.createRow(rown + 1);  //创建一个新行
			Map cellMap = (Map) list.get(rown);       //从list中取出这一行的数据
			for (short celln = 0; celln < cellNumber; celln++) {
				HSSFCell cell = row.createCell(celln);      //创建一次列
				String celltitle=title[celln];   //  编号, 姓名    //按顺序取列名
				String mapkey=(String) map.get(celltitle);   //根据列名取  英文键名
				String value=(String) cellMap.get(mapkey);   //根据英文键名取值
				cell.setCellValue( value );
				cell.setCellStyle(style);
			}
		}
		output.flush();
		workbook.write(output);
		output.close();
	}
	
	
//	public static void main(String[] args) throws IOException{
//	List<Map> list=new ArrayList<Map>();
//	Map<String,String> m=new HashMap<String,String>();
//	m.put("id", "1");
//	m.put("name","a");
//	list.add(m);
//	
//	Map<String,String> m2=new HashMap<String,String>();
//	m2.put("id", "2");
//	m2.put("name","ab");
//	list.add(m2);
//	
//	Map<String,String> map=new HashMap<String,String>();
//	map.put("编号", "id");
//	map.put("姓名", "name");
//	
//	FileOutputStream fos=new FileOutputStream(  new File("a.xls"));
//	
//	ExcelUtil eu=new ExcelUtil();
//	eu.createNewExcel(  list,"编号,姓名",map, fos);
//}
}
