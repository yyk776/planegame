package Files;

import java.util.List;

public interface ImpleFileService {
	boolean insertFile(String filename);
	public void selectAll();
	boolean storage();
	List<String> getAllFilesName();
	boolean updatePlanes(int planeId);
	boolean updateHonors(int HonorId);
	boolean updateCharpter(int CharpterId);
	int[] readPlanes();
	int[] readCharpters();
	int[] readHonors();
	void selectFilebyName(String name);
	int dolottery();
}
