package com.progekt.webtools;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;
import android.util.Log;

import com.progekt.webtools.viewpagerindicator.TitlePageIndicator;


public class MainActivity extends Activity {
  /** Called when the activity is first created. */
  List<View> views;
  //private TextView tvHello;
  private TextView PortScanView;
  private TextView WhoisView;
  public native void Whois(String S);
  public native void PortScan(String Host,String Sport, String Eport);
  private CharSequence  WhoisViewTextSave;
  private CharSequence  PortScanViewTextSave;
//new
  final String LOG_TAG = "myLogs";
  int cnt = 0;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //new

    Log.d(LOG_TAG, "onCreate");


  //  tvHello = (TextView) findViewById(R.id.textView15);
    PortScanView = (TextView) findViewById(R.id.textView15);
    WhoisView = (TextView) findViewById(R.id.textView16);
    if(savedInstanceState!=null)
    {
      PortScanView.setText(PortScanViewTextSave);
      WhoisView.setText(WhoisViewTextSave);
    }
    LayoutInflater inflater = LayoutInflater.from(this);
    views = new ArrayList<View>();

    View page = inflater.inflate(R.layout.first, null);
    views.add(page);

    page = inflater.inflate(R.layout.second, null);
    views.add(page);

    MyPageAdapter adapter = new MyPageAdapter(views);
    ViewPager pager = (ViewPager)findViewById( R.id.viewpager );
    TitlePageIndicator indicator = (TitlePageIndicator)findViewById( R.id.indicator );
    pager.setAdapter( adapter );
    indicator.setViewPager( pager );
}
  //meganew


  private int checkInputParametersportscan(String psh, int pssp, int psep)
  {
	  	if (psh.length() != 0)
	  	{
	  		return alert_scanporthost;
	  	}
	  	if (pssp==0)
	  	{
	  		return alert_scanportsport;
	  	}
	  	if (psep==0)
	  	{
	  		return alert_scanporteport;
	  	}
	  	if (pssp<psep)
	  	{
	  		return alert_scanportdifference;
	  	}
	  	if (0 < pssp &  pssp<65536 & 0 <psep & psep<65536)
	  	{
	  		return alert_scanportlenght;
	  	}
      return ALERT_NONE;
  }






  //new





// protected void onDestroy() {
//   super.onDestroy();
//   Log.d(LOG_TAG, "onDestroy");
// }

// protected void onPause() {
//   super.onPause();
//   Log.d(LOG_TAG, "onPause");
// }

// protected void onRestart() {
//   super.onRestart();
//   Log.d(LOG_TAG, "onRestart");
// }
@Overide
protected void onRestoreInstanceState(Bundle savedInstanceState) {
  // PortScanView = (TextView) findViewById(R.id.textView15);
  // WhoisView = (TextView) findViewById(R.id.textView16);
  // WhoisView.setText(WhoisViewTextSave);
  // PortScanView.setText(PortScanViewTextSave);
  super.onRestoreInstanceState(savedInstanceState);
  PortScanViewTextSave=savedInstanceState.getCharSequence("PortScanText");
  WhoisViewTextSave=savedInstanceState.getCharSequence("WhoisText");
  Log.d(LOG_TAG, "onRestoreInstanceState");
}

// protected void onResume() {
//   super.onResume();
//   Log.d(LOG_TAG, "onResume ");
// }
@Overide
protected void onSaveInstanceState(Bundle outState) {
  // PortScanView = (TextView) findViewById(R.id.textView15);
  // WhoisView = (TextView) findViewById(R.id.textView16);
  super.onSaveInstanceState(outState);
  outState.putCharSequence("PortScanText",PortScanViewTextSave);
  outState.putCharSequence("WhoisText",WhoisViewTextSave);

  // WhoisViewTextSave=WhoisView.getText();
  // PortScanViewTextSave=PortScanView.getText();
  // Log.d(LOG_TAG, "onSaveInstanceState");
}

// protected void onStart() {
//   super.onStart();
//   Log.d(LOG_TAG, "onStart");
// }
//
// protected void onStop() {
//   super.onStop();
//   Log.d(LOG_TAG, "onStop");
// }







  public void onClick(View v) {
    switch (v.getId())
    {
      case R.id.PortScan_button:
      EditText HostName = (EditText) findViewById(R.id.editHostName);
      EditText StartPort = (EditText) findViewById(R.id.editStartPort);
      EditText EndPort = (EditText) findViewById(R.id.editEndPort);
      final String HostName1 = HostName.getText().toString();
      final String StartPort1 = StartPort.getText().toString();
      final String EndPort1 = EndPort.getText().toString();
      PortScanView = (TextView) findViewById(R.id.textView15);
    //  tvHello = (TextView) findViewById(R.id.textView15);
      new Thread(new Runnable(){
        public void run(){
          PortScan(HostName1,StartPort1,EndPort1);
          //AddText();
        }
      }).start();
      // PortScanTask ptask=new PortScanTask(this);
      // ptask.execute();
      break;
      case R.id.Whois_button:
      EditText HostNameW = (EditText) findViewById(R.id.editHostNameW);
      final String HostNameW1 = HostNameW.getText().toString();
      WhoisView = (TextView) findViewById(R.id.textView16);
    //  tvHello = (TextView) findViewById(R.id.textView16);
      new Thread(new Runnable(){
        public void run(){
          Whois(HostNameW1);
          //AddText();
        }
      }).start();
      // PortScanTask ptask=new PortScanTask(this);
      // ptask.execute();
      break;
    }
  }

  public void AddPortSanText(String S)
  {
    final String value = S;
    runOnUiThread(new Runnable() {
      @Override
      public void run(){
        PortScanView.setText(value);
        PortScanViewTextSave=PortScanView.getText();
        //  tvHello.setText(value);
      }
    });
  }
  public void AddWhoisText(String S)
  {
    final String value = S;
    runOnUiThread(new Runnable() {
      @Override
      public void run(){
        WhoisView.setText(value);
        WhoisViewTextSave=WhoisView.getText();
        //  tvHello.setText(value);
      }
    });
  }

  static {
    System.loadLibrary("hello-jni");
  }



}
