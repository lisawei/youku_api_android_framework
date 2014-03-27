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

	

	public PlayModel(Context context) {
		super(context);
	}

	public void get_playurl(String vid, final HttpCallBackHandler callbackhandler) {

		
	}

}
