package Main_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Update {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "1313";
	private static  Connection conn;
	private static  PreparedStatement pmst;
public static void main(String[] args) {
	try {
		Scanner src = new Scanner(System.in);
		System.out.println("Enter Database: ");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("Enter TableName");
		String sql = "update "+src.next() + "set order_name=?,order_pincode=?,order_address=? where order_id=?";
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
			System.out.println("Data Inserted");
		} else {
			System.out.println("Data not inserted");
		}
		conn.close();
		pmst.close();
		src.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
