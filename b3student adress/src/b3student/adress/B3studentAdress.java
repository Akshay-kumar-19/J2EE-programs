
package b3student.adress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class B3studentAdress {

    public static void main(String[] args) throws ClassNotFoundException {
        try
        {
            Scanner in=new Scanner(System.in);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/b3studentadress","akshay","akki");
            Statement s=con.createStatement();
            int reg;
            String name,dob,adress,clas,course;

            while(true)
            {
                System.out.println("1.add student 2.delete student 3.update student adress 4.view particualr studnet 5.exit");
                System.out.println("Enter your choice");
                int ch=in.nextInt();
                
                switch(ch)
                {
                    case 1:
                        System.out.println("enter student detials");
                        System.out.println("Enter regno");
                        reg=in.nextInt();
                        System.out.println("Enter name");
                        name=in.next();
                        System.out.println("Enter dob");
                        dob=in.next();
                        System.out.println("Enter adress");
                        adress=in.next();
                        System.out.println("Enter class");
                        clas=in.next();
                        System.out.println("Enter course");
                        course=in.next();
                        
                        String sql="insert into student values("+reg+",'"+name+"','"+dob+"','"+adress+"','"+clas+"','"+course+"')";
                        int r=s.executeUpdate(sql);
                        if(r>0)
                            System.out.println("inserted");
                        else
                            System.out.println("failed to insert");
                        break;
                    case 2:
                        System.out.println("Enter reg no to delete");
                        reg=in.nextInt();
                        r=s.executeUpdate("delete from student where reg="+reg);
                        if(r>0)
                            System.out.println("deleted");
                        else
                            System.out.println("failed to delete");
                        break;
                    case 3:
                        System.out.println("Enter student regno to update adress");
                        reg=in.nextInt();
                        System.out.println("Enter new adress");
                        adress=in.next();
                        r=s.executeUpdate("update student set adress='"+adress+"' where reg="+reg);
                        if(r>0)
                            System.out.println("adress updated");
                        else 
                            System.out.println("failed to update");
                        break;
                    case 4:
                        System.out.println("Enter reg no to view the student");
                        reg=in.nextInt();
                        ResultSet rs=s.executeQuery("select * from student where reg="+reg);
                        System.out.println("student details");
                        while(rs.next())
                        {
                            System.out.println(rs.getInt(1));
                            System.out.println(rs.getString(2));
                            System.out.println(rs.getString(3));
                            System.out.println(rs.getString(4));
                            System.out.println(rs.getString(5));
                            System.out.println(rs.getString(6));
                        }
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
