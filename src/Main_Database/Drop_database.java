package Main_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Drop_database {
	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/";
	private static final String username="root";
	private static final String password="1313";
	private static Connection conn;
	private static PreparedStatement pmst;
	
	public static void main(String[] args) {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
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
			src.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
