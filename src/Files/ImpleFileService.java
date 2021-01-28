package Files;

import java.util.List;

public interface ImpleFileService {
	boolean insertFile(File f);
	boolean updatePlanes(String filename, int planeId, boolean b);
	boolean updateHonors(String filename, int HonorId, boolean b);
	boolean updateCharpter(String filename, int CharpterId, boolean b);
	List<File> selectAll();
	File selectFilebyName(String name);
}
