package com.xevate.zxing.client.android;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xevate.freebacode.android.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Browser;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;

public class ShowIO extends Activity  {
	public String severaddress = "http://assettrack.xevate.com/";//"http://192.168.0.52/assetdev/";
	public String rightID = "";
	private OnClickListener gobacktoScan = new OnClickListener() {
        public void onClick(View v) {
        	 finish();               
        }
    };
    
	private OnClickListener goHome = new OnClickListener() {
	        public void onClick(View v) {
	        	String link = severaddress+"index.php";
	        	Context context = v.getContext();
          	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
          	browserIntent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
          	startActivity(browserIntent);               
	        
	        }
	    };
	    
		private OnClickListener goAdd = new OnClickListener() {
	        public void onClick(View v) {
	        	String link = severaddress+"additem.php";
	        	Context context = v.getContext();
	          	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
	          	browserIntent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
	          	startActivity(browserIntent);              
	        
	        }
	    };
	    
		private OnClickListener goEdit = new OnClickListener() {
	        public void onClick(View v) {
	        	String link = severaddress+"edititem.php?id="+rightID;
	        	Context context = v.getContext();
	          	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
	          	browserIntent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
	          	startActivity(browserIntent);              
	        
	        }
	    };
   
//	@SuppressWarnings("deprecation")
//	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showprofile);
		
		Button buttonScan = (Button)findViewById(R.id.Button00);        
		buttonScan.setOnClickListener(gobacktoScan); // Register the onClick listener with the implementation above
		 
		 Button buttonHome = (Button)findViewById(R.id.Button01);        
		 buttonHome.setOnClickListener(goHome); // Register the onClick listener with the implementation above
	       
	     Button buttonSearch = (Button)findViewById(R.id.Button02);        
	     buttonSearch.setOnClickListener(goHome);
	     
	     Button buttonAdd = (Button)findViewById(R.id.Button03);        
	     buttonAdd.setOnClickListener(goAdd); // Register the onClick listener with the implementation above
	     
	     Button buttonEdit = (Button)findViewById(R.id.Button04);        
	     buttonEdit.setOnClickListener(goEdit); // Register the onClick listener with the implementation above
	       

//		TextView profilename = (TextView)findViewById(R.id.profilename);
//		TextView profilecompany = (TextView)findViewById(R.id.profilecompany);
//		TextView profileemail = (TextView)findViewById(R.id.profileemail);
//		TextView profileposition = (TextView)findViewById(R.id.profileposition);
//		TextView profilephone = (TextView)findViewById(R.id.profilephone);
//		TextView profileaddress = (TextView)findViewById(R.id.profileaddress);
		
		
		Intent intent = getIntent();
		final String BCContents = intent.getExtras().getString("BCContents");
		//BCContents="171101";
		String Title = "Label = " + BCContents;
		rightID = BCContents;

		setTitle("Barcode ID = "+BCContents);
			
			String id_str = BCContents;
		//	System.out.println("Recevied id =" + id_str );
			
			AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
			alertbox.setTitle("Bar Code: " + BCContents );
			alertbox.setMessage("Do you want to Google this item?");
			
		       alertbox.setPositiveButton("Yes",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int which) {
		                    	String GooglelinkSearch = "https://www.google.ca/#q=" + BCContents;
		                    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GooglelinkSearch));
		                    	startActivity(browserIntent);
		                    }
		                });
		       
		       alertbox.setNegativeButton("No",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int which) {
		                    	dialog.cancel();

		                    }
		                });
			
		       alertbox.show();
		       
			//String getprofilepath = severaddress+"detect.php?id="+id_str.replace(" ", "%20");
			//	LoadContentTask.execute(getprofilepath);
	}
	//////////////////////////
 

}
