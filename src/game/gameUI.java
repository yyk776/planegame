
package game;

import java.awt.Dimension;
import java.awt.Toolkit;
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
		int plane_id=-1;
		String file;
		public void buildPanel() {
			JFrame frame=new JFrame("飞机大战开始菜单");			
			/*int windowWidth = frame.getWidth();                     //获得窗口宽
	        int windowHeight = frame.getHeight();                   //获得窗口高
	        Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
	        Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	        int screenWidth = screenSize.width;                     //获取屏幕的宽
	        int screenHeight = screenSize.height;                   //获取屏幕的高
	        frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示*/
	        JPanel jp=new JPanel();    //创建面板
			jp.setLayout(null);      
			
			ImpleFileService ifs = new FileService();

			List<String> files = ifs.getAllFilesName();
			String[] filename = files.toArray(new String[files.size()]);
			
			JComboBox<String> cmb1=new JComboBox<String>();    //创建JComboBox
			cmb1.addItem("----请选择----");    //向下拉列表中添加一项
			for(String e:filename) {
				cmb1.addItem(e);
			}
			
	        JComboBox<String> chapter=new JComboBox<String>();
	        chapter.addItem("----请选择----");
			
			
			JComboBox<String> plane=new JComboBox<String>();    //创建JComboBox
	        plane.addItem("----请选择----");
	        
	        //第一部分UI布局
	        /*
	        JLabel jb1 = new JLabel("新建存档：");
	        jb1.setBounds(10, 20, 100, 30);
	        jp.add(jb1);
	        JTextField text = new JTextField(6);
	        text.setBounds(80, 20, 100, 30);
	        jp.add(text);
	        */
	        jp.add(buildJLabel("选择存档：", 10, 20, 100, 30));
	        cmb1.setBounds(80, 20, 100, 30);
			jp.add(cmb1);
			jp.add(buildJLabel("选择关卡：", 200, 20, 100, 30));
			chapter.setBounds(260, 20, 100, 30);
			jp.add(chapter);	
			jp.add(buildJLabel("选择飞机：", 380, 20, 100, 30));
			plane.setBounds(440, 20, 100, 30);
			jp.add(plane);
			
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
					file=e.getItem().toString();
					ifs.selectFilebyName(file);
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
			
			ItemListener planeItemListener=new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(ItemEvent.SELECTED==e.getStateChange()) {
						if(e.getItem().toString().equals("----请选择----"))plane_id=-1;
						else plane_id=e.getItem().toString().charAt(0)-48;
						System.out.println(plane_id);
					}
				}
			};
			plane.addItemListener(planeItemListener);
			
			//第二部分UI布局
			JButton btn3=new JButton("新建存档");
			btn3.setBounds(80, 120, 100, 30);
			jp.add(btn3);
			
			
			//第三部分UI布局
	
			//添加按键
			JButton btn1=new JButton("查看成就");    //创建JButton对象
			JButton btn2=new JButton("进入游戏");    //创建JButton对象

			btn1.setBounds(250, 120, 100, 30);
			btn2.setBounds(420, 120, 100, 30);
			jp.add(btn1);		
			jp.add(btn2);
			ActionListener honerListener=new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(file.length()==0)JOptionPane.showMessageDialog(null, "请选择存档");
					Honor.buildPanel(ifs);
				}
			};
			btn1.addActionListener(honerListener);;
			
			ActionListener startListener=new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(chapterid==0||plane_id==-1)JOptionPane.showMessageDialog(null, "请选择存档、关卡以及飞机");
					else{
						ImplePlaneTypeService ipts = new PlaneTypeService();
						new Battlefield(chapterid, ipts.selectPlanebyId(plane_id), ifs).startgame();
		
						ifs.storage();
						ifs.selectAll();
					}
				}
			};
			btn2.addActionListener(startListener);
			
			
			ActionListener newfileListener=new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String str=JOptionPane.showInputDialog("输入存档名");
					ifs.insertFile(str);
					System.out.println(str);
					ifs.storage();
					
					//重置存档列表
					cmb1.removeAllItems();
					cmb1.addItem("----请选择----");    //向下拉列表中添加一项
					for(String name:ifs.getAllFilesName()) {
						cmb1.addItem(name);
					}
					cmb1.setSelectedItem(str);
				}
			};
			btn3.addActionListener(newfileListener);
			
			
			frame.add(jp);
			frame.setBounds(100,200,600,250);
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