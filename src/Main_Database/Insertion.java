package Main_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Insertion {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "1313";
	private static PreparedStatement pmst;
	private static Connection conn;

	public static void main(String[] args) {
		try {
			Scanner src = new Scanner(System.in);
			System.out.println("Enter Database: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			String sql = "Insert into student_details(s_id,s_name,s_email,s_address) values(?,?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Student id: ");
			pmst.setString(1, src.nextLine());
			System.out.println("Enter Student name: ");
			pmst.setString(2, src.nextLine());
			System.out.println("Enter Student Email: ");
			pmst.setString(3, src.nextLine());
			System.out.println("Enter Student Address: ");
			pmst.setString(4, src.nextLine());
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
