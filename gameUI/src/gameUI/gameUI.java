package gameUI;

import javax.swing.*;

public class gameUI {
	
	public static void main(String[] args) {
		JFrame frame=new JFrame("飞机大战开始菜单");
		JPanel jp=new JPanel();    //创建面板
		
		JLabel label1=new JLabel("选择存档：");    //创建标签
		JComboBox cmb1=new JComboBox();    //创建JComboBox
		cmb1.addItem("--请选择--");    //向下拉列表中添加一项
		cmb1.addItem("存档1");
		cmb1.addItem("存档2");
		cmb1.addItem("存档3");
		jp.add(label1);
		jp.add(cmb1);
		
		JLabel label2=new JLabel("选择关卡：");    //创建标签
		JComboBox cmb2=new JComboBox();    //创建JComboBox
		cmb2.addItem("--请选择--");    //向下拉列表中添加一项
		cmb2.addItem("关卡1");
		cmb2.addItem("关卡2");
		cmb2.addItem("关卡3");
		jp.add(label2);
		jp.add(cmb2);
		
		JLabel label3=new JLabel("选择难度：");    //创建标签
		JComboBox cmb3=new JComboBox();    //创建JComboBox
		cmb3.addItem("--请选择--");    //向下拉列表中添加一项
		cmb3.addItem("难度1");
		cmb3.addItem("难度2");
		cmb3.addItem("难度3");
		jp.add(label3);
		jp.add(cmb3);
		
		JLabel label4=new JLabel("选择飞机：");    //创建标签
		JComboBox cmb4=new JComboBox();    //创建JComboBox
		cmb4.addItem("--请选择--");    //向下拉列表中添加一项
		cmb4.addItem("飞机1");
		cmb4.addItem("飞机2");
		cmb4.addItem("飞机3");
		jp.add(label4);
		jp.add(cmb4);
		
		JButton btn1=new JButton("查看成就");    //创建JButton对象
		JButton btn2=new JButton("进入游戏");    //创建JButton对象
		jp.add(btn1);
		jp.add(btn2);
		
		frame.add(jp);
		frame.setBounds(100,200,350,150);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}