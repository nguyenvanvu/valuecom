package com.androidexample.mvc;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;

public class FirstScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.firstscreen);
		
		final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);
		final Button secondBtn = (Button) findViewById(R.id.second);
		final Button thirdBtn = (Button) findViewById(R.id.third);
		
		//Get Global Controller Class object (see application tag in AndroidManifest.xml)
		final Controller aController = (Controller) getApplicationContext();
		aController.clearProductsArrayList();
		aController.getCart().clearCartsArrayList();
		/******************  Create Dummy Products Data  ***********/
		
		ModelProducts productObject = null; 
		for(int i=1;i<=4;i++)
		{
			int price = 10+i;
			// Create product model class object
			productObject = new ModelProducts("Product "+i,"Description "+i,price, "" + i);
			
			//store product object to arraylist in controller
			aController.setProducts(productObject);
			
		}
		
		/******************  Products Data Creation End   ***********/
		
		
		/******* Create view elements dynamically and show on activity ******/
		
		//Product arraylist size
		int ProductsSize = aController.getProductsArraylistSize();

		// create the layout params that will be used to define how your
	    // button will be displayed
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
	/******** Dynamically create view elements - Start **********/
		
		for(int j=0;j< ProductsSize;j++)
		{	
			// Get probuct data from product data arraylist
			String pName = aController.getProducts(j).getProductName();
			int pPrice   = aController.getProducts(j).getProductPrice();
			
			// Create LinearLayout to view elemnts
			LinearLayout ll = new LinearLayout(this);
			ll.setOrientation(LinearLayout.HORIZONTAL);
			
			TextView product = new TextView(this);
			product.setText(" "+pName+"    ");
			
			//Add textView to LinearLayout
			ll.addView(product);
			
			TextView price = new TextView(this);
			price.setText("  $"+pPrice+"     ");
			
			//Add textView to LinearLayout
			ll.addView(price);
			
			final Button btn = new Button(this);
				btn.setId(j+1);
				btn.setText("Add To Cart");
				
				// set the layoutParams on the button
				btn.setLayoutParams(params);
				
			    final int index = j;
			    
			    //Create click listener for dynamically created button
			    btn.setOnClickListener(new OnClickListener() {
			        public void onClick(View v) {
			        	
			        	//Clicked button index
			            Log.i("TAG", "index :" + index);
			            
			            // Get product instance for index
			            ModelProducts tempProductObject = aController.getProducts(index);
			            
			            //Check Product already exist in Cart or Not
			            if(!aController.getCart().checkProductInCart(tempProductObject))
			            {
			            	btn.setText("Added");
			            	
			            	// Product not Exist in cart so add product to
			            	// Cart product arraylist
			            	aController.getCart().setProducts(tempProductObject);
			            	
			            	Toast.makeText(getApplicationContext(), "Now Cart size: "+aController.getCart().getCartSize(), 
			            			Toast.LENGTH_LONG).show();
			            }
			            else
			            {
			            	// Cart product arraylist contains Product 
			            	Toast.makeText(getApplicationContext(), "Product "+(index+1)+" already added in cart.", 
			            			Toast.LENGTH_LONG).show();
			            }	
			        }
			    });
			    
			    //Add button to LinearLayout
			    ll.addView(btn);
			    
			    //Add LinearLayout to XML layout 
			    lm.addView(ll);  
		}
		
		/******** Dynamically create view elements - End **********/
		
		secondBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Intent i = new Intent(getBaseContext(), SecondScreen.class);
				int orientation = getResources().getConfiguration().orientation;
				i.putExtra("orientation",orientation );
				startActivity(i);
			}
		});

		/******** Show Cart Products on screen - End ********/

		thirdBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Get Cart Size
				final int cartSize = aController.getCart().getCartSize();
				if(cartSize >0)
				{
					Intent i = new Intent(getBaseContext(), ThirdScreen.class);
					aController.setOrientationScreen(getResources().getConfiguration().orientation);
					startActivity(i);
				}
				else
					Toast.makeText(getApplicationContext(),
							"Shopping cart is empty.",
							Toast.LENGTH_LONG).show();
			}
		});

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig){
		super.onConfigurationChanged(newConfig);

		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
			Toast.makeText(this,"ORIENTATION_LANDSCAPE",Toast.LENGTH_LONG).show();
		}else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
			Toast.makeText(this,"ORIENTATION_PORTRAIT",Toast.LENGTH_LONG).show();
		}
	}



}
