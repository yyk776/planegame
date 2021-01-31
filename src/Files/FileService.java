package Files;

import java.util.ArrayList;
import java.util.List;

public class FileService implements ImpleFileService{
	private ImpleFileDao dao = new FileDao();
	List<Files> list = new ArrayList<Files>();
	Files currfile;
	
	public FileService(){
		super();
		selectAll();	
	}
	
	@Override
	public void selectAll() {
		if(dao.readAll() != null) this.list = dao.readAll();
	}	

	@Override
	public boolean insertFile(String filename) {
		for(Files file : list) {
			if(file.getFileName().equals(filename)) {
				System.out.println("存档已存在");
				return false;
			}
		}
		Files newfile = new Files(filename);
		list.add(newfile);	
		this.currfile = newfile;
		return true;
	}

	@Override
	public boolean updatePlanes(int planeId) {		
		if (currfile == null) return false;
		else currfile.setFilePlanes(planeId);
		return true;		
	}

	@Override
	public boolean updateHonors(int HonorId) {
		if (currfile == null) return false;
		else currfile.setFileHonors(HonorId);
		return true;	
	}

	@Override
	public boolean updateCharpter(int CharpterId) {
		if (currfile == null) return false;
		else currfile.setFileCharpters(CharpterId);
		return true;	
	}

	@Override
	public int[] readPlanes() {
		if (currfile == null) return null;
		return currfile.getFilePlanes();
	}

	@Override
	public int[] readHonors() {
		if (currfile == null) return null;
		return currfile.getFileHonors();
	}

	@Override
	public int[] readCharpters() {
		if (currfile == null) return null;
		return currfile.getFileCharpters();
	}

	@Override
	public boolean storage() {
		dao.writeAll(list);
		return false;
	}

	@Override
	public List<String> getAllFilesName() {
		if (list == null) return null;
		List<String> names = new ArrayList<String>();
		for(Files file : list) {
			names.add(file.getFileName());		
			}
		return names;
	}
	
	@Override
	public void selectFilebyName(String name) {
		for(Files file : list) {
			if(file.getFileName().equals(name)) {
				this.currfile = file;
				break;						
			}
		}
	}

}	