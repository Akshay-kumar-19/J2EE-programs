
package b2bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class B2bank {

    public static void main(String[] args) throws ClassNotFoundException {
        try{
            Scanner in=new Scanner(System.in);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/b2bank","akshay","akki");
            Statement s=con.createStatement();
            int accno=0,balance=0,amt;
            String particulars,transtype,acctype;
            int ch;
            while(true)
            {
                System.out.println("1.deposit  2.withdraw  3.report 4.peridocial report  5.exit");
                System.out.println("Enter your choice");
                ch=in.nextInt();
                if(ch==1||ch==2)
                {
                    System.out.println("Enter account number");
                    accno=in.nextInt();
                    ResultSet rs=s.executeQuery("select * from customer where accno="+accno);
                    if(!rs.next())
                    {
                        System.out.println("account number doesnot exist");
                        continue;
                    }
                    balance=rs.getInt("balance");
                    rs.close();
                }
                switch(ch)
                {
                    case 1:
                        System.out.println("enter particulars");
                        particulars=in.next();
                        System.out.println("Enter amount to deposit");
                        amt=in.nextInt();
                        transtype="credited";
                        String date=LocalDate.now().toString();
                       s.executeUpdate("insert into bank values("+accno+",'"+date+"','"+transtype+"','"+particulars+"',"+amt+")");                     
                       s.executeUpdate("update customer set balance=balance+"+amt+" where accno="+accno);
                        System.out.println("deposited successfully");
                        break;
                    case 2:
                        System.out.println("Enter particulars");
                         particulars=in.next();
                        System.out.println("Enter withdraw amount");
                        amt=in.nextInt();
                        transtype="debited";
                        if(amt>balance)
                        {
                            System.out.println("insufficient balance");
                            break;
                        }
                        else
                        {
                        date=LocalDate.now().toString();
                        s.executeUpdate("insert into bank values("+accno+",'"+date+"','"+transtype+"','"+particulars+"',"+amt+")");
                        s.executeUpdate("update customer set balance=balance-"+amt+" where accno="+accno);
                        System.out.println(amt+"withdrawed successfully");
                        break;
                        }
                        
                    case 3:
                        System.out.println("daily report");
                        System.out.println("Enter date(yyyy-mm-dd)");
                        date=in.next();
                       ResultSet rs = s.executeQuery("SELECT * FROM bank WHERE date='" + date + "'");
                        while(rs.next())
                        {
                            System.out.print("\n"+rs.getString("transtype"));
                             System.out.print("Rs. "+rs.getInt("amt"));
                        }
                        break;
                        
                        case 4:
                        System.out.println("daily report");
                        System.out.println("Enter  startingdate(yyyy-mm-dd)");
                        date=in.next();
                        System.out.println("Enter  ending(yyyy-mm-dd)");
                        String date1=in.next();
                       ResultSet ss = s.executeQuery("SELECT * FROM bank WHERE date between '" + date + "' and '"+date1+"'");
                      
                        while(ss.next())
                        {
                            System.out.print("\n"+ss.getString("date"));
                            System.out.print("\t"+ss.getString("transtype"));
                             System.out.print("Rs. "+ss.getInt("amt"));
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
