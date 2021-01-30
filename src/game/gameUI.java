
package game;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

import Files.FileService;
import Files.Files;
import Files.ImpleFileService;

public class gameUI {
	
	public void buildPanel() {
		JFrame frame=new JFrame("飞机大战开始菜单");
		JPanel jp=new JPanel();    //创建面板
		
		ImpleFileService ifs = new FileService();
		ifs.selectAll();
		
		List<String> files = ifs.getAllFilesName();
		String[] filename = files.toArray(new String[files.size()]);
		
		JLabel label1=new JLabel("选择存档：");    //创建标签
		JComboBox cmb1=new JComboBox();    //创建JComboBox
		//cmb1.addItem("--请选择--");    //向下拉列表中添加一项
		//cmb1.addItem("存档1");
		//cmb1.addItem("存档2");
		//cmb1.addItem("存档3");
		for(int i = 0; i < filename.length; i++) {
			cmb1.addItem(filename[i]);
		}
		
		jp.add(label1);
		jp.add(cmb1);
		

        //jp.add(buildJLabel("选择存档", 10, 20, 100, 25));
        //jp.add(buildJComboBox("cundang", "cundang", filename, 0, 100, 20, 165, 25));
				
		//JLabel label2=new JLabel("选择关卡：");    //创建标签
		//JComboBox cmb2=new JComboBox();    //创建JComboBox
		//cmb2.addItem("--请选择--");    //向下拉列表中添加一项
		//cmb2.addItem("关卡1");
		//cmb2.addItem("关卡2");
		//cmb2.addItem("关卡3");
		//cmb2.addItem("关卡4");
		//jp.add(label2);
		//jp.add(cmb2);
        
        //String chosen = getItem(cmb1);
        //System.out.print(chosen);
        //ifs.selectFilebyName(chosen);
		ifs.selectFilebyName("a3");
        
        List<String> hurdle = new ArrayList<String>();
        if (ifs.readCharpters() == null) {
        	hurdle.add("0");	
        }
        if (ifs.readCharpters()[0] == 1) {
        	hurdle.add("1");	
        }
        if (ifs.readCharpters()[1] == 1) {
        	hurdle.add("2");	
        }
        if (ifs.readCharpters()[2] == 1) {
        	hurdle.add("3");	
        }
        String[] chapter = hurdle.toArray(new String[hurdle.size()]);
		

		
        jp.add(buildJLabel("选择关卡", 10, 20, 100, 25));
        //String cmb2[] = {"----请选择----","1", "2", "3","4"};
        jp.add(buildJComboBox("chapter", "chapter", chapter, 0, 100, 20, 165, 25));
		
		//JLabel label3=new JLabel("选择难度：");    //创建标签
		//JComboBox cmb3=new JComboBox();    //创建JComboBox
		//cmb3.addItem("--请选择--");    //向下拉列表中添加一项
		//cmb3.addItem("简单");
		//cmb3.addItem("中等");
		//cmb3.addItem("困难");
		//jp.add(label3);
		//jp.add(cmb3);
		
        //jp.add(buildJLabel("选择难度", 10, 20, 100, 25));
        //String cmb3[] = {"----请选择----","简单", "中等", "困难"};
        //jp.add(buildJComboBox("level", "level", cmb3, 0, 100, 20, 165, 25));
		
		//JLabel label4=new JLabel("选择飞机：");    //创建标签
		//JComboBox cmb4=new JComboBox();    //创建JComboBox
		//cmb4.addItem("--请选择--");    //向下拉列表中添加一项
		//cmb4.addItem("飞机1");
		//cmb4.addItem("飞机2");
		//cmb4.addItem("飞机3");
		//jp.add(label4);
		//jp.add(cmb4);
        
        List<String> plane = new ArrayList<String>();
        if (ifs.readPlanes() == null) {
        	plane.add("0");	
        }        
        if (ifs.readPlanes()[0] == 1) {
        	plane.add("0");	
        }
        if (ifs.readPlanes()[1] == 1) {
        	plane.add("1");	
        }
        if (ifs.readPlanes()[2] == 1) {
        	plane.add("2");	
        }
        if (ifs.readPlanes()[3] == 1) {
        	plane.add("3");	
        }
        String[] planes = plane.toArray(new String[plane.size()]);
		
        jp.add(buildJLabel("选择飞机", 10, 20, 100, 25));
        //String cmb4[] = {"----请选择----","飞机1", "飞机2", "飞机3"};
        jp.add(buildJComboBox("plane", "plane", planes, 0, 100, 20, 165, 25));
        
		JButton btn1=new JButton("选择存档后查看成就");    //创建JButton对象
		JButton btn2=new JButton("全部选择后进入游戏");    //创建JButton对象
		jp.add(btn1);		
		jp.add(btn2);
		
		frame.add(jp);
		frame.setBounds(100,200,400,150);
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
		getItem(codeTypeBox);
	    return codeTypeBox;
	}
	
	// 添加下拉框事件监听器，获得选择的对象
	public static String getItem(JComboBox codeTypeBox) {
		codeTypeBox.addItemListener(new ItemListener() {
			@Override
	    	public void itemStateChanged(ItemEvent e) {
	    		if (e.getStateChange() == ItemEvent.SELECTED) {
	    			// 选择的下拉框选项索引
	    			System.out.print(e.getItem());
	    		}
	    	}
	    });
		return codeTypeBox.getSelectedItem().toString();
    }
	
    private static JLabel buildJLabel(String name, int x, int y, int width, int height) {
        JLabel label = new JLabel(name);
        label.setBounds(x, y, width, height);
        return label;
    }

    public static void main(String[] args) {
    	
    	new gameUI().buildPanel();
    }

}