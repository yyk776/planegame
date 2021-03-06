package game;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

public class Airplane implements Serializable {
int pX,pY;
int pWidth,pHeight;
int armor=0;
double speed=2;
int oil=100,life=100;
int full_life=100;
int Xoffset=0;
int intervel;
int count=0;
int bulletnum=100;
int eplane;
boolean controlled;
Image myplaneimage;
static Image eplane1 ; 
static Image eplane2 ; 
Bullettype bullettype;
ControlplaneAdvance controller;//��ս������
boolean bespecial;//Я������
Accessorytype atype;

public Airplane(int x,int y,int w,int h,Bullettype btype){
	super();
  pX=x;
  pY=y;
  pWidth=w;
  pHeight=h;
  bullettype=btype;
  controlled=false;
  bespecial=false;
  
}
public Airplane(int x,int y,int w,int h,planetype ptype){
	super();
  pX=x;
  pY=y;
  pWidth=w;
  pHeight=h;
  bullettype=ptype.getBullettype();
  speed=ptype.speed;
  life=ptype.life;
  full_life=ptype.life;
  armor=ptype.armor;
  controlled=false;
  bespecial=false;
  myplaneimage = ptype.getImage();
  
}
public Airplane(int x,int y,int w,int h,Bullettype btype,Accessorytype atype_in){
	super();
  pX=x;
  pY=y;
  pWidth=w;
  pHeight=h;
  bullettype=btype;
  controlled=false;
  bespecial=true;
  atype=atype_in;
}
public Airplane(int x,int y,int w,int h,Bullettype btype,ControlplaneAdvance controller_in){
	super();
  pX=x;
  pY=y;
  pWidth=w;
  pHeight=h;
  bullettype=btype;
  controlled=true;
  controller=controller_in;
  bespecial=false;
}
public Airplane(Bullettype btype){
	super();
  pX=getRandomIntNum(50,950);
  pY=50;
  pWidth=78;
  pHeight=68;
  intervel=getRandomIntNum(0,6);
  eplane=1;
  bullettype=btype;
  controlled=false;
  bespecial=false;
  
}
public Airplane(Bullettype btype,Accessorytype atype_in){
	super();
  pX=getRandomIntNum(50,950);
  pY=50;
  pWidth=78;
  pHeight=68;
  intervel=getRandomIntNum(0,6);
  eplane=1;
  bullettype=btype;
  controlled=false;
  bespecial=true;
  atype=atype_in;
}
public boolean hit(Bullet b){
	if ((pX<=b.bX) && (b.bX<=pX+pWidth) && (pY<=b.bY) && (b.bY<=pY+pHeight)){
		if(controlled==false)
			life=life-b.power;
		else {
			if(b.power>controller.baseDefense)
				life=life-b.power+controller.baseDefense;
		}
		return true;
	} else return false;
	
}
public boolean hit(Airplane p){
	if ((pX-pWidth<p.pX) && (p.pX<pX+pWidth) && (pY<p.pY) && (p.pY<pY+pHeight)){
		if(controlled==true&&p.controlled==true)return true;
		if(controlled==false)
			life-=120;
		else {
			if(120>controller.baseDefense)
				life=life-120+controller.baseDefense;
		}
		p.life-=120;
		return true;
	} else return false;
	
}
public boolean hit(Accessory a){
	if ((pX<a.aX) && (a.aX<pX+pWidth) && (pY<a.aY) && (a.aY<pY+pHeight)){
       if(a.atype.beequipment==true) {
    	   if(controlled==true) {
    		   bullettype=a.atype.btype;
    		   return true;
    		   }
    	   else return false;
       }
       if(controlled==true) {
    	   if(a.atype.id==6) {
    		   controller.tempDefense=controller.baseDefense;
    		   controller.baseDefense=9999;
    		   java.util.Timer timer = new java.util.Timer(true);
    		   TimerTask task = new TimerTask() {
    			    public void run() {    
    			    	controller.baseDefense=controller.tempDefense;
    			    }    
    			};
    			timer.schedule(task, 3000);   
    			}
    	   if(a.atype.id==7) {
    		   controller.speedincrement=5;
    		   java.util.Timer timer = new java.util.Timer(true);
    		   TimerTask task = new TimerTask() {
    			    public void run() {    
    			    	controller.speedincrement=0;
    			    }    
    			};
    			timer.schedule(task, 3000);   
    			}
       }
       switch(a.atype.id) {
       case 1:{
    	   life+=50;
    	   life=life>full_life?full_life:life;
    	   break;
       }
       case 3:oil+=50;break;
       case 8:life-=100;break;
       default:break;
       }

		return true;
	} else return false;
	
}
public void fly(){
    count++;
	 if (pY%200==0) {
  	  Xoffset=(getRandomIntNum(0, 3)-2);

    }
    if  (pX<50)  Xoffset=1;
    if  (pX>950)  Xoffset=-1;
	 pX+=Xoffset;	
  if (count>=intervel){
	     if (pY>500) eplane=2;
	     if (pY<50) eplane=1;
	     if ((pY>500)||(pY<50)) speed=-speed;  
     pY+=speed/2; 
     count=0;
     }
}

public void fire(ArrayList<Bullet> bulletsList,ArrayList<Bullettype> bullettypesList) {
	if(controlled) {
	if(bullettype.id==0)bulletsList.add(new Bullet(pX+pWidth/2+3,pY,13,13, bullettype,controller,1));
	else if(bullettype.id==3) bulletsList.add(new Bullet_auto(pX+pWidth/2,pY,13,13,bullettype,controller,1));
	else if(bullettype.id==1) {
		Bullet b=new Bullet(pX+pWidth/2,pY,13,13,bullettype,controller,1);
		b.direX=0.15;
		bulletsList.add(b);
		bulletsList.add(new Bullet(pX+pWidth/2,pY,13,13,bullettype,controller,1));
		b=new Bullet(pX+pWidth/2,pY,13,13,bullettype,controller,1);
		b.direX=-0.15;
		bulletsList.add(b);
	}
	else if(bullettype.id==2) {
		bulletsList.add(new Bullet(pX+pWidth/2+11,pY,13,13,bullettype,controller,1));
		bulletsList.add(new Bullet(pX+pWidth/2,pY,13,13,bullettype,controller,1));
	}
	else if(bullettype.id==4)bulletsList.add(new Bullet(pX+pWidth+6,pY,13,13,bullettype,controller,1));
	} else {
		if(bullettype.id==0)bulletsList.add(new Bullet(pX-6,pY+pHeight+1,13,13,bullettypesList.get(bullettype.id)));
		else if(bullettype.id==3) bulletsList.add(new Bullet_auto(pX-6,pY+pHeight,13,13,bullettypesList.get(bullettype.id)));
		else if(bullettype.id==1) {
			Bullet b=new Bullet(pX,pY+pHeight+1,13,13,bullettypesList.get(bullettype.id));
			b.direX=0.15;
			bulletsList.add(b);
			bulletsList.add(new Bullet(pX-6,pY+pHeight+1,13,13,bullettypesList.get(bullettype.id)));
			b=new Bullet(pX-12,pY+pHeight+1,13,13,bullettypesList.get(bullettype.id));
			b.direX=-0.15;
			bulletsList.add(b);
		}
		else if(bullettype.id==2) {
			bulletsList.add(new Bullet(pX+1,pY+pHeight,13,13,bullettypesList.get(bullettype.id)));
			bulletsList.add(new Bullet(pX-13,pY+pHeight,13,13,bullettypesList.get(bullettype.id)));
		}
		else if(bullettype.id==4)bulletsList.add(new Bullet(pX+pWidth/2-3,pY+pHeight,13,13,bullettypesList.get(bullettype.id)));
	}
}
public void moveToTop(){
	
}
public void moveToBottom(){
	
}
public void moveToleft(){
	
}
public void moveToRihgt(){
	
}
public int getRandomIntNum(int a, int b)
{
  Random random = new Random();
	
  int c = random.nextInt();
//�����õ���Random���nextInt()����������������������һ�� int �͵�����
  if(c<0)
  {
    c = -c ;
  }

  int d = ((c %(b-a)) + a + 1);

//�������ñ���d���a��b֮�е����� % ��ȡ�����㣻
  
return d;

}

}
