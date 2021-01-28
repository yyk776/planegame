package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FileDao implements ImpleFileDao {
	private String path = "./file.properties";
	
	@Override
	public List<File> readAll() {
		List<File> list = new ArrayList<File>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "GB2312"));
			JSONArray jsonarr = JSONArray.parseArray(br.readLine());
			list = jsonarr.toJavaList(File.class);
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
	public boolean writeAll(List<File> list) {
		boolean flag = false;
		String jsonstr = JSONObject.toJSONString(list);
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "GB2312"));
			bw.write(jsonstr);
			bw.close();
			flag = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String generateId() {
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
		return sb.toString();				
	}

}
