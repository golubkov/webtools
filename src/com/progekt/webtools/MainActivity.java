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
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;


public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	List<View> views;
	private TextView PortScanView;
	private TextView WhoisView;
	public native void Whois(String S);
	public native void PortScan(String Host,String Sport, String Eport);
	private CharSequence  WhoisViewTextSave;
	private CharSequence  PortScanViewTextSave;  
	private static final int alert_none1 = 0; // параметры введены верно
	private static final int alert_scanporthost1 = 1; // некорректный ввод Host or IP
	private static final int alert_scanportsport1 = 2; // некорректный ввод Start Port
	private static final int alert_scanporteport1 = 3; // некорректный ввод End Port
	private static final int alert_scanportdifference1 = 4; // некорректная последовательность портов
	private static final int alert_scanportlenght1 = 5; // некорректный Range портов
	private static final int alert_whoiscorrect1 = 6; // некорректный Range портов
  
		final String LOG_TAG = "myLogs";
		int cnt = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	// возврат кодов ошибок UI
	private int checkInputParametersportscan(String psh, int pssp, int psep)
			{
	  			if (psh.length() == 0)
	  			{
	  				return alert_scanporthost1;
	  			}
	  			if (pssp==0)
	  			{
	  				return alert_scanportsport1;
	  			}
	  			if (psep==0)
	  			{
	  				return alert_scanporteport1;
	  			}
	  			if (pssp > psep)
	  			{
	  				return alert_scanportdifference1;
	  			}
	  			if (0 > pssp || pssp > 65537 || 0 > psep || psep > 65537)
	  			{
	  				return alert_scanportlenght1;
	  			}
	  			return alert_none1;
			}
	private int checkInputParameterswhois(String whoish)
				{
					if (whoish.length() == 0)
					{
						return alert_scanporthost1;
					}	  
					if (!whoish.contains("."))
					{
						return alert_whoiscorrect1;
					}
					return alert_none1;
				}
// не используется, но пусть будет
@Override
	 protected void onRestoreInstanceState(Bundle savedInstanceState) {
	 PortScanView = (TextView) findViewById(R.id.textView15);
	 WhoisView = (TextView) findViewById(R.id.textView16);
	 WhoisView.setText(WhoisViewTextSave);
	 PortScanView.setText(PortScanViewTextSave);
	 super.onRestoreInstanceState(savedInstanceState);
	 PortScanViewTextSave=savedInstanceState.getCharSequence("PortScanText");
	 WhoisViewTextSave=savedInstanceState.getCharSequence("WhoisText");
	  }	
	
@Override
	   protected void onSaveInstanceState(Bundle outState) {
	   PortScanView = (TextView) findViewById(R.id.textView15);
	   WhoisView = (TextView) findViewById(R.id.textView16);
	   super.onSaveInstanceState(outState);
	   WhoisViewTextSave=WhoisView.getText();
	   PortScanViewTextSave=PortScanView.getText();
	   outState.putCharSequence("PortScanText",PortScanViewTextSave);
	   outState.putCharSequence("WhoisText",WhoisViewTextSave);
	  }
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
      		int SPort = Integer.parseInt(StartPort.getText().toString());
      		int Eport = Integer.parseInt(EndPort.getText().toString());
      		int alertCode = checkInputParametersportscan(HostName1, SPort, Eport);
      	
      	if (alertCode != alert_none1)
      		{
      			showDialog(alertCode);
      			return;
      		}
      
      	new Thread(new Runnable(){
        public void run()
        		{
        			PortScan(HostName1,StartPort1,EndPort1);
        		}
      		}).start();
       break;
      case R.id.Whois_button:
    	  EditText HostNameW = (EditText) findViewById(R.id.editHostNameW);
    	  final String HostNameW1 = HostNameW.getText().toString();
    	  WhoisView = (TextView) findViewById(R.id.textView16);
    	  int alertCode2 = checkInputParameterswhois(HostNameW1);

    	  if (alertCode2 != alert_none1)
    	  	{
    		  	showDialog(alertCode2);
    		  	return;
    	  	}
      
    	  new Thread(new Runnable(){
    		  	public void run(){
    		  		Whois(HostNameW1);
    	  		}
    	  	}).start();
      break;
    }
  }
  @Override
  		protected Dialog onCreateDialog(int id)
  			{
      		DialogInterface.OnClickListener doNothing = new DialogInterface.OnClickListener()
      		{
      			public void onClick(DialogInterface dialog, int whichButton) 
      			{
      				}
      		};      
      		int alertMessage;
      		switch (id)
      		{
      			case alert_scanporthost1:
      				alertMessage = R.string.alert_scanporthost;
      			break;
      			case alert_scanportsport1:
      				alertMessage = R.string.alert_scanportsport;
      			break;
      			case alert_scanporteport1:
      				alertMessage = R.string.alert_scanporteport;
      			break;
      			case alert_scanportlenght1:
      				alertMessage = R.string.alert_scanportlenght;
      			break;
      			case alert_scanportdifference1:
      				alertMessage = R.string.alert_scanportdifference;
      			break;  
      			case alert_whoiscorrect1:
      				alertMessage = R.string.alert_whoiscorrect;
      			break; 
      			default:
      				return null;
      		}
  
  return new AlertDialog.Builder(this)
  		.setMessage(alertMessage)
  		.setNeutralButton(R.string.ok, doNothing)
  		.create(); 
  			}
  
  public void AddPortSanText(String S)
  		{
	  		final String value = S;
	  		runOnUiThread(new Runnable() {
	  			@Override
	  				public void run(){
	  						PortScanView.setText(value);
	  						PortScanViewTextSave=PortScanView.getText();
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
	  								}
	  									});
  		}
  static {
	  		System.loadLibrary("hello-jni");
  		}
}