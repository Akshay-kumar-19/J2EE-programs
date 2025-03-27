
package b5employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class B5employee {

    public static void main(String[] args) throws ClassNotFoundException {
        try{
            Scanner in=new Scanner(System.in);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/b5employee","akshay","akki");
            Statement s=con.createStatement();
            
            int id,sal;
            String name,dept;
            
            while(true)
            {
                System.out.println("1.insert 2.delete 3.view count of emp in dept  4.update sal  5.exit");
                System.out.println("Enter your choice");
                int ch=in.nextInt();
                switch(ch)
                {
                    case 1:
                        System.out.println("Enter employee details");
                        System.out.println("Enter id");
                        id=in.nextInt();
                        System.out.println("Enter name");
                        name=in.next();
                        System.out.println("Enter dept");
                        dept=in.next();
                        System.out.println("Enter sal");
                        sal=in.nextInt();
                        
                        PreparedStatement stmt=con.prepareStatement("insert into employee values(?,?,?,?)");
                        stmt.setInt(1,id);
                        stmt.setString(2,name);
                        stmt.setString(3,dept);
                        stmt.setInt(4,sal);
                        stmt.executeUpdate();
                        break;  
                        
                    case 2:
                        System.out.println("enter id to delete");
                        id=in.nextInt();
                        PreparedStatement stmt1=con.prepareStatement("delete from employee where id=?");
                        stmt1.setInt(1,id);
                        stmt1.executeUpdate();
                        break;

                    case 3:
                        System.out.println("display");
                        ResultSet rs=s.executeQuery("select dept,count(id),sum(sal) from employee group by dept");
                        while(rs.next())
                        {
                            System.out.print(rs.getString(1)+"\t");
                            System.out.print(rs.getInt(2)+"\t");
                            System.out.print(rs.getInt(3)+"\n");
                            
                        }
                        break;
                        
                    case 4:
                        System.out.println("Enter sal to update");
                        sal=in.nextInt();
                        PreparedStatement stmt2=con.prepareStatement("update employee set sal=sal+?");
                        stmt2.setInt(1,sal);
                        stmt2.executeUpdate();
                        break;
                        
                    case 5:
                        System.exit(0);
                        break;
                    
                    default:
                        System.out.println("invalid input");
                        break;   
                }
            }
        }catch(SQLException e)
        {}
    }  
}
