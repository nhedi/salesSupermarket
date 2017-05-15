package se.kth.ict.nextgenpos.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import se.kth.ict.nextgenpos.controller.Controller;
import se.kth.ict.nextgenpos.model.NonExistingItemIdException;
import se.kth.ict.nextgenpos.model.SalesObserver;
import se.kth.ict.nextgenpos.model.ProductSpecification;
import util.LogHandler;

/**
 * A placeholder for the view.
 */
public class View implements SalesObserver {
    private Map<Integer, ProductSpecification> noOfItemsRegistered = new HashMap<Integer, ProductSpecification>();    
    private Controller cont;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logger;
	int quantity = 1;

    /**
     * Creates a new <code>View</code>.
     * @param cont           The controller of the application.
     * @throws IOException 
     */
    public View(Controller cont) throws IOException {
	this.cont = cont;
	this.logger = new LogHandler();
    }

    /**
     * Simulates a view. Makes some calls to the controller.
     */
    public void test() {
	cont.makeNewSale();
	enterItem(1);
	System.out.println(">>>>> NOTE!!\n" +
			   "A null pointer exception will follow since there is no handling" + 
			   " of non-existing item ids. When you have implemented exception" +
			   " handling, there should be some informative printout instead of the" +
			   " exception stack trace.");
	enterItem(10);
    }

    /**
     * Prints the information about the requested product.
     * @param itemId an identifier for the item that is entered.
     */
    private void enterItem(int itemId) {
	try{
		System.out.println("");
		System.out.println("Result for item " + itemId + ": " + cont.enterItem(itemId, quantity));
		System.out.println("");
	} catch(NonExistingItemIdException n) {
		handleException(n.getMessage(), n);
		}
    }
    
    /**
     * Handles different types of exceptions.
     * @param uiMsg The user and developer error message that will be shown in the view and logged to the log file.
     * @param exc The exception that should be handled.
     */
    private void handleException(String uiMsg, Exception exc) {
        errorMsgHandler.showErrorMsg(uiMsg);
        logger.logException(exc);
    }

	@Override
	public void newItem(int itemId) throws NonExistingItemIdException {
		addNewItem(itemId);
		printCurrentState(noOfItemsRegistered);
	}
	
	private void addNewItem(int itemId) throws NonExistingItemIdException {
		noOfItemsRegistered.put(itemId, cont.enterItem(itemId, quantity));
	}
	
	private void printCurrentState(Map<Integer, ProductSpecification> noOfItemsRegistered) throws NonExistingItemIdException { 
		System.out.println("### The items registered in the product catalog: ###"); 
		//Iterator it = noOfItemsRegistered.entrySet().iterator();
		//while (it.hasNext())
		for (int itemId : noOfItemsRegistered.keySet()) {
        System.out.print(noOfItemsRegistered.get(itemId));
        System.out.print(" ");
		System.out.println(itemId + ": " + cont.enterItem(itemId, quantity));
        System.out.println("##############################");
		}
	}
}
