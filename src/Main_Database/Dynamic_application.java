package Main_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Dynamic_application {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "1313";
	private static Connection conn;
	private static PreparedStatement pmst;
	public static void main(String[] args) {
		int Choice;
		do {
			Scanner src = new Scanner(System.in);
			System.out.println("Choose your Choice");
			displayMenu();
			Choice =Integer.parseInt(src.next());
			switch (Choice) {
			case 1:
				CreateDataBase();
				break;
			case 2:
				DropDataBase();
				break;
			case 3:
				InsertData();
				break;
			case 4:
				DeleteData();
				break;
			case 5:
				UpdateData();
				break;
			case 6:
				GetById();
				break;
			case 7:
				GetAll();
				break;
			case 8:
				Login();
				break;
			case 9:
				exit();
				break;
				default:
					System.out.println("Invalid Option!");
					break;
			}
		} while (Choice>0);
	}
	private static void CreateDataBase() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			String url="jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username,password);
			System.out.println("Enter Databse: ");
			String sql = "create database "+ src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Database is Created!");
			}else {
				System.out.println("Database is not created");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void DropDataBase() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			String url="jbdc:mysql://locaalhost:3306/";
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Enter database name: ");
			String sql="Drop Database "+src.nextLine();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i == 0 ) {
				System.out.println("Database dropped!");
			}else {
				System.out.println("Database not dropped");
			}
			conn.close();
			pmst.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void InsertData() {
		try {
			Scanner src = new Scanner(System.in);
			System.out.println("Enter Database: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter TableName");
			String sql = "insert into "+src.next() + "(order_id,order_name,order_pincode,order_address) values(?,?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Order Id: ");
			pmst.setLong(1, src.nextLong());
			System.out.println("Enter Order Name: ");
			pmst.setString(2, src.next());
			System.out.println("Enter Order Pincode: ");
			pmst.setInt(3, src.nextInt());
			System.out.println("Enter Order Address: ");
			pmst.setString(4, src.next());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Data Inserted");
			} else {
				System.out.println("Data not inserted");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void DeleteData() {
		try {
			Scanner src = new Scanner(System.in);
			System.out.println("Enter Database: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter TableName");
			String sql = "delete from "+src.next() + " where order_id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Order Id: ");
			pmst.setLong(1, src.nextLong());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Data Deleted");
			} else {
				System.out.println("Data not deleted");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void UpdateData() {
		try {
			Scanner src = new Scanner(System.in);
			System.out.println("Enter Database: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter TableName");
			String sql = "update "+ src.next() + " set order_name=?,order_pincode=?,order_address=? where order_id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Order Name: ");
			pmst.setString(1, src.next());
			System.out.println("Enter Order Pincode: ");
			pmst.setInt(2, src.nextInt());
			System.out.println("Enter Order Address: ");
			pmst.setString(3, src.next());
			System.out.println("Enter Order Id: ");
			pmst.setLong(4, src.nextLong());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Data updated");
			} else {
				System.out.println("Data not updated");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	private static void GetById() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter Database: ");
			String url="jdbc:mysql://localhost:3306/"+src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter Table name: ");
			String sql = "select * from "+src.next() +" where order_id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Order Id: ");
			pmst.setLong(1,src.nextLong());
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("Order Id: "+rs.getLong("order_id"));
				System.out.println("Order name: "+rs.getString("order_name"));
				System.out.println("Order pin code :"+rs.getInt("order_pincode"));
				System.out.println("Order address: "+rs.getString("order_address"));
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void GetAll() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter Database: ");
			String url= "jdbc:mysql://localhost:3306/"+src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter Table name: ");
			String sql = "select * from "+ src.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("Order Id: "+rs.getLong("order_id"));
				System.out.println("Order name: "+rs.getString("order_name"));
				System.out.println("Order pin code :"+rs.getInt("order_pincode"));
				System.out.println("Order address: "+rs.getString("order_address"));
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void Login() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter Database: ");
			String url = "jdbc:mysql://localhost:3306/"+ src.next();
			conn = DriverManager.getConnection(url, username,password);
			System.out.println("Enter Table name:");
			String sql = "select * from "+src.next()+ " where user_id=? and  user_password=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter user id:");
			pmst.setLong(1,src.nextLong());
			System.out.println("Enter user password");
			pmst.setString(2,src.next());
			ResultSet rs = pmst.executeQuery();
			if(rs.next()) {
				System.out.println("Login sucessful");
			}else {
				System.out.println("Login failed!");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void exit() {
		System.out.println("You are exited!");
		System.exit(0);
		
	}
	private static void displayMenu() {
		System.out.println("\t1.Create Database");
		System.out.println("\t2.Drop Database");
		System.out.println("\t3.Data Insertion");
		System.out.println("\t4.Data Deletion by Id");
		System.out.println("\t5.Update Data");
		System.out.println("\t6.Get by Id");
		System.out.println("\t7.Get all");
		System.out.println("\t8.Login");
		System.out.println("\t9.Exit");
		
	}
}
