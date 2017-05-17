package se.kth.ict.nextgenpos.view;

import java.io.IOException;
import se.kth.ict.nextgenpos.controller.Controller;
import se.kth.ict.nextgenpos.model.NoneExistingItemIdException;
import se.kth.ict.nextgenpos.util.LogHandler;

/**
 * A placeholder for the view.
 */
public class View {
    private Controller cont;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logger;
	int quantity = 1;

    /**
     * Creates a new <code>View</code>.
     * @param cont The controller of the application.
     * @throws IOException 
     */
    public View(Controller cont) throws IOException {
    	this.cont = cont;
    	this.logger = new LogHandler();
    	cont.addSalesObserver(new SalesItemDisplay());
    }

    /**
     * Simulates a view. Makes some calls to the controller.
     */
    public void test() {
    	cont.makeNewSale();    	
    	enterItem(1);
    	enterItem(2);
    	enterItem(10);
    }

    /**
     * Prints the information about the requested product.
     * @param itemId an identifier for the item that is entered.
     */
    private void enterItem(int itemId) {
    	try {
    		System.out.println("");
    		System.out.println("\n ### The information about the requested product ### \n Result for item " + itemId + ": " + cont.enterItem(itemId, quantity));
    		System.out.println("");
    	}
    	catch(NoneExistingItemIdException n) {
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
}
