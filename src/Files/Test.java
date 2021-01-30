package Files;

public class Test {
	public static void main(String[] args) {
		// 新建一个FileService层
		ImpleFileService ifs = new FileService(); 	
		// 读取所有存档，放在内存中
		ifs.selectAll();
		// 插入存档 a2
		ifs.insertFile("a2");
		// 将a2 中下标为三的关卡设为1（通关）
		ifs.updateCharpter("a2", 3, true);
		// 将a2 中下标为三的荣誉设为1 （激活）
		ifs.updateHonors("a2", 3, true);
		// 将a2 中下标为三的飞机设为1（拥有）
		ifs.updatePlanes("a2",3, true);
		// 将存档写回硬盘
		ifs.storage();
		// 读取a2下标为0-3的关卡状态
		System.out.println(ifs.readCharpters("a2")[0]);
		System.out.println(ifs.readCharpters("a2")[1]);
		System.out.println(ifs.readCharpters("a2")[2]);
		System.out.println(ifs.readCharpters("a3")[3]);
		// 读取a2下标为3的荣誉状态
		System.out.println(ifs.readHonors("a2")[3]);
		// 读取a2下标为3的飞机状态
		System.out.println(ifs.readPlanes("a2")[3]);
		ifs.storage();
		//读取所有存档的名字，返回List<String>
		System.out.println(ifs.getAllFilesName());
	}
}
