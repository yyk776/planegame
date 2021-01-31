package game;
import java.util.ArrayList;
import java.util.Iterator;

public class Bullet_auto extends Bullet {

	public Bullet_auto(int x, int y, int w, int h, Bullettype btype) {
		super(x, y, w, h, btype);
		// TODO Auto-generated constructor stub
	}
	public Bullet_auto(int x,int y,int w,int h,Bullettype btype,ControlplaneAdvance controller,int id) {
		super(x, y, w, h, btype,controller,id);
	}

	public Bullet_auto(int x, int y, int w, int h, Bullettype btype,int i) {
		super(x, y, w, h, btype,i);
		// TODO Auto-generated constructor stub
	}
	public void set_directions(ArrayList<Airplane> planeList) {
		Iterator<Airplane> pnums = planeList.iterator();
		int X=0,Y=0;
		double distance=10000000;
		while(pnums.hasNext()) {
			Airplane p = pnums.next();
			double dis=(p.pX+p.pWidth/2-bX)*(p.pX+p.pWidth/2-bX)+(p.pY+p.pHeight/2-bY)*(p.pY+p.pHeight/2-bY);
			if(dis<distance) {
				distance=dis;
				X=p.pX+p.pWidth/2;
				Y=p.pY+p.pHeight/2;
			}
		}
		direX=(X-bX)/Math.sqrt(distance);
		direY=(Y-bY)/Math.sqrt(distance);
	}
}
