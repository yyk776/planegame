package game;
import java.awt.*;
import java.io.*;
public class Bullettype implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	int power;
	transient Image bimage;
	double speed;
	
	public Bullettype(int power_in,Image bimage_in){
		super();
		power=power_in;
		bimage=bimage_in;
	}
	public Bullettype(int id,int power_in,double speed_in,Image bimage_in){
		super();
		this.id=id;
		power=power_in;
		bimage=bimage_in;
		speed=speed_in;
	}
}