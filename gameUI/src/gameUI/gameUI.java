package gameUI;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import Files.File;

public class gameUI {
	
	public void buildPanel() {
		JFrame frame=new JFrame("飞机大战开始菜单");
		JPanel jp=new JPanel();    //创建面板
		
		//JLabel label1=new JLabel("选择存档：");    //创建标签
		//JComboBox cmb1=new JComboBox();    //创建JComboBox
		//cmb1.addItem("--请选择--");    //向下拉列表中添加一项
		//cmb1.addItem("存档1");
		//cmb1.addItem("存档2");
		//cmb1.addItem("存档3");
		//jp.add(label1);
		//jp.add(cmb1);
		

		
        jp.add(buildJLabel("选择存档", 10, 20, 100, 25));
        String cmb1[] = {"----请选择----","存档1", "存档2", "存档3"};
        jp.add(buildJComboBox("file", "file", cmb1, 0, 100, 20, 165, 25));
				
		//JLabel label2=new JLabel("选择关卡：");    //创建标签
		//JComboBox cmb2=new JComboBox();    //创建JComboBox
		//cmb2.addItem("--请选择--");    //向下拉列表中添加一项
		//cmb2.addItem("关卡1");
		//cmb2.addItem("关卡2");
		//cmb2.addItem("关卡3");
		//cmb2.addItem("关卡4");
		//jp.add(label2);
		//jp.add(cmb2);
		
		
        jp.add(buildJLabel("选择关卡", 10, 20, 100, 25));
        String cmb2[] = {"----请选择----","关卡1", "关卡2", "关卡3"};
        jp.add(buildJComboBox("chapter", "chapter", cmb2, 0, 100, 20, 165, 25));
		
		//JLabel label3=new JLabel("选择难度：");    //创建标签
		//JComboBox cmb3=new JComboBox();    //创建JComboBox
		//cmb3.addItem("--请选择--");    //向下拉列表中添加一项
		//cmb3.addItem("简单");
		//cmb3.addItem("中等");
		//cmb3.addItem("困难");
		//jp.add(label3);
		//jp.add(cmb3);
		
        jp.add(buildJLabel("选择难度", 10, 20, 100, 25));
        String cmb3[] = {"----请选择----","简单", "中等", "困难"};
        jp.add(buildJComboBox("level", "level", cmb3, 0, 100, 20, 165, 25));
		
		//JLabel label4=new JLabel("选择飞机：");    //创建标签
		//JComboBox cmb4=new JComboBox();    //创建JComboBox
		//cmb4.addItem("--请选择--");    //向下拉列表中添加一项
		//cmb4.addItem("飞机1");
		//cmb4.addItem("飞机2");
		//cmb4.addItem("飞机3");
		//jp.add(label4);
		//jp.add(cmb4);
		
        jp.add(buildJLabel("选择飞机", 10, 20, 100, 25));
        String cmb4[] = {"----请选择----","飞机1", "飞机2", "飞机3"};
        jp.add(buildJComboBox("plane", "plane", cmb4, 0, 100, 20, 165, 25));
        
		JButton btn1=new JButton("选择存档后查看成就");    //创建JButton对象
		JButton btn2=new JButton("全部选择后进入游戏");    //创建JButton对象
		jp.add(btn1);		
		jp.add(btn2);
		
		frame.add(jp);
		frame.setBounds(100,200,350,150);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static JComboBox buildJComboBox(Object selectedItem, String name, String[] elements, int selectedIndex, int x, int y, int width, int height) {
		DefaultComboBoxModel codeTypeModel = new DefaultComboBoxModel();
		// elements 下拉框中的选项
		for (String element : elements) {
		codeTypeModel.addElement(element);
		}
		JComboBox codeTypeBox = new JComboBox(codeTypeModel);
		codeTypeBox.setName(name);
		// 默认选中的下拉框选项
		codeTypeBox.setSelectedItem(selectedItem);
		//codeTypeBox.setSelectedItem(selectedIndex);
		codeTypeBox.setBounds(x, y, width, height);
		// 添加下拉框事件监听器
	    codeTypeBox.addItemListener(new ItemListener() {
	    	@Override
	    	public void itemStateChanged(ItemEvent e) {
	    		if (e.getStateChange() == ItemEvent.SELECTED) {
	    			// 选择的下拉框选项索引
	    			System.out.print(codeTypeBox.getSelectedIndex());
	    		}
	    	}
	    });
	    return codeTypeBox;
	}
	
    private static JLabel buildJLabel(String name, int x, int y, int width, int height) {
        JLabel label = new JLabel(name);
        label.setBounds(x, y, width, height);
        return label;
    }

    public static void main(String[] args) {
    	buildPanel();
    }

}