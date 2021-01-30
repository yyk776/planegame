package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlanetypeDao implements ImplePlaneTypeDao{
	private String path = "planes";
	
	
	@Override
	public List<planetype> readAll() {
		File f = new File(path);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		List<planetype> list = new ArrayList<planetype>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "GB2312"));
			Gson gson = new Gson();
			list = gson.fromJson(br.readLine(), new TypeToken<List<planetype>>(){}.getType());	
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
	public boolean writeAll(List<planetype> list) {
		File f = new File(path);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
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

