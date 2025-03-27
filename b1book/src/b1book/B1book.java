
package b1book;
import static java.lang.Boolean.TRUE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class B1book {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try{
            Scanner in=new Scanner(System.in);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/b1book","akshay","akki");
            Statement s=con.createStatement();
            
            int id,price,copy;
            String name,author,pub;
            
            while(true)
            {
                System.out.println("Enter your choice");
                System.out.println("1.insert  2.delete  3.update  4.exit");
                int ch=in.nextInt();
                switch(ch)
                {
                    case 1:
                        System.out.println("Enter your book details");
                        System.out.println("Enter id");
                        id=in.nextInt();
                        System.out.println("Enter name");
                        name=in.next();
                        System.out.println("Enter author");
                        author=in.next();
                        System.out.println("Enter publication");
                        pub=in.next();
                        System.out.println("Enter price");
                        price=in.nextInt();
                        copy=in.nextInt();
                        
                        String sql="insert into book values("+id+",'"+name+"','"+author+"','"+pub+"',"+price+","+copy+")";
                        int r=s.executeUpdate(sql);
                        if(r>0)
                            System.out.println("inserted");
                        else
                            System.out.println("failed to insert");
                        break;    
                    case 2:
                        System.out.println("Enter id to delete");
                        id=in.nextInt();
                         r=s.executeUpdate("delete from book where id="+id);
                        if(r>0)
                            System.out.println("deleted");
                        else
                            System.out.println("failed to delete");
                        break;
                        
                    case 3:
                        System.out.println("Enter your choice");
                        System.out.println("1.update price 2.update copy");
                        int op=in.nextInt();
                        switch(op)
                        {
                            case 1:
                                System.out.println("Enter book id");
                                id=in.nextInt();
                                System.out.println("Enter new price");
                                price=in.nextInt();
                                r=s.executeUpdate("update book set price="+price+" where id="+id);
                                if(r>0)
                                    System.out.println("updated");
                                else
                                    System.out.println("failed to update");
                                break;
                            case 2:
                                System.out.println("Enter id");
                                id=in.nextInt();
                                System.out.println("enter new copy");
                                copy=in.nextInt();
                                r=s.executeUpdate("update book set copy="+copy+"where id="+id);
                                if(r>0)
                                    System.out.println("update");
                                else
                                    System.out.println("failed to update");
                                break;
                        }
                        break;
                    case 4:
                        System.exit(0);
                       break; 
                    default:
                        System.out.println("invalid input ");
                        break;    
                }
            }

        }catch(ClassNotFoundException | SQLException e)
        {}
    }

}
