package com.progekt.webtools;

import android.widget.TextView;
import android.widget.EditText;

public class PortScanTask extends MyTask {

	PortScanTask(MainActivity Msct) {
		super(Msct);
	}
	 @Override
	    
	    protected Void doInBackground(String... params) {
	 EditText HostName = (EditText) MAcive.findViewById(R.id.editHostName);
		 EditText StartPort = (EditText) MAcive.findViewById(R.id.editStartPort);
		 EditText EndPort = (EditText) MAcive.findViewById(R.id.editEndPort);
		 String HostName1 = HostName.getText().toString();
		 String StartPort1 = StartPort.getText().toString();
		 String EndPort1 = EndPort.getText().toString();

		 MAcive.AddText(MAcive.PortScan(HostName1, StartPort1, EndPort1));
//		 MAcive.AddText("\n Еще в процессе \n");
		return null;
	}
}
