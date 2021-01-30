package game;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

public class Accessory implements Serializable {
	int aX,aY;
	int aWidth,aHeight;
	int speed=1;
	int typeint;
	int life=50;
	int Xoffset=0;
	int intervel;
	int count=0;
	Accessorytype atype;//��������
	
	
	public Accessory(Accessorytype atype_in){
		  aX=getRandomIntNum(50,950);
		  aY=30;
		  aWidth=32;
		  aHeight=32;
		  atype=atype_in;
		  
	}
	public Accessory(int x,int y,Accessorytype atype_in){
		  aX=x;
		  aY=y;
		  aWidth=32;
		  aHeight=32;
		  atype=atype_in;
		  
	}
	public boolean hit(Bullet b){
		if ((aX<b.bX) && (b.bX<aX+aWidth) && (aY<b.bY) && (b.bY<aY+aHeight)){
			//life-=20;
			return true;
		} else return false;
		
	}
	public boolean hit(Airplane p){
		if ((aX-+aWidth<p.pX) && (p.pX<aX+aWidth) && (aY<p.pY) && (p.pY<aY+aHeight)){
			//life-=60;
			p.life-=60;
			return true;
		} else return false;
		
	}	
	public int getRandomIntNum(int a, int b)
	{
	  Random random = new Random();
	  int c = random.nextInt();
//	�����õ���Random���nextInt()����������������������һ�� int �͵�����
	  if(c<0)
	  {
	    c = -c ;
	  }
	  int d = ((c %(b-a)) + a + 1);
//	�������ñ���d���a��b֮�е����� % ��ȡ�����㣻
	return d;

	}
}
