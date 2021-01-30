package Files;

import java.util.List;

public class FileService implements ImpleFileService{
	private ImpleFileDao dao = new FileDao();
	List<Files> list;
	
	@Override
	public void selectAll() {
		this.list = dao.readAll();
	}

	@Override
	public boolean insertFile(String filename) {
		for(Files file : list) {
			if(file.getFileName().equals(filename)) {
				System.out.println("存档已存在");
				return false;
			}
		}		
		list.add(new Files(filename));	
		return true;
	}

	@Override
	public boolean updatePlanes(String filename, int planeId, boolean b) {
		Files f = selectFilebyName(filename);
		if (f == null) return false;
		else f.setFilePlanes(planeId, b);
		return true;		
	}

	@Override
	public boolean updateHonors(String filename, int HonorId, boolean b) {
		Files f = selectFilebyName(filename);
		if (f == null) return false;
		else f.setFileHonors(HonorId, b);
		return true;	
	}

	@Override
	public boolean updateCharpter(String filename, int CharpterId, boolean b) {
		Files f = selectFilebyName(filename);
		if (f == null) return false;
		else f.setFileCharpters(CharpterId, b);
		return true;	
	}

	@Override
	public Files selectFilebyName(String name) {
		Files f = null;
		for(Files file : list) {
			if(file.getFileName().equals(name)) {
				f = file;
				break;						
			}
		}
		return f;		
	}

	@Override
	public int[] readPlanes(String filename) {
		Files f = selectFilebyName(filename);	
		if (f == null) return null;
		return f.getFilePlanes();
	}

	@Override
	public int[] readHonors(String filename) {
		Files f = selectFilebyName(filename);	
		if (f == null) return null;
		return f.getFileHonors();
	}

	@Override
	public int[] readCharpters(String filename) {
		Files f = selectFilebyName(filename);	
		if (f == null) return null;
		return f.getFileCharpters();
	}

	@Override
	public boolean storage() {
		dao.writeAll(list);
		return false;
	}	
}	