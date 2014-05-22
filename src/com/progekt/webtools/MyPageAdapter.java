package com.progekt.webtools;

import java.util.ArrayList;
import java.util.List;

import com.progekt.webtools.viewpagerindicator.TitleProvider;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

public class MyPageAdapter extends PagerAdapter implements TitleProvider{
	
	List<View> views = null;

	private static String[] titles = new String[]
		    {
		        "PortScan",
		        "WhoIs"
		    };
		 
		    public MyPageAdapter(List<View> inViews )
		    {
		    	views = inViews;
		    }
		 
		    public String getTitle( int position )
		    {
		        return titles[ position ];
		    }
		 
		    @Override
		    public int getCount()
		    {
		        return titles.length;
		    }
		 
		    @Override
		    public Object instantiateItem( View pager, int position )
		    {
		        View v = views.get(position );
		        ((ViewPager)pager).addView( v, 0);
		        return v;
		    }
		 
		    @Override
		    public void destroyItem( View pager, int position, Object view )
		    {
		        ((ViewPager)pager).removeView( (View)view );
		    }
		 
		    @Override
		    public boolean isViewFromObject( View view, Object object )
		    {
		        return view.equals( object );
		    }
		 
		    @Override
		    public void finishUpdate( View view ) {}
		 
		    @Override
		    public void restoreState( Parcelable p, ClassLoader c ) {}
		 
		    @Override
		    public Parcelable saveState() {
		        return null;
		    }
		 
		    @Override
		    public void startUpdate( View view ) {}
    
}