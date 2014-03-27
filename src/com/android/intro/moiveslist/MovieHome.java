package com.android.intro.moiveslist;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.viewpagerindicator.TabPageIndicator;

public class MovieHome extends FragmentActivity {
    
    public static HashMap<Integer,String> channelTabs = new HashMap<Integer,String>();
	public static ArrayList<Integer> channels = new ArrayList<Integer>();
	ViewPager pager;
	ImageView gotoSearchBtn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_tabs);
        
        channelTabs.put(96, "电影");
		channelTabs.put(84, "纪录片");
		channelTabs.put(97, "电视剧");
		channelTabs.put(85, "综艺");
		channelTabs.put(91, "咨询");
		channelTabs.put(100, "动漫");
		channelTabs.put(87, "教育");
		
		channels.add(96);
		channels.add(97);
		channels.add(85);
		channels.add(100);
		channels.add(84);
		channels.add(87);
		channels.add(91);

        FragmentPagerAdapter adapter = new MovieChannelAdapter(getSupportFragmentManager(),this);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);
        

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        
        gotoSearchBtn = (ImageView) this.findViewById(R.id.gotoSearchBtn);
        gotoSearchBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), SearchActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.anim_slide_in_bottom,R.anim.anim_slide_out_top);
			}
        });
        
    }

    class MovieChannelAdapter extends FragmentPagerAdapter {
    	Context mContext;
        public MovieChannelAdapter(FragmentManager fm,Context context) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
        	//return TestFragment.newInstance("TestFragment");
            return MoiveChannelFragment.newInstance(mContext,channels.get(position % channels.size()));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Integer cid =  channels.get(position % channels.size());
            if(cid!=null){
            	return channelTabs.get(cid);
            }
            return "weizhi"; 
        }

        @Override
        public int getCount() {
          return channels.size();
        }
    }
}
