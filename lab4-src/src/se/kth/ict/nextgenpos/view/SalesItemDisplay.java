package se.kth.ict.nextgenpos.view;

import java.util.ArrayList;
import java.util.List;
import se.kth.ict.nextgenpos.model.NonExistingItemIdException;
import se.kth.ict.nextgenpos.model.SalesLineItem;
import se.kth.ict.nextgenpos.model.SalesObserver;

/**
 * This class represents a display showing the items registered.
 */
public class SalesItemDisplay implements SalesObserver {
	
    private ArrayList<SalesLineItem> noOfItemsRegistered = new ArrayList<>();    

    /**
     * Requests to add a new item in a list and asks for printout.
     * @param lineItems The specified item.
     * @throws NonExistingItemIdException if the search item Id does not exist in the product catalog.
     */
    @Override
	public void newItem(SalesLineItem lineItems) throws NonExistingItemIdException {
		addNewItem(lineItems);
		printCurrentState(noOfItemsRegistered);
	}
	
	/**
	 * Adds a new item in a SalesLineItem list.
	 * @param lineItems The specified item.
	 * @throws NonExistingItemIdException if the search item Id does not exist in the product catalog.
	 */
	private void addNewItem(SalesLineItem lineItems) throws NonExistingItemIdException {
		boolean listUpdated = false;
			for (int i = 0; i < noOfItemsRegistered.size(); i++) {
				if (lineItems.getSpec().getProductId() == noOfItemsRegistered.get(i).getSpec().getProductId()) {
					noOfItemsRegistered.set(i, new SalesLineItem(lineItems.getSpec(), noOfItemsRegistered.get(i).getQuantity() + 1));
					listUpdated = true;
				}
			}
		if (listUpdated == false) {
			noOfItemsRegistered.add(lineItems);
		}
	}
	
	/**
	 * Prints the current product catalog list.
	 * @param lineItems The items in the list.
	 * @throws NonExistingItemIdException if the search item Id does not exist in the product catalog.
	 */
	public void printCurrentState(List<SalesLineItem> noOfItemsRegistered) throws NonExistingItemIdException { 
		System.out.println("### The items registered so far: ###"); 
		for (int i = 0; i < noOfItemsRegistered.size(); i++) {
			System.out.println("Quantity: " + noOfItemsRegistered.get(i).getQuantity() + "\t Product ID: " + noOfItemsRegistered.get(i).getSpec().getProductId() +
								"\t Product name: " + noOfItemsRegistered.get(i).getSpec().getName());
		}
	}
}
