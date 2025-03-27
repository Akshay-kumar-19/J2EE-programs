
package b4student.marks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class B4studentMarks {

    public static void main(String[] args) throws ClassNotFoundException {
        try{
            Scanner in=new Scanner(System.in);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/b4studentmarks","akshay","akki");
            Statement s=con.createStatement();
            
            int id,sub1,sub2,sub3,total,percentage;
            String grade;
            
            while(true)
            {
                System.out.println("1.insert marks 2.display 3.exit");
                System.out.println("Enter your choice");
                int ch=in.nextInt();
                switch(ch)
                {
                    case 1:
                        System.out.println("Enter id");
                        id=in.nextInt();
                        ResultSet rs=s.executeQuery("select * from student where id="+id);
                        if(rs.next())
                        {
                            System.out.println("Enter marks in 3 subjects");
                            sub1=in.nextInt();
                            sub2=in.nextInt();
                            sub3=in.nextInt();
                            total=sub1+sub2+sub3;
                            percentage=total/3;
                            if(sub1<35||sub2<35||sub3<35)
                                grade="fail";
                            else if(percentage>80)
                                grade="distinction";
                            else if(percentage>70)
                                grade="first class";
                            else if(percentage>50)
                                grade="second class";
                            else
                                grade="pass class";
                            
                            String sql="insert into exam values("+id+","+sub1+","+sub2+","+sub3+","+total+","+percentage+",'"+grade+"')";
                            int r=s.executeUpdate(sql);
                            if(r>0)
                                System.out.println("inserted");
                            else
                                System.out.println("failed to insert");
                            
                        }
                        break;
                        
                    case 2:
                        System.out.println("display");
                        ResultSet ss=s.executeQuery("select * from student s,exam m where s.id=m.id");
                        while(ss.next())
                        {
                            System.out.print(ss.getInt(1)+"\t");
                            System.out.print(ss.getString(2)+"\t");
                            System.out.print(ss.getString(3)+"\t");
                            System.out.print(ss.getString(4)+"\t");
                            System.out.print(ss.getString(5)+"\t");
                            System.out.print(ss.getInt(6)+"\t");
                            System.out.print(ss.getInt(7)+"\t");
                            System.out.print(ss.getInt(8)+"\t");
                            System.out.print(ss.getInt(9)+"\t");
                            System.out.print(ss.getInt(10)+"\t");
                            System.out.print(ss.getInt(11)+"\t");
                            System.out.print(ss.getString(12)+"\n");
                        }
                        break;
                        
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
            
        }catch(SQLException e)
        {}
    }
}
