package com.progekt.webtools;


import android.widget.TextView;
import android.os.AsyncTask;



class MyTask extends AsyncTask<String, Object, Void> {
	MainActivity MAcive;
	//TextView tvHello;
	MyTask(MainActivity Msct){MAcive=Msct;}
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
     MAcive.AddText("\n Старт \n");
}
    
    @Override
    
    protected Void doInBackground(String... params) {

    	return null;
}

  	@Override
	protected void onProgressUpdate(Object... values) {
		MAcive.AddText("\n Еще в процессе \n");
}
 	
    @Override
  protected void onPostExecute(Void result) {
    	
    	
    	
//  super.onPostExecute(result);
//  MAcive.AddText("\n Готово \n");
    }
}  
