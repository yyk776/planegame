package game;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.geom.*;
import java.util.*; 
import java.io.*;
import java.applet.*;
import java.net.*;
import java.text.AttributedString;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument.AttributeContext;

import Files.FileService;
import Files.ImpleFileService;
import jdk.javadoc.internal.doclets.formats.html.resources.standard;

import javax.swing.JButton;
import javax.swing.JFrame;

class Flag {
	 int f1=0,f2=0;
	 public Flag(){ }
	 public synchronized void putf1begin() {
	    while (f1==1)  try{ wait();} catch(Exception e){}
	 }
	 public synchronized void putf1end() { 
	    f1=1;	
	    notifyAll();  
	 }
	 public synchronized void getf1begin() {
      while (f1==0)  try{ wait();} catch(Exception e){}
		 }
	 public synchronized void getf1end() { 
		    f1=0;	
		    notifyAll();  
		 }
	 
	 public synchronized void putf2begin() {
		   while (f2==1)  try{ wait();} catch(Exception e){}
		    }
	 
	 public synchronized void putf2end() {	    
		    f2=1;	
		    notifyAll();  
		 }
	 public synchronized void getf2begin() {
		   while (f2==0)  try{ wait();} catch(Exception e){}
		    }
	 
	 public synchronized void getf2end() {	    
		    f2=0;	
		    notifyAll();  
		 }

}
public class Battlefield  extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image       OffScreen1,OffScreen2,O2;
	Graphics2D     drawOffScreen1,drawOffScreen2,g;
    Image        myplane,myplane1,eplane1,eplane2,bullet,bullet1,missile,explode,backgroud,a1,a2,a3,a4,gameoverimage,winimage,myplaneL1,myplaneL2,myplaneL3,bossImage;
    int key;
    boolean boss_attend=false;
    Boss boss;
    boolean flag1,flag2;
    boolean controlflag[]= new boolean[6];
    boolean controlflag1[]= new boolean[5];
    boolean locationreflesh=false;
    Airplane Controlplane,Controlplane1;
    ControlplaneAdvance controller,controller1;//�ɻ��������
    Bullettype nmlBullet,shotBullet,biBullet,autoBullet,cjBullet;//��ͨ�ӵ� ɢ�� ˫���ӵ�
    Accessorytype lives,boxs,oil,bibox,shotbox,shield,smasher,stoneleft,stoneright;
    //��������:���������ӣ��͹ޣ�˫���ӵ��䣬ɢ���䣬���ƣ�������
   	ArrayList<Bullet> bulletsList;
   	ArrayList<Bullettype> bullettypesList;
   	ArrayList<Airplane> planeList;
   	ArrayList<Explode> explodeList;
   	ArrayList<Accessory> accessoryList;
    TextField t1,t2,t3,t4,t5,t6 ;
    Panel p1,p2;
    Button start,save,load;
    Timer timer,timer2,timer3,timer4,timer5;
    Drawer	d1;
    Displayer d2;
    Backgroudmusic m1;
    Scenemusic m2;
   	int delay=5000;
   	float backy=638;
