package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileDao implements ImpleFileDao {
	private String path = "files";
	
	@Override
	public List<Files> readAll() {
		File f = new File(path);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		List<Files> list = new ArrayList<Files>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "GB2312"));
			Gson gson = new Gson();
			list = gson.fromJson(br.readLine(), new TypeToken<List<Files>>(){}.getType());	
			br.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;				
	}

	@Override
	public boolean writeAll(List<Files> list) {
		boolean flag = false;
		Gson gson = new Gson();
		String str = gson.toJson(list);
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "GB2312"));
			bw.write(str);
			bw.close();
			flag = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
