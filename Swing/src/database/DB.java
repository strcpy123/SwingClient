package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Purchase_History;
import model.Product;
import model.User;

/**
 * Class for Database operation
 */
public class DB {
	// Reference declaration
	private PreparedStatement preparedStatement;
	private User user;
	private Product product;
	private Purchase_History purchaseHistoryofOneItem;
	private Purchase_History purchaseHistory;
	private List<Purchase_History> purchaseList;

	/**
	 * Store User in the database
	 * 
	 * @param user
	 */
	public void Store(User user) {
		String query = "INSERT INTO User(name,email,password,mobile,gender,date,month,year,addressarea) VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			preparedStatement.setString(1, user.getNameField());
			preparedStatement.setString(2, user.getEmailField());
			preparedStatement.setString(3, user.getPasswordField());
			preparedStatement.setString(4, user.getMobileField());
			preparedStatement.setString(5, user.getradioButton());
			preparedStatement.setString(6, user.getDate());
			preparedStatement.setString(7, user.getMonth());
			preparedStatement.setString(8, user.getYear());
			preparedStatement.setString(9, user.getAddressArea());
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Get user from database on the basis of two params
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public User getUser(String email, String password) {
		// TODO Auto-generated method stub
		try {
			String query = "Select * from User where email='" + email + "' AND password='" + password + "'";
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user = new User();
				user.setNameField(resultSet.getString(1));
				user.setEmailField(resultSet.getString(2));
				user.setPasswordField(resultSet.getString(3));
				user.setMobileField(resultSet.getString(4));
				user.setradioButton(resultSet.getString(5));
				user.setDate(resultSet.getString(6));
				user.setMonth(resultSet.getString(7));
				user.setYear(resultSet.getString(8));
				user.setAddressArea(resultSet.getString(9));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Get product on the basis of Id
	 * 
	 * @param id
	 * @return
	 */
	public Product getProduct(int id) {
		try {
			String query = "Select * from Product where Id='" + id + "'";
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt(1));
				product.setName(resultSet.getString(2));
				product.setPrice(resultSet.getFloat(3));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(product);
		return product;
	}

	/**
	 * Insert data in the Purchase_History table
	 * 
	 * @param purchaseHistory
	 * @param code
	 */
	public void storeProduct(List<Purchase_History> purchaseHistory, int code) {
		
		String query = "INSERT INTO Purchase_History(Id,Product_ID,Product_Name,Product_Cost,Quantity,Total_Cost) VALUES(?,?,?,?,?,?)";
		for (int i = 0; i < purchaseHistory.size(); i++) {
			try {
				preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
				preparedStatement.setInt(1, code);
				preparedStatement.setInt(2, purchaseHistory.get(i).getProduct_Id());
				preparedStatement.setString(3, purchaseHistory.get(i).getProduct_Name());
				preparedStatement.setFloat(4, purchaseHistory.get(i).getProduct_cost());
				preparedStatement.setInt(5, purchaseHistory.get(i).getQuantity());
				preparedStatement.setFloat(6, purchaseHistory.get(i).getTotal_cost());
				preparedStatement.execute();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get data from Purchase_History table on the basis of id
	 * 
	 * @param id
	 * @return
	 */
	public List<Purchase_History> getPurchaseDetails(int id) {
//		System.out.println(id);
		purchaseList = new ArrayList<Purchase_History>();
		try {
			String query = "Select * from Purchase_History where Id='" + id + "'";
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				purchaseHistory = new Purchase_History();
				purchaseHistory.setId(resultSet.getInt(1));
				purchaseHistory.setProduct_Id(resultSet.getInt(2));
				purchaseHistory.setProduct_Name(resultSet.getString(3));
				purchaseHistory.setProduct_cost(resultSet.getFloat(4));
				purchaseHistory.setQuantity(resultSet.getInt(5));
				purchaseHistory.setTotal_cost(resultSet.getFloat(6));
				purchaseList.add(purchaseHistory);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
//		System.out.println("purchaseList.size()"+purchaseList.size());
		return purchaseList;
	}

	/**
	 * Get one item from Purchase_History table on the basis of productId
	 * 
	 * @param productId
	 * @return
	 */
	public Purchase_History getPurchaseHistoryOfOneItem(int productId, int billingId) {
		purchaseHistoryofOneItem = new Purchase_History();
		try {
			String query = "Select * from Purchase_History where Product_ID='" + productId + "' AND Id='" + billingId
					+ "'";
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				purchaseHistoryofOneItem.setId(resultSet.getInt(1));
				purchaseHistoryofOneItem.setProduct_Id(resultSet.getInt(2));
				purchaseHistoryofOneItem.setProduct_Name(resultSet.getString(3));
				purchaseHistoryofOneItem.setProduct_cost(resultSet.getFloat(4));
				purchaseHistoryofOneItem.setQuantity(resultSet.getInt(5));
				purchaseHistoryofOneItem.setTotal_cost(resultSet.getFloat(6));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DB Quanity:" + purchaseHistoryofOneItem.getQuantity());
		System.out.println("DB TotalCost:" + purchaseHistoryofOneItem.getTotal_cost());
		return purchaseHistoryofOneItem;
	}

	/**
	 * Update Purchase_History table
	 * 
	 * @param quantity
	 * @param totalCost
	 * @param productId
	 * @param billingId
	 * @return
	 */
	public int updatePurchaseDetails(int quantity, float totalCost, int productId, int billingId) {
		int count = 0;
		try {
			String query = "Update Purchase_History set Quantity = '" + quantity + "',Total_Cost ='" + totalCost
					+ "' where Id = '" + billingId + "' AND Product_ID ='" + productId + "'";
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			count = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	/**
	 * Delete selected product from the table Purchase_History
	 * 
	 * @param productId
	 * @param billingId
	 * @return
	 */
	public int deleteProductFromHistory(int productId, int billingId) {
		int deletedRow = 0;
		try {
			String query = "Delete from Purchase_History where Product_ID ='" + productId + "' AND Id = '" + billingId
					+ "'";
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			deletedRow = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return deletedRow;
	}

}
