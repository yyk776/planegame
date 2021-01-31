package game;

import java.util.*;

import Files.FileService;
import Files.ImpleFileService;

public class commandLine {
	public static void main (String[] args) throws Exception {
		ImpleFileService ifs = new FileService();
		ifs.selectAll();
		if (ifs.getAllFilesName() == null) {
			System.out.println("当前没有存档，即将开始新游戏");
			System.out.println("请为新游戏输入一个存档名");
			Scanner step0 = new Scanner(System.in);	
			String zero = step0.next();
			step0.close();
			ifs.insertFile("zero");
			ifs.selectFilebyName("zero");
			ifs.updateCharpter(0);
			ifs.updatePlanes(0);
			ifs.storage();
			Battlefield.hurdle = 0;
			ImplePlaneTypeService ipts = new PlaneTypeService();
			planetype a = ipts.selectPlanebyId(0);
			Battlefield f = new Battlefield(Battlefield.hurdle);
			f.gamebegin();	
		}
		else if (ifs.getAllFilesName() != null) {
			System.out.println("请选择 开始新游戏（n）还是 读取旧存档（o）");
			Scanner step1 = new Scanner(System.in);
			String first = step1.next();
			if (first.equals("n")) {
				System.out.println("请为新游戏输入一个存档名");
				String second = step1.next();
				ifs.insertFile("second");
				ifs.selectFilebyName("second");
				ifs.updateCharpter(0);
				ifs.updatePlanes(0);
				ifs.storage();
				Battlefield.hurdle = 0;
				ImplePlaneTypeService ipts = new PlaneTypeService();
				planetype a = ipts.selectPlanebyId(0);
				Battlefield f = new Battlefield(Battlefield.hurdle);
				f.gamebegin();
			}
			else if (first.equals("o")) {
				System.out.println("目前的存档有：" + ifs.getAllFilesName());
				System.out.println("请输入您要加载的存档名");
				String chosenFile = step1.next();
				ifs.insertFile("chosenFile");
				ifs.selectFilebyName("chosenFile");
				System.out.println("该存档已解锁的关卡有：" + ifs.readCharpters());
				System.out.println("该存档已解锁的飞机有：" + ifs.readPlanes());
				System.out.println("请输入您要进入的关卡：");
				int chosenChapter = step1.nextInt();
				Battlefield.hurdle = chosenChapter;
				System.out.println("请输入您要控制的飞机：");
				int chosenPlane = step1.nextInt();
				ImplePlaneTypeService ipts = new PlaneTypeService();
				planetype a = ipts.selectPlanebyId(chosenPlane);
				Battlefield f = new Battlefield(Battlefield.hurdle);
				f.gamebegin();
			}
		}
	}
}
