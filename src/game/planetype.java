package game;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;

public class planetype implements Serializable{
	private static final long serialVersionUID = 1L;
		double speed;
		int life;
		int armor;
		int plane_id;
		String pimagedir;
		transient Image pimage;
		Bullettype bullet;
		
		planetype(double speed, int life, int armor, int planeid, String pimagepath, int bulletid, int bulletpower, double bulletspeed, String bimagepath){
			this.speed = speed;
			this.life = life;
			this.armor = armor;
			this.plane_id = planeid;
			this.pimagedir = pimagepath;
			this.bullet = new Bullettype(bulletid, bulletpower, bulletspeed, bimagepath);
			this.pimage = Toolkit.getDefaultToolkit().getImage(pimagepath);
		}
		
		public int getlife() {
			return life;
		}
		
		public double getspeed() {
			return speed;
		}
		
		public int gearmor() {
			return armor;
		}
		
		public int getPlaneId() {
			return plane_id;
		}
		
		
		
		public Bullettype getBullettype() {
			return bullet;
		}
		
		public Image getImage() {			
			this.pimage = Toolkit.getDefaultToolkit().getImage(pimagedir);
			System.out.println("飞机"+plane_id+pimage);
			this.bullet.getbimage();
			return this.pimage;
		}
}
