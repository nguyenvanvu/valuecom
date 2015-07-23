package com.androidexample.mvc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class ThirdScreen extends Activity {
	private ProgressDialog barProgressDialog;
	private Handler updateBarHandler;
	final Context context = this;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdscreen);
		updateBarHandler = new Handler();
    
		TextView showCartContent    = (TextView) findViewById(R.id.showCart);
		
		//Get Global Controller Class object (see application tag in AndroidManifest.xml)
		final Controller aController = (Controller) getApplicationContext();

		if(aController.getOrientationScreen() == Configuration.ORIENTATION_PORTRAIT) {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else if(aController.getOrientationScreen() == Configuration.ORIENTATION_LANDSCAPE){
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}

		int cartSize = aController.getCart().getCartSize();
		
		String showString = "";
		
/******** Show Cart Products on screen - Start ********/	
		
			for(int i=0;i<cartSize;i++)
			{
				//Get product details
				String pName 	= aController.getCart().getProducts(i).getProductName();
				int pPrice   	= aController.getCart().getProducts(i).getProductPrice();
				String pDisc   	= aController.getCart().getProducts(i).getProductDesc();
				
				showString += "\n\nProduct Name : "+pName+"\n"+
	                               "Price : "+pPrice+"\n"+
	                               "Discription : "+pDisc+""+
	                               "\n -----------------------------------";
			}
		
		
		showCartContent.setText(showString);
		
/******** Show Cart Products on screen - End ********/	
		
	}

	public void launchRingDialog(View view) {
		final ProgressDialog ringProgressDialog = ProgressDialog.show(context, "Please wait ...",	"Downloading Image ...", true);
		ringProgressDialog.setCancelable(true);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// Here you should write your time consuming task...
					// Let the progress ring for 10 seconds...
					Thread.sleep(10000);
				} catch (Exception e) {

				}
				ringProgressDialog.dismiss();
			}
		}).start();
	}

	public void launchBarDialog(View view) {
		barProgressDialog = new ProgressDialog(context);

		barProgressDialog.setTitle("Downloading Image ...");
		barProgressDialog.setMessage("Download in progress ...");
		barProgressDialog.setProgressStyle(barProgressDialog.STYLE_HORIZONTAL);
		barProgressDialog.setProgress(0);
		barProgressDialog.setMax(20);
		barProgressDialog.show();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					// Here you should write your time consuming task...
					while (barProgressDialog.getProgress() <= barProgressDialog.getMax()) {

						Thread.sleep(500);

						updateBarHandler.post(new Runnable() {

							public void run() {

								barProgressDialog.incrementProgressBy(2);

							}

						});

						if (barProgressDialog.getProgress() == barProgressDialog.getMax()) {

							barProgressDialog.dismiss();

						}
					}
				} catch (Exception e) {
				}
			}
		}).start();
	}
	
}
