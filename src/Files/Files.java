package Files;

import java.io.Serializable;
import java.util.Random;

public class Files implements Serializable{
	private static final long serialVersionUID= 1L;
	private String FileId;
	private String FileName;
	private int [] FilePlanes =new int[10];
	private int [] FileHonors = new int[10];
	private int [] FileCharpters = new int[4] ;
	
	public Files() {
		super();
		setFilePlanes(0);
	}
	
	public Files(String fileName) {
		super();
		setFileId();
		this.FileName = fileName;
		setFilePlanes(0);
	}
	
	public String getFileId() {
		return FileId;
	}
	
	public void setFileId(String fileId) {
		this.FileId = fileId;
	}
	
	public void setFileId(){
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		char[] numbers = new char[]
				{'0', '1', '2', '3', '4', '5',
				'6', '7', '8', '9', 'a', 'b', 
				'c', 'd', 'e', 'f', 'g', 'h', 
				'i', 'j', 'k', 'l', 'm', 'n',
				'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z'};
		for (int i = 0; i < numbers.length; i++) {
			sb.append(numbers[r.nextInt(numbers.length)]);
		}
		this.FileId = sb.toString();		
	}
	
	public String getFileName() {
		return FileName;
	}
	
	public void setFileName(String fileName) {
		this.FileName = fileName;
	}
	
	public int [] getFilePlanes() {
		return FilePlanes;
		
	}
	
	public void setFilePlanes(int i) {
		this.FilePlanes[i] = 1;
	}
	
	public int [] getFileHonors() {
		return FileHonors;
	}
	
	public void setFileHonors(int i) {
		this.FileHonors[i] = 1;
	}
	
	public int [] getFileCharpters() {
		return FileCharpters;		
	}
	
	public void setFileCharpters(int i) {
		System.out.println("setcharpters!!!");
		this.FileCharpters[i-1] = 1;
	}
}
	
	
	

