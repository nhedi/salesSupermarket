package se.kth.ict.nextgenpos.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a single sale to one customer.
 */
public class Sale {
    private List<SalesLineItem> lineItems;
    private int currentTotal;
    private int payedAmount;
    private int iterator;
	private List<SalesObserver> salesObservers = new ArrayList<>();

    /**
     * Instantiates a new <code>Sale</code>.
     */
    public Sale() {
    	lineItems = new ArrayList<SalesLineItem>();
    }

    /**
     * Adds new items to the current <code>Sale</code>. 
     *
     * @param spec            The specification of the items that is added.
     * @param quantity        The number of items.
     * @throws NoneExistingItemIdException 
     */
    public void addItem(ProductSpecification spec, int quantity) throws NoneExistingItemIdException {
    	SalesLineItem lineItem = new SalesLineItem(spec, quantity);
    	lineItems.add(lineItem);
    	addToTotal(lineItem);
    	notifyObservers();
    }

    private void addToTotal(SalesLineItem lineItem) {
    	currentTotal = currentTotal + lineItem.getCost();
    }

    /**
     * Returns the total cost of all products registered so for.
     *
     * @return The total cost of all products registered so for.
     */
    public int getCurrentTotal() {
    	return currentTotal;
    }
    
    /**
     * Adds all SalesObserver objects to a combined list.
     * @param obs A list with SalesObservers.
     */
    public void addSalesObservers(List<SalesObserver> obs) {
    	salesObservers.addAll(obs);
    }
    
    /**
     * Notifies all SalesObservers if a new item has been added to the product catalog.
     * @throws NoneExistingItemIdException 
     */
    private void notifyObservers() throws NoneExistingItemIdException {
    	int index = (lineItems.size()-1);
    	for (SalesObserver obs : salesObservers) {
    		obs.newItem(lineItems.get(index));
    	} 	
    }

    /**
     * Calculates change and returns all information needed for the receipt.
     *
     * @return All information needed for the receipt.
     */
    public Receipt createReceipt(int payedAmount) {
    	this.payedAmount = payedAmount;
    	return new Receipt(this);
    }

    void resetLineItemIterator() {
    	iterator = 0;
    }

    SalesLineItem nextLineItem() {
    	return lineItems.get(iterator);
    }

    boolean hasMoreLineItems() {
    	return iterator < lineItems.size();
    }

    int getPayedAmount() {
    	return payedAmount;
    }
}
