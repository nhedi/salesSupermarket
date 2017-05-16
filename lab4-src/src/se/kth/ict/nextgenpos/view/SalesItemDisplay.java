package se.kth.ict.nextgenpos.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.kth.ict.nextgenpos.model.NonExistingItemIdException;
import se.kth.ict.nextgenpos.model.ProductSpecification;
import se.kth.ict.nextgenpos.model.SalesLineItem;
import se.kth.ict.nextgenpos.model.SalesObserver;

/**
 * 
 *
 */
public class SalesItemDisplay implements SalesObserver {
	
    private ArrayList<SalesLineItem> noOfItemsRegistered = new ArrayList<>();    

	@Override
	public void newItem(SalesLineItem lineItems) throws NonExistingItemIdException {
		addNewItem(lineItems);
		printCurrentState(noOfItemsRegistered);
	}
	
	/**
	 * 
	 * @param lineItems
	 * @throws NonExistingItemIdException
	 */
	private void addNewItem(SalesLineItem lineItems) throws NonExistingItemIdException {
		noOfItemsRegistered.add(lineItems);
	}
	
	/**
	 * 
	 * @param lineItems
	 * @throws NonExistingItemIdException
	 */
	public void printCurrentState(List<SalesLineItem> noOfItemsRegistered) throws NonExistingItemIdException { 
		System.out.println("### The items registered in the product catalog: ###"); 
		for (int i = 0; i < noOfItemsRegistered.size(); i++) {
			System.out.println("Product ID: " + noOfItemsRegistered.get(i).getSpec().getProductId() +
								"\t Product name: " + noOfItemsRegistered.get(i).getSpec().getName());
		}
	}
}
