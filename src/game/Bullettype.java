package game;
import java.awt.*;
import java.io.*;
public class Bullettype implements Serializable {
	private static final long serialVersionUID = 1L;
	int id;
	int power;
	String bimagedir;
	transient Image bimage;
	double speed;
	
	public Bullettype(int id,int power_in,double speed_in,Image bimage_in){
		super();
		this.id=id;
		power=power_in;
		bimage=bimage_in;
		speed=speed_in;
	}
	
	public Bullettype(int id,int power_in,double speed_in,String imagepath){
		super();
		this.id=id;
		power=power_in;
		bimagedir = imagepath;
		bimage = Toolkit.getDefaultToolkit().getImage(imagepath);
		speed=speed_in;
	}
	
	public int getbid() {
		return this.id;
	}
	
	public int getbpower() {
		return this.power;
	}
	
	public double getbspeed() {
		return this.speed;
	}
	
	public Image getbimage() {
		this.bimage = Toolkit.getDefaultToolkit().getImage(bimagedir);
		System.out.println("子弹"+id+bimage);
		return this.bimage;
	}
}