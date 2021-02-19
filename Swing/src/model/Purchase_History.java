package model;

public class Purchase_History {
	// Variable Declaration
	private int id;
	private int billingId;
	private int productId;
	private String product_Name;
	private float product_cost;
	private int quantity;
	private float total_cost;

	// Non-Parameterized constructor
	public Purchase_History() {
		super();

	}

	// parameterized constructor
	public Purchase_History(int id, int billingId, int productId, String product_Name, float product_cost,
			int quantity, float total_cost) {
		super();
		this.id = id;
		this.billingId = billingId;
		this.productId = productId;
		this.product_Name = product_Name;
		this.product_cost = product_cost;
		this.quantity = quantity;
		this.total_cost = total_cost;
	}

	/**
	 * <p>
	 * Getters and Setters
	 * </p>
	 */
	
	public int getId() {
		return id;
	}

	public int getBillingId() {
		return billingId;
	}

	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public float getProduct_cost() {
		return product_cost;
	}

	public void setProduct_cost(float product_cost) {
		this.product_cost = product_cost;
	}

	public float getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(float total_cost) {
		this.total_cost = total_cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProduct_Name() {
		return product_Name;
	}

	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}
	// Product Description
	@Override
	public String toString() {
		return "Purchase_History [id=" + id + ", billingId=" + billingId + ", productId=" + productId
				+ ", product_Name=" + product_Name + ", product_cost=" + product_cost + ", quantity=" + quantity
				+ ", total_cost=" + total_cost + "]";
	}

	
	

}
