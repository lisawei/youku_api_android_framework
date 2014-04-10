package com.android.intro.moiveslist.models;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class PlayModel extends BaseModel {

	String Path = "v3/play/address";

	public PlayModel(Context context) {
		super(context);
	}

	public void get_playurl(String vid, final HttpCallBackHandler callbackhandler) {

		String MoivesUrl = String.format("http://%s/%s", Host, Path);
		Log.d("URL", MoivesUrl);

		RequestParams params = new RequestParams();
		params.put("id", vid);
		params.put("pid", PID);
		params.put("guid", GUID);
		params.put("format", "6");

		Header[] headers = {
				new BasicHeader("Content-type", "application/json"),
				new BasicHeader("Accept", "text/html,text/xml,application/xml"),
				new BasicHeader("User-Agent", UserAgent) };

		client.get(this.context, MoivesUrl, headers, params,
				new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject document) {
						JSONObject results;
						JSONObject playinfo = null;
						ArrayList<String> urls;
						ArrayList<HashMap<String, String>> datas = new ArrayList<HashMap<String, String>>();

						try {
							results = document.getJSONObject("results");
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							return;
						}
						String key=null;

						if (results.has("m3u8")) {
							key = "m3u8";
						}else if (results.has("m3u8_mp4")) {
							key = "m3u8_mp4";
						} 
						
						JSONArray data;
						try {
							data = results.getJSONArray(key);
							playinfo = (JSONObject) data.get(0);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						HashMap<String, Object> resultHash = new HashMap<String, Object>();
						resultHash.put("playinfo", playinfo);
						callbackhandler.onSuccess(resultHash);

					}
				});
	}

}
