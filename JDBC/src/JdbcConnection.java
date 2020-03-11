import java.sql.*;
import java.util.Scanner;
public class JdbcConnection {

	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		Statement stmt=con.createStatement();
		stmt.executeUpdate("create table Student(id number primary key,name varchar2(20),age number)");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number of rows :");
		int n=sc.nextInt();
		for(int i=0;i<n;i++)
		{
		System.out.println("Enter Student ID ");
		int id=sc.nextInt();
		System.out.println("Enter Student Name ");
		String name=sc.next();
		System.out.println("Enter Student  Age ");
		int age=sc.nextInt();

		PreparedStatement pstmt=con.prepareStatement("insert into Student values(?,?,?)");
		 pstmt.setInt(1,id);
		pstmt.setString(2,name);
		pstmt.setInt(3,age);
		pstmt.executeUpdate();
		}
		ResultSet rs=stmt.executeQuery("select * from Student where age<21");
		System.out.println("Current Row "+rs.getRow());
		System.out.println("ID \t Name \t Age");
		System.out.println("------------------");
		while(rs.next())
		{
		System.out.print("   "+rs.getInt(1));
		System.out.print("   "+rs.getString(2));
		System.out.print("   "+rs.getInt(3));
		System.out.println( );

		}
		stmt.close();
		con.close();
		}
		

	}

