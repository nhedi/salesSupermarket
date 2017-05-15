package se.kth.ict.nextgenpos.model;

/**
 * An instance of this class holds information about a single sale of a single product.
 */
class SalesLineItem {
    private ProductSpecification spec;
    private int quantity;

    /**
     * Creates a new instance.
     *
     * @param spec        Information about the product whos sale is represented by the 
     *                    new instance.
     * @param quantity    The number of items of this product that are sold to the customer.
     */
    SalesLineItem(ProductSpecification spec, int quantity) {
	this.spec = spec;
	this.quantity = quantity;
    }

    /**
     * Returns the sum of the cost of all products represented by this instance.
     *
     * @return  The sum of the cost of all products represented by this instance.
     */
    int getCost() {
	return spec.getPrice() * quantity;
    }
    
}
