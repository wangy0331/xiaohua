package com.test.xiaohualist.util;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.test.xiaohualist.bean.Xiaohua;

public class JsonParser {
	
	private static String imageUri = "http://pica.nipic.com/2007-11-09/200711912453162_2.jpg";
	
	public static List<Xiaohua> parseJSONWithJSONObject(String response) {
		List<Xiaohua> list = null;
		try {
			JSONArray jsonArray = new JSONObject(response).getJSONArray("detail");
			list = new ArrayList<Xiaohua>();
			for (int i = 0; i < jsonArray.length(); i++) {
				Xiaohua xiaohua = new Xiaohua();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				xiaohua.setId(jsonObject.getString("id"));
				xiaohua.setContent(jsonObject.getString("content"));
				xiaohua.setImage(jsonObject.getString("picUrl"));
				list.add(xiaohua);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
