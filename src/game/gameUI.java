
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
	int chapterid=0;
	String filename;
	public void buildPanel() {
		JFrame frame=new JFrame("飞机大战开始菜单");
		JPanel jp=new JPanel();    //创建面板
		      
		ImpleFileService ifs = new FileService();
		ifs.insertFile("bbb");

		ifs.storage();
		List<String> files = ifs.getAllFilesName();
		String[] filename = files.toArray(new String[files.size()]);
		
		JComboBox<String> cmb1=new JComboBox<String>();    //创建JComboBox
		cmb1.addItem("----请选择----");    //向下拉列表中添加一项
		for(String e:filename) {
			cmb1.addItem(e);
		}
		
        JComboBox<String> chapter=new JComboBox<String>();
        chapter.addItem("----请选择----");
		
		JLabel label4=new JLabel("选择飞机：");    //创建标签
		JComboBox<String> plane=new JComboBox<String>();    //创建JComboBox
        plane.addItem("----请选择----");
        
        jp.add(buildJLabel("选择存档", 20, 50, 100, 25));
		jp.add(cmb1);
		jp.add(buildJLabel("选择关卡", 10, 20, 100, 25));
		jp.add(chapter);
		jp.add(label4);
		jp.add(plane);
		
		JButton btn1=new JButton("选择存档后查看成就");    //创建JButton对象
		JButton btn2=new JButton("全部选择后进入游戏");    //创建JButton对象
		jp.add(btn1);		
		jp.add(btn2);
		
		
        ItemListener chapterlistener=new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ItemEvent.SELECTED==e.getStateChange()) {
					String str=e.getItem().toString();
					if(str.equals("第一关"))chapterid=1;
					else if(str.equals("第二关"))chapterid=2;
					else if(str.equals("第三关"))chapterid=3;
					else if(str.equals("第四关"))chapterid=4;
					else if(str.equals("----请选择----"))chapterid=0;
				}
			}
		};
		chapter.addItemListener(chapterlistener);

		ItemListener filelistener=new ItemListener() {
	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ItemEvent.SELECTED==e.getStateChange()) {
				
				//重置存档
				ifs.selectFilebyName(e.getItem().toString());
				//重置选关列表
				chapter.removeAllItems();
				int a[]=ifs.readCharpters();
				chapter.addItem("----请选择----");
				chapter.addItem("第一关");
		        if (a[0]==1) {
		        	chapter.addItem("第二关");	
		        }
		        if (a[1]==1) {
		        	chapter.addItem("第三关");	
		        }
		        if (a[2]==1) {
		        	chapter.addItem("第四关");	
		        }
		        //重置飞机列表
		        plane.removeAllItems();
		        int b[]=ifs.readPlanes();
		        plane.addItem("----请选择----");
		        for(int i=0;i<b.length;i++) {
		        	if(b[i]==1)plane.addItem(i+"号飞机");
		        }
				}
			}
		};
		cmb1.addItemListener(filelistener);
		
		ActionListener honerListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Honor.buildPanel(ifs);
			}
		};
		btn1.addActionListener(honerListener);;

		
		frame.add(jp);
		frame.setBounds(100,200,600,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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