package se.kth.ict.nextgenpos.startup;

import java.io.IOException;

import se.kth.ict.nextgenpos.controller.Controller;
import se.kth.ict.nextgenpos.view.View;

/**
 * Starts the application.
 * @throws NonExistingItemIdException if the search item Id does not exist in the product catalog.
 */
public class Main {
    public static void main(String[] args) throws IOException {
	Controller cont = new Controller();
	View view = new View(cont);
	view.test();
    }
}