//   	boolean fire=false;
//�Զ����ӵ�
   	boolean fire=false;
   	boolean fire1=false;
   	boolean goon=true;
   	int gameover=0;
	boolean hasAccessory=false;
	boolean addplane=false;
	boolean isSleep=true;
	int hurdle;
	Flag flag;
	String filename;
	//////
	
	////////
	public int getRandomIntNum(int a, int b)
	{
	  Random random = new Random();
	  int c = random.nextInt();
//	Random���nextInt()�������������һ�� int �͵�����
	  if(c<0)
	  {
	    c = -c ;
	  }
	  int d = ((c %(b-a)) + a + 1);
//	�����ñ���d���a��b֮�е����� % ��ȡ�����㣻
	return d;

	}
	public Battlefield (int hurdle, planetype ptype,String filename)
    { 	this.filename=filename; 
		this.hurdle=hurdle;
		OffScreen1     =  new BufferedImage(1000,900,BufferedImage.TYPE_INT_RGB);
		drawOffScreen1 = (Graphics2D)OffScreen1.getGraphics();
		OffScreen2     =  new BufferedImage(1000,900,BufferedImage.TYPE_INT_RGB);
		drawOffScreen2 = (Graphics2D)OffScreen2.getGraphics();
        flag=new Flag();
		myplane = getToolkit().getImage("Airplanes/airplane3.gif");

		switch(hurdle) {
		case 1:eplane1 = getToolkit().getImage("Airplanes/airplane4-1.gif"); eplane2 = getToolkit().getImage("Airplanes/airplane4.gif");break;
		case 2:eplane1 = getToolkit().getImage("Airplanes/airplane7-1.gif"); eplane2 = getToolkit().getImage("Airplanes/airplane7.gif");break;
		case 3:eplane1 = getToolkit().getImage("Airplanes/airplane3-1.gif"); eplane2 = getToolkit().getImage("Airplanes/airplane2.gif");break;
		case 4:eplane1 = getToolkit().getImage("Airplanes/airplane.png"); eplane2 = getToolkit().getImage("Airplanes/airplanef.png");break;
		}
		a1=getToolkit().getImage("accessory/lives.gif"); 
		a2=getToolkit().getImage("accessory/box1.gif"); 
		a3=getToolkit().getImage("accessory/oil.gif"); 
		a4=getToolkit().getImage("accessory/stone.gif");
		
		Airplane.eplane1=eplane1;
		Airplane.eplane2=eplane2;
		bullet=getToolkit().getImage("Bullets/Bullet11.gif"); 
		bullet1=getToolkit().getImage("Bullets/Bullet12.png");
		missile=getToolkit().getImage("Bullets/cj2.png");
		explode=getToolkit().getImage("Bullets/explosion.gif");
		//backgroud=getToolkit().getImage("Backgrounds/beach.jpg");
		gameoverimage=getToolkit().getImage("accessory/gameover.gif");
		winimage=getToolkit().getImage("accessory/win.gif");
		bossImage=getToolkit().getImage("Airplanes/airplane.png");
       	planeList = new ArrayList<Airplane>(); 
       	bulletsList = new ArrayList<Bullet>(); 
       	explodeList = new ArrayList<Explode>(); 
       	accessoryList = new ArrayList<Accessory>(); 
      	Controlplane=new Airplane(500,500,80,66,ptype);
      	
    }

	
	public void hurdlebackground() {
		switch(hurdle) {
		case 1:backgroud = getToolkit().getImage("Backgrounds/bg.jpg");break;
		case 2:backgroud = getToolkit().getImage("Backgrounds/sandroad.jpg");break;
		case 3:backgroud = getToolkit().getImage("Backgrounds/color.jpg");break;
		case 4:backgroud = getToolkit().getImage("Backgrounds/beach.jpg");break;
		}
	}
	
	public void gameperpare(){
		controller=new ControlplaneAdvance();
        nmlBullet=new Bullettype(0,10,2.5,bullet);
        shotBullet=new Bullettype(1,10,1,bullet);
        biBullet=new Bullettype(2,10,2.5,bullet);
        autoBullet=new Bullettype(3,10,2,bullet1);
        cjBullet = new Bullettype(4,10,3, missile);
		lives = new Accessorytype(1,a1);
		boxs = new Accessorytype(2,a2);
		oil=new Accessorytype(3,a3);
		bibox = new Accessorytype(4,a2,biBullet);
		shotbox = new Accessorytype(5,a2,shotBullet);
		shield=new Accessorytype(6,a2);
		smasher=new Accessorytype(7,a2);
		stoneleft=new Accessorytype(8,a4,true);
		stoneright=new Accessorytype(8,a4,false);
		bullettypesList=new ArrayList<Bullettype>();
		bullettypesList.add(nmlBullet);
		bullettypesList.add(shotBullet);
		bullettypesList.add(biBullet);
		bullettypesList.add(autoBullet);
		bullettypesList.add(cjBullet);
		Controlplane.controller = controller;
		Controlplane.controlled=true;
        Controlplane.speed*=3;
        
        boss=new Boss(400,20, 210, 166, shotBullet, 5000, 5);
        p2.addKeyListener(new MyKeyListener(controlflag,controlflag1));
        
        m2=new Scenemusic();
	}
	public void gamebegin(){
//		��ʼ��
		int x = 50,y=90;
		
		
		TimerTask task=new TimerTask(){
			 @SuppressWarnings("deprecation")
			public void run(){
				 hasAccessory=true; 
			     m2.beepclip.loop();
			 }	
			};
		timer = new Timer();
		timer.schedule(task,0,delay);

	     TimerTask task2=new TimerTask(){
			 public void run(){
						 Controlplane.oil-=5 ; 
				         t3.setText(Controlplane.oil+"");
				      
					 }	
					};
		timer2 = new Timer();
		timer2.schedule(task2,3000,3000);

//	    TimerTask task3=new TimerTask(){
//			 public void run(){
//                 addplane=true;
//                       }	
//			};
//		timer3 = new Timer();
//		timer3.schedule(task3,2000,2000);
		
		TimerTask task4=new TimerTask(){
			 public void run(){
						 locationreflesh=true;
					 }	
					};
		timer4 = new Timer();
		timer4.schedule(task4,0,30);
		
		TimerTask task5=new TimerTask(){
			 public void run(){
						 isSleep=false;
					 }	
					};
		timer5 = new Timer();
		timer5.schedule(task5,8000);

		
		
		Controlplane.pX=500;
		Controlplane.pY=650;
		Controlplane.life=100;
		//Controlplane.bulletnum=100;
		Controlplane.oil=100;
		Controlplane.controller.over=false;

		t1.setText(Controlplane.life+"");

		flag1=false;
		flag2=false;
		
		int planenum=8;
		switch(hurdle) {
		case 1:planenum=8;break;
		case 2:planenum=10;break;
		case 3:planenum=12;break;
		case 4:planenum=14;break;
		}

        g=(Graphics2D)this.p2.getGraphics(); 
       	planeList.clear(); 
       	bulletsList.clear(); 
       	explodeList.clear(); 
       	accessoryList.clear(); 
       	Airplane ptemp=new Airplane(10,50,78,68,nmlBullet,bibox);
    	planeList.add(ptemp);
        for (int i=2;i<=planenum;i++){
        	switch(hurdle) {
    		case 1:x=90*i;y=50;break;
    		case 2:x=90*i;y=50*i;break;
    		case 3:x=90*i;y=50*(planenum-i);break;
    		case 4:x=90*i;y=50*(i%5);break;
    		}
        	
        	Airplane p1=new Airplane(x,y,78,68,nmlBullet);
        	p1.intervel=p1.getRandomIntNum(0,6);
        	planeList.add(p1);

            p1.eplane=1;

        }
        
        p2.requestFocus();
        m1=new Backgroudmusic();
        m1.run();
        d1=new Drawer();
    	d2=new Displayer();
    	d1.start();
        d2.start();
	}
	public void gameUnlimit(){
		int planenum=8;
		switch(hurdle) {
		case 1:planenum=8;break;
		case 2:planenum=10;break;
		case 3:planenum=12;break;
		case 4:planenum=14;break;
		}
		int i = planenum;
		TimerTask task=new TimerTask(){
			 public void run(){  
				 if(planeList.size()<i) {
					 addplane=true;
				 }
             }	
		};
		timer3 = new Timer();
		timer3.schedule(task,1000,100);
		
	}
	@SuppressWarnings("deprecation")
	public void gameContrl(Graphics2D drawOffScreen){


    	//drawOffScreen.drawImage(winimage,450,300,null);
		

//����
 
		  drawOffScreen.fillRect(0, 0, 1000, 900);
		  drawOffScreen.drawImage(backgroud,0,0,1000,900,0,(int)backy,360,320+(int)backy,null);
		  backy-=.2;
		//  System.out.println((int)backy+"");
		  if (backy<0) backy=638; 
		  Rectangle2D life=new Rectangle2D.Double(2, 700, 160, 30);
		  drawOffScreen.setColor(Color.BLACK);
		  drawOffScreen.draw(life);
		  Rectangle2D life0=new Rectangle2D.Double(2, 700, 160*Controlplane.life/100, 30);
			drawOffScreen.setColor(Color.RED);
			drawOffScreen.fill(life0);
		  
		//  drawOffScreen.drawImage(backgroud,0,0,1000,900,null);  
    	   if (addplane){
			if (planeList.size()<15) {
				if(getRandomIntNum(1, 10)<=5){
					switch(getRandomIntNum(1, 10)) {
					case 1:planeList.add(new Airplane(nmlBullet,lives));break;
					case 2:planeList.add(new Airplane(nmlBullet,oil));break;
					case 3:planeList.add(new Airplane(nmlBullet,oil));break;
					case 4:planeList.add(new Airplane(nmlBullet,bibox));break;
					case 5:planeList.add(new Airplane(nmlBullet,shotbox));break;
					case 6:planeList.add(new Airplane(nmlBullet,shield));break;
					case 7:planeList.add(new Airplane(nmlBullet,smasher));break;
					default:planeList.add(new Airplane(nmlBullet,lives));break;
					}
				}
				else
					planeList.add(new Airplane(nmlBullet));
			}    		   
		    addplane=false;
           }
    	   
    	  Iterator<Airplane> pnums = planeList.iterator();
    	   while(pnums.hasNext()) { 
    		      Airplane p = pnums.next(); 
                  p.fly();
                  if(p instanceof Boss) {
            		  life=new Rectangle2D.Double(300, 5, 400, 25);
            		  drawOffScreen.setColor(Color.BLACK);
            		  drawOffScreen.draw(life);
            		  life0=new Rectangle2D.Double(300, 5, 400*boss.life/boss.full_life, 25);
            			drawOffScreen.setColor(Color.RED);
            			drawOffScreen.fill(life0);
                drawOffScreen.drawImage(bossImage,boss.pX,boss.pY,210,166,null);
                  boss.count++;
                  if(boss.count%150==0)
      			  boss.fire(bulletsList, bullettypesList,2);
                  else if(boss.count%2000==0)boss.fire(bulletsList, bullettypesList, 0);}
                  else {
                 if (p.eplane==1) {
                	 drawOffScreen.drawImage(Airplane.eplane1,p.pX-35,p.pY-35,70,70,null);  
                 }
                 if (p.eplane==2) drawOffScreen.drawImage(Airplane.eplane2,p.pX-35,p.pY-35,70,70,null);                 
                  //�����ӵ�
     		     if ((p.getRandomIntNum(0, 300))==2)  {
     		    	 	
     		    		 p.fire(bulletsList, bullettypesList);     
     		    	 
     		       }
                  }
    		      //�ж��Ƿ񱻻���?
    		      Iterator<Bullet> bnums = bulletsList.iterator();
    	    	   while(bnums.hasNext()) { 
    	  		      Bullet b = bnums.next(); 
    	  		      if (p.hit(b)) {
    	  		    	  if(b.parent_id==1) {
    	  		    		Controlplane.controller.exp+=20;
    	  		    		if(Controlplane.controller.exp%500==0) {
    	  		    			Controlplane.controller.exp=0;
    	  		    			Controlplane.controller.level+=1;
    	  		    		}
    	  		    	  }
  							b = null;
  							bnums.remove();

    	 		
    	 		    	  m2.hitclip.play();
    	 		          };  		      
    	  	       //�ж��Ƿ�ײ�����Ʒɻ�
    	 		     if (p.hit(Controlplane)) 
 	 		    	  m2.explodeclip.play();
    	    	   } 
    	    	   
    	    	   //�ж��Ƿ�ײ������
     		      Iterator<Accessory> anums =accessoryList.iterator();
      	    	   while(anums.hasNext()) { 
      	    	    	Accessory a = anums.next(); 
      	  		        if (p.hit(a)){
      	  		        t1.setText(Controlplane.life+"");
      	 		    	  a=null;
    	 		    	  anums.remove();	
         		    	  m2.beepclip.stop();
         		    	  m2.eatclip.play();
       	  		        };
   	    	        } 
    	    	   if (p.life<0) {
    	    		  if(p.bespecial==true)
    	    			  accessoryList.add(new Accessory(p.pX,p.pY,p.atype));
    		    	  explodeList.add(new Explode(p.pX,p.pY));
    		    	  p=null;
    		    	  pnums.remove();
  	 		    	  m2.explodeclip.play();
    		          };  		    
    	   } 
//����
    	   if (hasAccessory){
    		   int temp;

    		   temp=getRandomIntNum(1,3);
    		   
    		   switch(temp) {
    		   case 1:
    			   accessoryList.add(new Accessory(boxs));
    			   break;
    		   case 2:
    			   accessoryList.add(new Accessory(lives));
    			   break;   
    		   case 3:
    			   accessoryList.add(new Accessory(oil));
    			   break;
    		   case 4:
    			   accessoryList.add(new Accessory(stoneleft));
    			   break;
    		   case 5:
    			   accessoryList.add(new Accessory(stoneleft));
    			   break;
    		   case 6:
    			   accessoryList.add(new Accessory(stoneright));
    			   break;
    		   case 7:
    			   accessoryList.add(new Accessory(stoneright));
    			   break;
    		   default:break;
    		   }
    		   
    		   hasAccessory=false;

               }
        	   Iterator<Accessory> anums = accessoryList.iterator();
        	   while(anums.hasNext()) { 
        		   Accessory a = anums.next(); 
        		   drawOffScreen.drawImage(a.atype.aImage,a.aX,a.aY,null);  
        		   
     		      a.aY+=a.speed; 
     		      if (a.aY>900){
     		    	  a=null;
     		    	  anums.remove();
     		    	  m2.beepclip.stop();
     		    	  continue;
        			     
      		        };  
     		      if (Controlplane.hit(a)){
     		    	  a=null;
     		    	 t1.setText(Controlplane.life+"");
     		    	  anums.remove();
     		    	  m2.beepclip.stop();
     		    	  m2.eatclip.play();

     		    	  continue;
        			  //t2.setText(Controlplane.life+"");    
      		        };  

      		      //�ж��Ƿ񱻻���?
      		      Iterator<Bullet> bnums = bulletsList.iterator();
      	    	   while(bnums.hasNext()) { 
      	  		      Bullet b = bnums.next(); 
      	  		      if (a.hit(b)) {
      	 		    	  b=null;
      	 		    	  bnums.remove();
      	 		    	 m2.hitclip.play();
      	  		      }; 
      	    	   }
    	    	   if (a.life<0) {
     		    	  explodeList.add(new Explode(a.aX,a.aY));
     		    	  a=null;
     		    	  m2.beepclip.stop();
     		    	  anums.remove();
  	 		    	  m2.explodeclip.play();
     		          };  		 
     	        } 
//�ӵ�
    	   if (fire){
    		   Controlplane.fire(bulletsList, bullettypesList);
    		   fire=false;
           }
    	   
    	   Iterator<Bullet> bnums = bulletsList.iterator();
    	   while(bnums.hasNext()) { 
 		      Bullet b = bnums.next(); 
 		      if(b instanceof Bullet_auto)((Bullet_auto) b).set_directions(planeList);
 		      drawOffScreen.translate(b.bX,b.bY);
 		      double tmp=b.direY<0?Math.PI+Math.asin(b.direX):-Math.asin(b.direX);
 		      drawOffScreen.rotate(tmp);
 		      //drawOffScreen.drawImage(bullet,b.bX,b.bY,null);  
 		      drawOffScreen.drawImage(b.bullettype.bimage,0,0,null); 
 		     drawOffScreen.rotate(-tmp);
 		      drawOffScreen.translate(-b.bX,-b.bY);
 		      //b.bY-=b.speed; 
 		      b.fly();

  		      if ((b.bY<0) || (b.bY>900)||(b.bX<0)||(b.bX>1000)){
 		    	  b=null;
 		    	  bnums.remove();
 		    	  continue;
  		      }
 		      if ((Controlplane.hit(b))){
 		    	  b=null;
 		    	  bnums.remove();
 		    	  m2.hitclip.play();
    			  t1.setText(Controlplane.life+"");    
 		    	     
 		    	  t3.setText(Controlplane.oil+"");   
 		    	  continue;
  		      };
 	        } 

    	    if (gameover==0) {
    	    	if(Controlplane.controller.over==false)
    	    		drawOffScreen.drawImage(Controlplane.myplaneimage,Controlplane.pX,Controlplane.pY,null);
    	    			}
    	    if (gameover==-1) drawOffScreen.drawImage(gameoverimage,450,300,null);  
    	    if (gameover==1) drawOffScreen.drawImage(winimage,450,300,null);  

  //�ж��Ƿ񱻻���?
 		      if ((Controlplane.life<0) || (Controlplane.oil<0)) {
 		    	  if(flag1==false)
 		    		  explodeList.add(new Explode(Controlplane.pX,Controlplane.pY));
 		    	  flag1=true;
		    	  Controlplane.life=0;
		    	  Controlplane.oil=0;
		    	  Controlplane.controller.over=true;
	 		      m2.explodeclip.play();
		          };
		    if(Controlplane.controller.over) {
			    	   gameover=-1;
		       
		       }
//�ж��Ƿ�ʤ��?
           if (planeList.size()==0&&boss_attend) gameover=1;
           if (planeList.size()==0&&!boss_attend) {planeList.add(boss);boss_attend=true;} 
	     if ((explodeList.size()==0) && (gameover!=0)) {
	    	 if(gameover==1) {
	    		 ImpleFileService ifs = new FileService();
	    		JOptionPane.showConfirmDialog(null, "恭喜您游戏胜利！","提示",JOptionPane.DEFAULT_OPTION);
	    		 ifs.selectFilebyName(filename);
	    	 	ifs.updateCharpter(hurdle);
	    	 	ifs.storage();
	    		int result = ifs.dolottery();
				if (result!=-1) JOptionPane.showMessageDialog(null,"恭喜你获得"+result+"号机,快到成就中查看吧！");
				else JOptionPane.showMessageDialog(null,"很遗憾你没有获得新飞机");
				ifs.storage();
	    		dispose();
	    	 }else {
	    	 JOptionPane.showConfirmDialog(null, "很遗憾您失败了！","提示",JOptionPane.DEFAULT_OPTION);
	    	 }
	         goon=false;
	     }
		   
		   Iterator<Explode> enums = explodeList.iterator();
 	   	   while(enums.hasNext()) { 
		      Explode e = enums.next(); 
		      drawOffScreen.drawImage(explode,e.eX,e.eY,null);  
		      e.life--; 
	
		      if (e.life<0) {
		    	  e=null;
		    	  enums.remove();
		          };  		      
  	        }     
 	    	//g.drawImage(OffScreen1,0,0,this.p2);
//����λ��
 	   	if(Controlplane.controller.over==false&&locationreflesh) {
 	   		//if(mode.biperson==false)
 	   		locationreflesh=false;
 	   		if(controlflag[0] == true)Controlplane.pX+= Controlplane.speed+Controlplane.controller.speedincrement;
	    	if(controlflag[1] == true)Controlplane.pX-= Controlplane.speed+Controlplane.controller.speedincrement;
	    	if(controlflag[2] == true)Controlplane.pY-= Controlplane.speed+Controlplane.controller.speedincrement;
	    	if(controlflag[3] == true)Controlplane.pY+= Controlplane.speed+Controlplane.controller.speedincrement; 
 	   }
 }
  class MyKeyListener implements KeyListener{
	  Airplane Cplane=Controlplane;
	  Airplane Cplane1=Controlplane1;
	  int keyboard[]=new int[6];
	  int keyboard1[]=new int[5];
	       boolean key_flag[]= new boolean[6];
	       boolean key_flag1[]= new boolean[5];
	       MyKeyListener(boolean controlflag[],boolean controlflag1[]) {
	    	   key_flag=controlflag;
	    	   key_flag1=controlflag1;
	    	   for(int i=0;i<5;i++) {
	    		   key_flag[i]=false;
	    		   key_flag1[i]=false;
	    	   }
	    	   key_flag[5]=false;
	    	   keyboard[0]=KeyEvent.VK_RIGHT;
	    	   keyboard[1]=KeyEvent.VK_LEFT;
	    	   keyboard[2]=KeyEvent.VK_UP;
	    	   keyboard[3]=KeyEvent.VK_DOWN;
	    	   keyboard[4]=KeyEvent.VK_SPACE;
	    	   keyboard[5]=KeyEvent.VK_X;
	    	   keyboard1[0]=KeyEvent.VK_D;
	    	   keyboard1[1]=KeyEvent.VK_A;
	    	   keyboard1[2]=KeyEvent.VK_W;
	    	   keyboard1[3]=KeyEvent.VK_S;
	    	   keyboard1[4]=KeyEvent.VK_F;
	       }
	       @SuppressWarnings("deprecation")
		public void movePlane() {
	    	   if(Cplane.controller.over==false) {
		    	   if(key_flag[4] == true) {
		    		  // if  (Cplane.bulletnum-->=0)
		 	 	    	  fire=true;
		  		    	  m2.gunshotclip.play();
		    	   }  
		    	   if(key_flag[5]==true) {
		    		   int index=bullettypesList.indexOf(Controlplane.bullettype);
		    		   if(index==4)index=0;else {
						index++;
					}
		    		   Controlplane.bullettype=bullettypesList.get(index);
		    	   }
	    	   }
	    	   
	    	   
	       }
	 	   public void keyTyped(KeyEvent e)  { }
	 	   public void keyPressed(KeyEvent e)
	 	   {
	 		   key = e.getKeyCode();
	 		   if(key == keyboard[0])
	 	      {
	 			  key_flag[0]=true;
	 	      }
	 	      else if(key == keyboard[1])
	 	      {
	 	    	 key_flag[1]=true;
	 	      }
	 	      else if(key == keyboard[2])
	 	      {
	 	    	 key_flag[2]=true;
	 	      }
	 	      else if(key == keyboard[3])
	 	      {
	 	    	 key_flag[3]=true;
	 	      }
	 	      else if(key == keyboard[4])
	 	      {
	 	    	 key_flag[4]=true;
	 	      } else if(key == keyboard[5])
	 	      {
	 	    	 key_flag[5]=true;
	 	      }
	 	      else if(key == keyboard1[0])
	 	      {
	 			  key_flag1[0]=true;
	 	      }
	 	      else if(key == keyboard1[1])
	 	      {
	 	    	 key_flag1[1]=true;
	 	      }
	 	      else if(key == keyboard1[2])
	 	      {
	 	    	 key_flag1[2]=true;
	 	      }
	 	      else if(key == keyboard1[3])
	 	      {
	 	    	 key_flag1[3]=true;
	 	      }
	 	      else if(key == keyboard1[4])
	 	      {
	 	    	 key_flag1[4]=true;
	 	      } 
	 		   
	 		  movePlane();
	 	   }
	 	   public void keyReleased(KeyEvent e)
	 	   { 
	 		  key = e.getKeyCode();
	 		   if(key == KeyEvent.VK_RIGHT)
	 	      {
	 			  key_flag[0]=false;
	 	      }
	 	      else if(key == KeyEvent.VK_LEFT)
	 	      {
	 	    	 key_flag[1]=false;
	 	      }
	 	      else if(key == KeyEvent.VK_UP)
	 	      {
	 	    	 key_flag[2]=false;
	 	      }
	 	      else if(key == KeyEvent.VK_DOWN)
	 	      {
	 	    	 key_flag[3]=false;
	 	      }
	 	      else if(key == KeyEvent.VK_SPACE)
	 	      {
	 	    	 key_flag[4]=false;
	 	      } 
	 	      else if(key == KeyEvent.VK_X)
	 	      {
	 	    	 key_flag[5]=false;
	 	      } 
	 	     else if(key == keyboard1[0])
	 	      {
	 			  key_flag1[0]=false;
	 	      }
	 	      else if(key == keyboard1[1])
	 	      {
	 	    	 key_flag1[1]=false;
	 	      }
	 	      else if(key == keyboard1[2])
	 	      {
	 	    	 key_flag1[2]=false;
	 	      }
	 	      else if(key == keyboard1[3])
	 	      {
	 	    	 key_flag1[3]=false;
	 	      }
	 	      else if(key == keyboard1[4])
	 	      {
	 	    	 key_flag1[4]=false;
	 	      } 
	 		  movePlane();
	 	   }	   
	    }

  public void showcomponent(){
	   MenuBar m_MenuBar = new MenuBar(); 
	   Menu menuFile = new Menu("0");     //�����˵�
	   m_MenuBar.add(menuFile);                 //���˵�����˵���
	   MenuItem  f1 =  new MenuItem("11");   //�������˵���
	   MenuItem  f2 = new MenuItem("22");
	   menuFile.add(f1);                                       //����˵�
	   menuFile.add(f2);
       setMenuBar(m_MenuBar);  
     //;
    	p1 = new Panel();
	    add(p1,"North");
	    p1.setLayout(new GridLayout(1,10)); 


	    //p1.add(new Label("13"),0);
		t1 = new TextField(3);
		//p1.add(t1,1);
	   	//.add(new Label("14"),2);
		t3 = new TextField(3);
		//p1.add(t3,3);
	    start=new Button("start");	
	    //p1.add(start,4);
		start.addActionListener(new Startaction());
	    //save=new Button("save");	
	    //p1.add(save,5);
	    //save.addActionListener(new Saveaction());
	    //load=new Button("load");	
	    //p1.add(load,6);

        //load.addActionListener(new Loadaction());
        	    
   //
	  	p2 = new Panel();
   	    add(p2);
	/*  	Choice ColorChooser = new Choice();
        ColorChooser.add("Green");
	    ColorChooser.add("Red"); 
	    ColorChooser.add("Blue");
	    p.add(ColorChooser);
	    t1 = new TextField(3);
	    p.add(t1);
	    ColorChooser.addItemListener(new ItemListener(){
 			 public void itemStateChanged(ItemEvent e){
				 String s= e.getItem().toString();
				 t1.setText(s);}
	    });*/
	   }
  
