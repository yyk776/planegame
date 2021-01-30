package Files;

import java.util.List;

public interface ImpleFileService {
	boolean insertFile(String filename);
	boolean updatePlanes(String filename, int planeId, boolean b);
	boolean updateHonors(String filename, int HonorId, boolean b);
	boolean updateCharpter(String filename, int CharpterId, boolean b);
	public void selectAll();
	Files selectFilebyName(String name);
	int[] readPlanes(String filename);
	int[] readHonors(String filename);
	int[] readCharpters(String filename);
	boolean storage();
	List<String> getAllFilesName();
}
