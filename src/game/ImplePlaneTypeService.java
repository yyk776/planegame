package game;

import java.awt.Image;

public interface ImplePlaneTypeService {

	void selectAll();

	boolean insertPlaneType(double speed, int life, int armor, int planeid, String pimagepath, int bulletid,
			int bulletpower, double bulletspeed, String bimagepath);

	boolean storage();

	planetype selectPlanebyId(int PlaneId);

	int getplife();

	double getpspeed();

	int getparmor();

	int getpPlaneId();

	Bullettype getpBullettype();

	Image getpImage();

	int getbid();

	int getbpower();

	double getbspeed();

	Image getbimage();

}
