package game;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import Files.Files;

public class PlaneTypeService implements ImplePlaneTypeService{
	private ImplePlaneTypeDao ipdao = new PlanetypeDao();
	List<planetype> list = new ArrayList<planetype>();
	planetype currplanetype;
	
	PlaneTypeService(){
		super();
		selectAll();
	}
	
	@Override
	public void selectAll() {
		if(ipdao.readAll() != null) this.list = ipdao.readAll();
		for (planetype p : list) {
			p.getImage();
		}
	}	

	@Override
	public boolean insertPlaneType(double speed, int life, int armor, int planeid, String pimagepath, int bulletid, int bulletpower, double bulletspeed, String bimagepath) {
		planetype newplane = new planetype(speed,life,armor,planeid,pimagepath,bulletid,bulletpower,bulletspeed,bimagepath);
		list.add(newplane);	
		this.currplanetype = newplane;
		return true;
	}

	@Override
	public int getplife() {
		return currplanetype.getlife();
	}

	@Override
	public double getpspeed() {
		return currplanetype.getspeed();
	}

	@Override
	public int getparmor() {
		return currplanetype.gearmor();
	}

	@Override
	public int getpPlaneId() {
		return currplanetype.getPlaneId();
	}
	
	@Override
	public Bullettype getpBullettype() {
		return currplanetype.getBullettype();
	}

	@Override
	public Image getpImage() {
		return currplanetype.getImage();
	}

	@Override
	public int getbid() {
		return getpBullettype().getbid();
	}

	@Override
	public int getbpower() {
		return getpBullettype().getbpower();
	}

	@Override
	public double getbspeed() {
		return getpBullettype().getbspeed();
	}

	@Override
	public Image getbimage() {
		return getpBullettype().getbimage();
	}
	
	@Override
	public boolean storage() {
		ipdao.writeAll(list);
		return false;
	}
	
	@Override
	public planetype selectPlanebyId(int PlaneId) {
		for(planetype p : list) {
			if(p.getPlaneId() == PlaneId) {
				this.currplanetype = p;
				break;						
			}
		}
		return this.currplanetype;
	}
	
	

}
