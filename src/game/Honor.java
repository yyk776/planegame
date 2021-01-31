package game;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

import Files.FileService;
import Files.Files;
import Files.ImpleFileService;

public class Honor {
	public static void buildPanel(ImpleFileService ifs) {
		JFrame frame=new JFrame("查看成就");
		JPanel jp=new JPanel();    //创建面板

		
		JLabel jl00=new JLabel(new ImageIcon("Airplanes/airplane0-9.gif.png"));
		JLabel jl01=new JLabel(new ImageIcon("Airplanes/airplane0.gif"));
		JLabel jl10=new JLabel(new ImageIcon("Airplanes/airplane1-9.gif.png"));
		JLabel jl11=new JLabel(new ImageIcon("Airplanes/airplane1.gif"));
		JLabel jl20=new JLabel(new ImageIcon("Airplanes/airplane2-9.gif.png"));
		JLabel jl21=new JLabel(new ImageIcon("Airplanes/airplane2.gif"));
		JLabel jl30=new JLabel(new ImageIcon("Airplanes/airplane3-9.gif.png"));
		JLabel jl31=new JLabel(new ImageIcon("Airplanes/airplane3.gif"));
		JLabel jl40=new JLabel(new ImageIcon("Airplanes/airplane4-9.gif.png"));
		JLabel jl41=new JLabel(new ImageIcon("Airplanes/airplane4.gif"));
		JLabel jl50=new JLabel(new ImageIcon("Airplanes/airplane5-9.gif.png"));
		JLabel jl51=new JLabel(new ImageIcon("Airplanes/airplane5.gif"));
		JLabel jl60=new JLabel(new ImageIcon("Airplanes/airplane6-9.gif.png"));
		JLabel jl61=new JLabel(new ImageIcon("Airplanes/airplane6.gif"));
		JLabel jl70=new JLabel(new ImageIcon("Airplanes/airplane7-9.gif.png"));
		JLabel jl71=new JLabel(new ImageIcon("Airplanes/airplane7.gif"));
		JLabel jl80=new JLabel(new ImageIcon("Airplanes/airplane8-9.gif.png"));
		JLabel jl81=new JLabel(new ImageIcon("Airplanes/airplane8.gif"));
		JLabel jl90=new JLabel(new ImageIcon("Airplanes/airplane9-9.png"));
		JLabel jl91=new JLabel(new ImageIcon("Airplanes/airplane9.png"));
		
		if (ifs.readPlanes()[0] == 0) {
			jp.add(jl00);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[0] == 1) {
	        jp.add(jl01);
	        jl00.setBounds(0, 150, 700, 500);
		}
        
		if (ifs.readPlanes()[1] == 0) {
			jp.add(jl10);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[1] == 1) {
	        jp.add(jl11);
	        jl00.setBounds(0, 150, 700, 500);
		}
        
		if (ifs.readPlanes()[2] == 0) {
			jp.add(jl20);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[2] == 1) {
	        jp.add(jl21);
	        jl00.setBounds(0, 150, 700, 500);
		}
        
		if (ifs.readPlanes()[3] == 0) {
			jp.add(jl30);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[3] == 1) {
	        jp.add(jl31);
	        jl00.setBounds(0, 150, 700, 500);
		}
        
		if (ifs.readPlanes()[4] == 0) {
			jp.add(jl40);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[4] == 1) {
	        jp.add(jl41);
	        jl00.setBounds(0, 150, 700, 500);
		}
        
		if (ifs.readPlanes()[5] == 0) {
			jp.add(jl50);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[5] == 1) {
	        jp.add(jl51);
	        jl00.setBounds(0, 150, 700, 500);
		}
        
		if (ifs.readPlanes()[6] == 0) {
			jp.add(jl60);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[6] == 1) {
	        jp.add(jl61);
	        jl00.setBounds(0, 150, 700, 500);
		}
        
		if (ifs.readPlanes()[7] == 0) {
			jp.add(jl70);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[7] == 1) {
	        jp.add(jl71);
	        jl00.setBounds(0, 150, 700, 500);
		}
        
		if (ifs.readPlanes()[8] == 0) {
			jp.add(jl80);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[8] == 1) {
	        jp.add(jl81);
	        jl00.setBounds(0, 150, 700, 500);
		}
		
		if (ifs.readPlanes()[9] == 0) {
			jp.add(jl90);
			jl00.setBounds(0, 150, 700, 500);
		}
		else if (ifs.readPlanes()[9] == 1) {
	        jp.add(jl91);
	        jl00.setBounds(0, 150, 700, 500);
		}
        		
        frame.add(jp);
		frame.setBounds(100,200,400,350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		buildPanel();
	}
}
