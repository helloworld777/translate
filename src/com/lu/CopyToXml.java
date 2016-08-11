package com.lu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CopyToXml extends JFrame {
	static List<String> chines = new ArrayList<>();
	static List<String> chines2 = new ArrayList<>();
	static List<String> yingyu = new ArrayList<>();
	static List<String> riyu = new ArrayList<>();
	static List<String> hanyu = new ArrayList<>();
	static List<String> eyu = new ArrayList<>();
	static List<String> fayu = new ArrayList<>();
	static List<String> deyu = new ArrayList<>();
	static List<String> yidali = new ArrayList<>();
	static List<String> putao = new ArrayList<>();
	static List<String> xibanya = new ArrayList<>();
	static List<String> xiong = new ArrayList<>();
	static List<String> bo = new ArrayList<>();
	static List<List<String>> language = new ArrayList<>();
	static List<String> names;
	JPanel jp1, jp2, jp3;//
	JLabel jlb1, jlb2, jlb3, jlb4;//
	JButton jb1, jb2, jbtnTran;//
	JTextField jtf;//
	JPasswordField jpf;//
	static Map<String, String> country = new HashMap<>();

//	public static void init() {
//		language.add(chines);
//		language.add(chines2);
//		language.add(yingyu);
//		language.add(riyu);
//		language.add(hanyu);
//		language.add(eyu);
//		language.add(fayu);
//		language.add(deyu);
//		language.add(yidali);
//		language.add(putao);
//		language.add(xibanya);
//		language.add(xiong);
//		language.add(bo);
//
//		// country.put("中文", "zh");
//		country.put("英语", "en");
//		// country.put("粤语", "yue");
//		// country.put("wyw", "文言文");
//		// country.put("日语", "jp");
//		// country.put("韩语", "kor");
//		// country.put("法语", "fra");
//		// country.put("西班牙语", "spa");
//		// country.put("葡萄牙语", "pt");
//		// country.put("德语", "de");
//		// country.put("意大利语", "it");
//		// country.put("nl", "荷兰语");
//		// country.put("波兰语", "pl");
//		// country.put("匈牙利语", "hu");
//		// country.put("繁体中文", "cht");
//	}

	private String mobanPath, excelPath;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// replace();
		// readXml(fileName);
		// writeToFile();
		CopyToXml c = new CopyToXml();
//		init();
		// c.mobanPath="e:\\test\\moban2.txt";
		c.showUI();
		// APP ID: 20160721000025590

		// 密钥: CaptM32jg976cOjkLhid
		// BaiduTranslateDemo b = new BaiduTranslateDemo();

		try {
			// System.out.println(b.translate("国家", "zh", "en"));
			// valueGetKey
			// c.replace2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static ArrayList valueGetKey(Map map, String value) {
		Set set = map.entrySet();
		ArrayList arr = new ArrayList<>();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			if (entry.getValue().equals(value)) {
				int s = (int) entry.getKey();
				arr.add(s);
			}
		}
		return arr;
	}

	private static void translate(String key) {
		BaiduTranslateDemo b = new BaiduTranslateDemo();
		try {
			b.translate(key, "zh", "en");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showFile(boolean isMoban) {
		JFileChooser jf = new JFileChooser("e:/test");
		jf.showDialog(null, null);
		File fi = jf.getSelectedFile();
		if (null != fi) {
			if (isMoban) {
				mobanPath = fi.getAbsolutePath();
				jlb3.setText(fi.getAbsolutePath());
			} else {
				excelPath = fi.getAbsolutePath();
				jlb4.setText(excelPath);
			}
		}

	}

	private void trans() {
		try {
			;
			readXml();
			writeToFile();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void showUI() {
		jp1 = new JPanel();
		// jp1.setLayout(new GridLayout(2, 2));

		jp2 = new JPanel();
		jp3 = new JPanel();
		jlb1 = new JLabel("模板文件:");
		jlb1.setSize(100, 150);
		jlb3 = new JLabel("             ");
		jlb4 = new JLabel("             ");
		jlb2 = new JLabel("excel文件:");
		jb1 = new JButton("选择模板");
		jbtnTran = new JButton("转换");
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showFile(true);
			}
		});
		jb1.setBounds(0, 0, 550, 50);
		jb2 = new JButton("选择excel");
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showFile(false);
			}
		});
		jbtnTran.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				trans();
			}
		});
		jtf = new JTextField(10);
		jpf = new JPasswordField(10);

		this.setLayout(new GridLayout(4, 1));

		jp1.add(jlb1);
		// jp1.add(jtf);
		jp1.add(jlb3);
		jp1.add(jb1);

		jp2.add(jlb2);
		jp2.add(jlb4);
		jp2.add(jb2);

		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		add(jbtnTran);

		this.setTitle("...");
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.setResizable(false);
	}

	public void replace() throws Exception {
		// String file = "E:\\moban2.txt";
		BufferedReader in = new BufferedReader(new FileReader(mobanPath));
		String str = null;
		while ((str = in.readLine()) != null) {
			if (str.contains(">") && str.contains("</")) {
				int index = str.indexOf(">");
				int end = str.indexOf("</");
				if (end > index) {
					String s = str.substring(index + 1, end);
					String result = str.replace(s, "%s");
					System.out.println(result);
					for (List<String> list : language) {
						list.add(result);
					}
				}
			}
		}
		in.close();
	}

	public void replace2() throws Exception {
		// String file = "E:\\moban2.txt";
		BufferedReader in = new BufferedReader(new FileReader(mobanPath));
		String str = null;
		BaiduTranslateDemo t = new BaiduTranslateDemo();
		while ((str = in.readLine()) != null) {
			if (str.contains(">") && str.contains("</")) {
				int index = str.indexOf(">");
				int end = str.indexOf("</");
				if (end > index) {
					String s = str.substring(index + 1, end);
					System.out.println(s);
					System.out.println(t.translate(s, "zh", "pl"));
				}
			}
		}
		in.close();
	}

	/**
	 * 
	 * @param filePath
	 */
	public void readXml() {
		boolean isE2007 = false; //
		if (excelPath.endsWith("xlsx"))
			isE2007 = true;
		try {
			InputStream input = new FileInputStream(excelPath); //
			Workbook wb = null;

			if (isE2007)
				wb = new XSSFWorkbook(input);
			// else
			// wb = new HSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(0);

			Iterator<Row> rows = sheet.rowIterator();
			// List<String> result=new ArrayList<String>();
			int i = 0;
			Row r = sheet.getRow(0);
			Iterator<Cell> cs = r.cellIterator();
			names = new ArrayList<>();
			while (cs.hasNext()) {
				Cell cell = cs.next();
				names.add(cell.getStringCellValue());
				language.add(new ArrayList<String>());
			}
			replace();
			rows.next();
			int count = 0;
			while (rows.hasNext()) {
				Row row = rows.next(); //
				Iterator<Cell> cells = row.cellIterator(); //
				int index = 0;
				while (cells.hasNext()) {
					Cell cell = cells.next();
					CellStyle style = cell.getCellStyle();

					int type =cell.getCellType();
					System.out.println("type:" + type);
					if (type != 1) {
						continue;
					}
					List<String> list = language.get(index);
					if (count < list.size()) {

						String value = cell.getStringCellValue().trim();

						// 替换' 为\',\ n 为\n,
						value = value.trim().replaceAll("'", "\\\\'")
								.replaceAll("\\\\ n", "\\\\n");
						// 处理空格,
						int j = 0;
						for (; j < value.length(); j++) {
							int code = (int) value.charAt(j);
							if (code == 160) {
								continue;
							} else {
								break;
							}
						}
						;
						value = value.substring(j);
						String s2 = String.format(list.get(count), value);
						System.out.println(s2);
						list.remove(count);
						list.add(count, s2);
					}

					index++;
				}
				count++;
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeToFile() throws Exception {
		File f = new File(mobanPath);
		for (int i = 0; i < names.size(); i++) {
			File file = new File(f.getParent(), names.get(i) + ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter write = new BufferedWriter(new FileWriter(file));
			List<String> list = language.get(i);
			for (String s : list) {
				write.write(s);
				write.newLine();
			}
			write.flush();
			write.close();
		}

	}

}
