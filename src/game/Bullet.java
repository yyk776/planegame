package game;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
public class Bullet implements Serializable {
	double bX,bY;
	int bWidth,bHeight;
	double speed=3;
	int xspeed=1;
	double direX=0;
	double direY=1;
	transient Image bimage;
	Bullettype bullettype;
	int shotid;
	int power;
	int parent_id;
	public Bullet(int x,int y,int w,int h,Bullettype btype){
		super();
		  bX=x;
		  bY=y;
		  bWidth=w;
		  bHeight=h;
		  bullettype=btype;
		  bimage=btype.bimage;
		  speed=btype.speed;
		  power=bullettype.power;
		  parent_id=0;
	}
	public Bullet(int x,int y,int w,int h,Bullettype btype,int shotid_in){//����ɢ��
		super();
		  bX=x;
		  bY=y;
		  bWidth=w;
		  bHeight=h;
		  bullettype=btype;
		  shotid=shotid_in;
		  bimage=btype.bimage;
		  speed=btype.speed;
		  power=bullettype.power;
		  parent_id=0;
	}
	public Bullet(int x,int y,int w,int h,Bullettype btype,int shotid_in,ControlplaneAdvance controller,int id){//����ɢ��
		super();
		  bX=x;
		  bY=y;
		  bWidth=w;
		  bHeight=h;
		  bullettype=btype;
		  shotid=shotid_in;
		  bimage=btype.bimage;
		  speed=btype.speed;
		  power=bullettype.power+controller.baseDamage;
		  parent_id=id;
		  direY=-1;
	}
	public Bullet(int x,int y,int w,int h,Bullettype btype,ControlplaneAdvance controller,int id){
		super();
		  bX=x;
		  bY=y;
		  bWidth=w;
		  bHeight=h;
		  bullettype=btype;
		  bimage=btype.bimage;
		  speed=btype.speed;
		  power=bullettype.power+controller.baseDamage;
		  parent_id=id;
		  direY=-1;
	}
	public void fly() {
		bX+=direX*speed;
		bY+=direY*speed;
	}
	public void hit(){
		
	}
	public void moveToTop(){
		
	}
	public void moveToBottom(){
		
	}
	public void moveToleft(){
		
	}
	public void moveToRihgt(){
		
	}
}