public void startgame() {
	JFrame fs = new JFrame("飞机大战");
    fs.setSize(1000, 900);
    //fs.setLocation(580, 240);
    fs.setLayout(null);
	hurdlebackground();


	addWindowListener(new WindowAdapter()
	  {
	   public void windowClosing(WindowEvent e) {
	    System.exit(0);
	   }
	  });
    showcomponent();
    setSize(1000, 900);
    setVisible(true);
    fs.dispose();
    fs.setAlwaysOnTop(isShowing());
    fs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    gameperpare();	
    JOptionPane.showMessageDialog(null, "点击开始游戏！");
    goon=true;
    gameover=0;
    start.disable(); 
    gamebegin();
}
public static void main(String args[]) {

	System.out.print("请输入关卡数：");
	Scanner input = new Scanner(System.in);
	int hurdle=input.nextInt();
	System.out.print("请输入要玩的飞机编号：");
	Scanner input1 = new Scanner(System.in);
	int pid = input1.nextInt();
	ImplePlaneTypeService ipts = new PlaneTypeService();
	planetype ptype = ipts.selectPlanebyId(pid);
	Battlefield f = new Battlefield(hurdle,ptype);


    f.gamebegin();    
}	

class Drawer extends Thread {
	public void run() {
		 while (goon)
         {
         flag.putf1begin();
         gameContrl(drawOffScreen1);
         flag.putf1end();
        
         flag.putf2begin();

         gameContrl(drawOffScreen2);
         flag.putf2end();

	 }
	}
}
class Displayer extends Thread {
	@SuppressWarnings("deprecation")
	public void run() {
		 while (goon)
         {
	       flag.getf1begin();
	       	 g.drawImage(OffScreen1,0,0,Battlefield.this.p2);
	       flag.getf1end();

	       flag.getf2begin();
	       	 g.drawImage(OffScreen2,0,0,Battlefield.this.p2);   
	       flag.getf2end();
	 }
		 System.out.println("Game Over");
		 timer.cancel();
		 timer=null;
		 timer2.cancel();
		 timer2=null;
		 m2.beepclip.stop();
		 m1.clip.stop();
		 m1=null;
		 start.enable();
		 
	}
}
class Startaction implements ActionListener{
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent event) {                    
    	goon=true;
    	gameover=0;
    	start.disable(); 
		gamebegin();
		
 
    }   
}
class Saveaction implements ActionListener{
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent event) {                    
        d1.suspend();
        d2.suspend();
        ObjectOutputStream oos;
		try {
			File f=new File("save/save.dat");
			if (f.exists())  f.delete();

			oos = new ObjectOutputStream(new FileOutputStream("save/save.dat"));
			oos.writeObject(Controlplane);
			oos.writeObject(Controlplane1);
			oos.writeObject(planeList);
			oos.writeObject(bulletsList);
			oos.writeObject(accessoryList);
			oos.writeObject(explodeList);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        d1.resume();
        d2.resume();
    }   
}
class Loadaction implements ActionListener{
    @SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent event) {                    

        ObjectInputStream ios;

			try {
				ios = new ObjectInputStream(new FileInputStream("save/save.dat"));
				Controlplane=(Airplane)ios.readObject();
				Controlplane1=(Airplane)ios.readObject();
				planeList=(ArrayList<Airplane>)ios.readObject();
				bulletsList=(ArrayList<Bullet>)ios.readObject();
    			accessoryList=(ArrayList<Accessory>)ios.readObject();
	    		explodeList=(ArrayList<Explode>)ios.readObject();
		    	ios.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TimerTask task=new TimerTask(){
				 @SuppressWarnings("deprecation")
				public void run(){
					 hasAccessory=true; 
				     m2.beepclip.loop();
				 }	
				};
			timer = new Timer();
			timer.schedule(task,0,delay);

		     TimerTask task2=new TimerTask(){
				 public void run(){
							 Controlplane.oil-=5 ; 
					         t3.setText(Controlplane.oil+""); 	
						 }	
						};
			timer2 = new Timer();
			timer2.schedule(task2,3000,3000);
		    TimerTask task3=new TimerTask(){
				 public void run(){
	                 addplane=true;
	                       }	
				};
			timer3 = new Timer();
			timer3.schedule(task3,2000,40000);
			goon=true;
	    	gameover=0;
			p2.requestFocus();

			d1=new Drawer();
	    	d2=new Displayer();
	    	d1.start();
	        d2.start();
	        m1=new Backgroudmusic();
	        m1.run();

    }   
}
class Backgroudmusic {
	@SuppressWarnings("deprecation")
	AudioClip clip;
	@SuppressWarnings("deprecation")
	public void run() {
	File backmusic=new File("music/Every Breath You Take.mid");
	try{
		clip=Applet.newAudioClip(backmusic.toURL());
		clip.loop();
	}catch (Exception e) {};
   }
 }
class Scenemusic {
	File gunshot,explode,beep,hit,eat;
	@SuppressWarnings("deprecation")
	AudioClip gunshotclip,explodeclip,beepclip,hitclip,eatclip;
	@SuppressWarnings("deprecation")
	public  Scenemusic(){
		super();
    		gunshot=new File("music/gunshot.wav");
    		explode=new File("music/explode.wav");
    		beep=new File("music/beep.wav");
    		hit=new File("music/hit.wav");
    		eat=new File("music/eat.wav");
		try{
			gunshotclip=Applet.newAudioClip(gunshot.toURL());
			explodeclip=Applet.newAudioClip(explode.toURL());
			beepclip=Applet.newAudioClip(beep.toURL());
			hitclip=Applet.newAudioClip(hit.toURL());
			eatclip=Applet.newAudioClip(eat.toURL());

		}catch (Exception e) {};
	}
/*	public void run() {
	  while (true) {
		  if (gunshot_voice>0) {gunshotclip.play();gunshot_voice--;};
		  if (explode_voice>0) {explodeclip.play();explode_voice--;};
		  if (accessory_voice>0) {beepclip.play(); accessory_voice--;};
	    }
	  }*/
  }
}
