package com.lu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * 自动翻译
 * @author lyw
 *
 */
public class TranslateFrame extends JFrame {
	JPanel jp1, jp2, jp3;//
	JLabel jlb1, jlb2, jlb3, jlb4;//
	JButton jb1, jb2, jbtnTran;//
	JTextField jtf;//
	JPasswordField jpf;//


	private String countyPath, strPath;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TranslateFrame c = new TranslateFrame();
		c.showUI();
		
//		String a="L'échec";
//		a.replaceAll("'", "\\'");
//		a = a.trim().replaceAll("'", "\\\\'");
//		System.out.println(a);
	}


	private void showFile(boolean isMoban) {
		File directory = new File("");//设定为当前文件夹
		JFileChooser jf = new JFileChooser(directory.getAbsoluteFile());
		jf.showDialog(null, null);
		File fi = jf.getSelectedFile();
		if (null != fi) {
			if (isMoban) {
				countyPath = fi.getAbsolutePath();
				jlb3.setText(fi.getAbsolutePath());
			} else {
				strPath = fi.getAbsolutePath();
				jlb4.setText(strPath);
			}
		}

	}

	private void trans() {
		try {
			ResolverTranslateFileUtil translateFileUtil=new ResolverTranslateFileUtil();
			translateFileUtil.resolver(countyPath,strPath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void showUI() {
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jlb1 = new JLabel("国家信息:");
		jlb1.setSize(100, 150);
		jlb3 = new JLabel("             ");
		jlb4 = new JLabel("             ");
		jlb2 = new JLabel("字符串文件:");
		jb1 = new JButton("国家信息");
		jbtnTran = new JButton("翻译");
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showFile(true);
			}
		});
		jb1.setBounds(0, 0, 550, 50);
		jb2 = new JButton("选择字符串文件");
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
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.setResizable(false);
	}

}
