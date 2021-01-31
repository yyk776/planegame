package game;

import java.util.ArrayList;
import java.util.Random;

public class Boss extends Airplane {
	
	int count=0;
	public Boss(int x, int y, int w, int h, Bullettype btype,int life,int armor) {
		super(x, y, w, h, btype);
		// TODO Auto-generated constructor stub
		this.life=life;
		this.full_life=life;
		this.armor=armor;
	}
	@Override
	public void fly() {}
	public void fire(ArrayList<Bullet> bulletsList,ArrayList<Bullettype> bullettypesList,int i) {
		if(i==0) {
			Bullet b;
			for(int j=0;j<20;j++) {

				b=new Bullet(pX+j-7+pWidth/2,pY+pHeight+3,13,13,bullettypesList.get(4));
				b.direX=j*0.06-0.6;
				b.speed=1.2;
				bulletsList.add(b);
			}
		}
		else if(i==1);
		else if (i==2) {
			Random rand=new Random();
			int j=rand.nextInt(13);
			j=j%13-6;
			
			Bullet b=new Bullet(pX+6+pWidth/2,pY+pHeight+3,13,13,bullettypesList.get(bullettype.id));
			b.direX=0.2+j*0.05;
			bulletsList.add(b);
			b=new Bullet(pX+2+pWidth/2,pY+pHeight+3,13,13,bullettypesList.get(bullettype.id));
			b.direX=0.5+j*0.05;
			bulletsList.add(b);
			b=new Bullet(pX-2+pWidth/2,pY+pHeight+3,13,13,bullettypesList.get(bullettype.id));
			b.direX=-0.5+j*0.05;
			bulletsList.add(b);
			b=new Bullet(pX-6+pWidth/2,pY+pHeight+3,13,13,bullettypesList.get(bullettype.id));
			b.direX=-0.2+j*0.05;
			bulletsList.add(b);
		}
	}
}
