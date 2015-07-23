package com.androidexample.mvc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondScreen extends Activity {
	final Context context = this;
	public MyDialog.myOnClickListener myListener;

	private static int counter = 1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(this.getClass().getName(), "onCreate " + counter);
		counter++;
		if(getIntent().getIntExtra("orientation", 0) == Configuration.ORIENTATION_PORTRAIT) {
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else if(getIntent().getIntExtra("orientation", 0) == Configuration.ORIENTATION_LANDSCAPE){
			setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		setContentView(R.layout.secondscreen);

    
		TextView showCartContent    = (TextView) findViewById(R.id.showCart);
		final Button thirdBtn 		= (Button) findViewById(R.id.third);
		final Button alertBtn 		= (Button) findViewById(R.id.alert);
		
		//Get Global Controller Class object (see application tag in AndroidManifest.xml)
		final Controller aController = (Controller) getApplicationContext();
		
		// Get Cart Size
		final int cartSize = aController.getCart().getCartSize();
		
		String showString = "";
		
/******** Show Cart Products on screen - Start ********/
		
		if(cartSize >0)
		{	
			
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
		}
		else
			showString = "\n\nShopping cart is empty.\n\n";
		
		showCartContent.setText(showString);
		
/******** Show Cart Products on screen - End ********/
		
		thirdBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(cartSize >0)
				{
					Intent i = new Intent(getBaseContext(), ThirdScreen.class);
					startActivity(i);
				}
				else
					Toast.makeText(getApplicationContext(), 
							"Shopping cart is empty.", 
	            			Toast.LENGTH_LONG).show();
			}
		});
/********** Dialog test ************************/
		alertBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/*
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				// set title
				alertDialogBuilder.setTitle("Your Title");

				// set dialog message
				alertDialogBuilder
						.setMessage("Click yes to exit!")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								SecondScreen.this.finish();
							}
						})
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
				*/
				//create new onclicklistener interface //
				myListener = new MyDialog.myOnClickListener() {
					@Override
					public void onButtonClick() {
						System.out.println("I am clicking the button in the dialog");
						Toast.makeText(getApplicationContext(),
								"I am clicking the button in the dialog",
								Toast.LENGTH_LONG).show();
					}

					@Override
					public void onButtonCancel() {

					}
				};

				MyDialog mydialog = new MyDialog(context, myListener);
				mydialog.show();
			}


		});
		
	} 
	
	@Override
    protected void onDestroy() {
		
        super.onDestroy();
        
    }
}
