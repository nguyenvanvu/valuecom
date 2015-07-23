package com.androidexample.mvc;

public class ModelProducts {

	private String productID;
	private String productName;
	private String productDesc;
	private int productPrice;
	
    public ModelProducts(String productName,String productDesc,int productPrice, String productID)
    {
    	this.productName  = productName;
    	this.productDesc  = productDesc;
    	this.productPrice = productPrice;
		this.productID = productID;
    }
	
	public String getProductName() {
		
		return productName;
	}
   
    public String getProductDesc() {
		
		return productDesc;
	}
	
    public int getProductPrice() {
		
		return productPrice;
	}

	public String getProductID(){
		return productID;
	}
		
}
