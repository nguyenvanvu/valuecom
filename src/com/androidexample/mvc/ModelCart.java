package com.androidexample.mvc;

import java.util.ArrayList;

public class ModelCart{
	
   private  ArrayList<ModelProducts> cartProducts = new ArrayList<ModelProducts>();
	

   public ModelProducts getProducts(int pPosition) {
		
		return cartProducts.get(pPosition);
	}
	
	public void setProducts(ModelProducts Products) {
		for (ModelProducts cartProduct : cartProducts){
			if (cartProduct.getProductID() ==  Products.getProductID() )
				return;
		}
		cartProducts.add(Products);
	}
	
	public int getCartSize() {
		   
		return cartProducts.size();
		
	}
 
	public boolean checkProductInCart(ModelProducts aProduct) {
		   
		return cartProducts.contains(aProduct);
		
	}
	public void clearCartsArrayList(){
		cartProducts.clear();
	}

}
