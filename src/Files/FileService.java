package Files;

import java.util.List;

public class FileService implements ImpleFileService{
	private ImpleFileDao dao = new FileDao();

	@Override
	public boolean insertFile(File f) {
		List<File> list = dao.readAll();
		for(File file : list) {
			if(file.getFileName().equals(f.getFileName()) || file.getFileId().equals(f.getFileId())) return false;
		}		
		list.add(f);
		return dao.writeAll(list);		
	}

	@Override
	public boolean updatePlanes(String filename, int planeId, boolean b) {
		List<File> list = dao.readAll();
		File f = selectFilebyName(list, filename);
		if (f == null) return false;
		else f.setFilePlanes(planeId, b);
		return dao.writeAll(list);		
	}

	@Override
	public boolean updateHonors(String filename, int HonorId, boolean b) {
		List<File> list = dao.readAll();
		File f = selectFilebyName(list, filename);
		if (f == null) return false;
		else f.setFilePlanes(HonorId, b);
		return dao.writeAll(list);	
	}

	@Override
	public boolean updateCharpter(String filename, int CharpterId, boolean b) {
		List<File> list = dao.readAll();
		File f = selectFilebyName(list, filename);
		if (f == null) return false;
		else f.setFilePlanes(CharpterId, b);
		return dao.writeAll(list);	
	}

	@Override
	public List<File> selectAll() {
		return dao.readAll();
	}
	
	@Override
	public File selectFilebyName(String name) {
		File f = null;
		List<File> list = dao.readAll();
		for(File file : list) {
			if(file.getFileName().equals(name)) {
				f = file;
				break;						
			}
		}
		return f;		
	}
	
	public File selectFilebyName(List<File> list, String name) {
		File f = null;
		for(File file : list) {
			if(file.getFileName().equals(name)) {
				f = file;
				break;						
			}
		}
		return f;
	}
	
}	