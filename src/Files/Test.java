package Files;

public class Test {
	public static void main(String[] args) {
		// 新建一个FileService层
		ImpleFileService ifs = new FileService(); 	
		// 读取所有存档，放在内存中
		/*ifs.selectAll();
		//读取所有存档的名字，返回List<String>
		System.out.println(ifs.getAllFilesName());
		// 新建存档 a5
		ifs.insertFile("a5");
		//选择存档a3
		ifs.selectFilebyName("a3");
		// 将当前存档中下标为3的关卡设为1（通关）
		ifs.updateCharpter(3);
		// 将当前存档中下标为3的荣誉设为1 （激活）
		ifs.updateHonors(3);
		// 将当前存档中下标为3三的飞机设为1（拥有）*/
		ifs.selectFilebyName("abc");
		// 将存档写回硬盘
		//ifs.updateCharpter(1);
		//ifs.storage();
		//ifs.selectAll();
		System.out.println(ifs.readCharpters()[0]);
		// 读取当前存档中下标为0-3的关卡状态
		/*System.out.println(ifs.readCharpters()[0]);
		System.out.println(ifs.readCharpters()[1]);
		System.out.println(ifs.readCharpters()[2]);
		System.out.println(ifs.readCharpters()[3]);
		// 读取当前存档中下标为3的荣誉状态
		System.out.println(ifs.readHonors()[3]);
		// 读取当前存档中下标为3的飞机状态
		System.out.println(ifs.readPlanes()[3]);
		//将此前修改写入硬盘
		ifs.storage();
		System.out.println(ifs.getAllFilesName());
		System.out.println(ifs.dolottery());*/
	}
}
