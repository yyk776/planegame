package game;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;




public class TestPlane {
	public static void main(String[] args) throws IOException {
		ImplePlaneTypeService ipts = new PlaneTypeService();
		//下面是添加飞机的记录，已经添加完成十架飞机，不需要再次改动。
		ipts.insertPlaneType(2, 100, 5, 0, "Airplanes/airplane0.gif", 0,10,2.5,"Bullets/Bullet11.gif");
		ipts.insertPlaneType(1.5, 110, 8, 1, "Airplanes/airplane1.gif", 0,10,2.5,"Bullets/Bullet11.gif");
		ipts.insertPlaneType(4, 90, 3, 2, "Airplanes/airplane2.gif", 2,10,2.5,"Bullets/Bullet11.gif");
		ipts.insertPlaneType(3, 95, 4, 3, "Airplanes/airplane3.gif", 2,10,2.5,"Bullets/Bullet11.gif");
		ipts.insertPlaneType(5, 80, 2, 4,"Airplanes/airplane4.gif", 3,10,2,"Bullets/Bullet12.png");
		ipts.insertPlaneType(6, 70, 1, 5, "Airplanes/airplane20.gif", 3,10,2,"Bullets/Bullet12.png");
		ipts.insertPlaneType(1.3, 120, 10, 6, "Airplanes/airplane6.gif", 4,10,3, "Bullets/cj2.png");
		ipts.insertPlaneType(1.3, 190, 11, 7, "Airplanes/airplane7.gif", 4,10,3, "Bullets/cj2.png");
		ipts.insertPlaneType(1.3, 200, 12, 8, "Airplanes/airplane8.gif", 1,10,1,"Bullets/Bullet11.gif");
		ipts.insertPlaneType(1.2, 300, 13, 9, "Airplanes/airplane9.png",1,10,1,"Bullets/Bullet11.gif");
		ipts.storage(); 
		
		// 选择0号飞机
		//如果不需要返回飞机，也可以直接ipts.selectPlanebyId(飞机编号)
		//planetype a = ipts.selectPlanebyId(3);
		//获取当前飞机的护甲
		//System.out.println(ipts.getparmor());
		//获取当前飞机的生命
		//System.out.println(ipts.getplife());
		//获取当前飞机的速度
		//System.out.println(ipts.getpspeed());
		//获取当前飞机的图片
		//System.out.println(ipts.getpImage());
		//获取当前飞机的子弹id
		//System.out.println(ipts.getbid());
		//获取当前飞机的子弹威力
		//System.out.println(ipts.getbpower());
		//获取当前飞机的子弹速度
		//System.out.println(ipts.getbspeed());
		//获取当前飞机的子弹图片
		//System.out.println(ipts.getbimage());
	}

}
