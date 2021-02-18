package model;

public class Product{
	    /**
	 * 
	 */
		// Variable Declaration
         private int id;
         private String name;
         private float price;
         // Parameterized Constructor
         /**
          * @param id
          * @param name
          * @param price
          */
		public Product(int id, String name, float price) {
			super();
			this.id = id;
			this.name = name;
			this.price = price;
		}
		//Non-Parameterized Constructor
		public Product() {
			super();
		}
		/** 
		 * <p> Getters and setters for variables </p>
		 */
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		// Product Description
		@Override
		public String toString() {
			return "Product [id=" + id + ", Name=" + name + ", price=" + price + "]";
		}
		//Product hashCode
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}
		
		/**
		 *equals method to check parameterized object is the instance of this class 
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Product other = (Product) obj;
			if (id != other.id)
				return false;
			return true;
		}
		
}
