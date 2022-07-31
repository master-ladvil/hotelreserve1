import java.sql.*;
import java.util.*;

public class Db {
    static Connection con = null;
    static  Scanner sc = new Scanner(System.in).useDelimiter("\n");
    static String name;
    static String mobile;
    static String cid;
    static String tid;
    static String roomno;
    static String sdate;
    static String edate;
    public Db(){
        System.out.println("Enter your name : ");
        Db.name = sc.nextLine();
        System.out.println("name added to db "+Db.name);
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hotelreserve1_0","postgres","pwd");
            if(con!=null){
                System.out.println("connection estabished");
            }else{
                System.out.println("Connection failed");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static int login(){
        Statement stmt;
        ResultSet rs = null;
        System.out.println("Enter your mobile : ");
        String mobile = sc.nextLine();
        String getmobilnum = new String();
        try{
            String query = String.format("select mobile from client where fullname = '%s';",name);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if(rs.next()){
            getmobilnum = rs.getString("mobile");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("entered->"+mobile+"got->"+getmobilnum);

        if(getmobilnum.equals(mobile)){
            Db.mobile = getmobilnum;
            return 1;
        }
        else{
            System.out.println("bad credentials");
            return 0;
        }
        
        
    }

    public static void register(){
        Statement stmt;
        System.out.println("Enter your mobile : ");
        Db.mobile = sc.nextLine();
        try{
            String query = String.format("insert into client(fullname,mobile) values('%s','%s');",Db.name,Db.mobile);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("added succesfully...");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void getAvailableRooms(){
        Statement stmt;
        ResultSet rs = null;
        try{
            String query = String.format("SELECT room.id,capacity,rtype,price FROM room JOIN capacity ON room.cid=capacity.id JOIN rtype ON room.tid = rtype.id WHERE isavailablle = true;");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            System.out.print("roomNo ");
            System.out.print("capacity ");
            System.out.print("roomtype ");
            System.out.println("price ");
            while(rs.next()){
                System.out.print(rs.getString("id")+"           ");
                System.out.print(rs.getString("capacity")+"   ");
                System.out.print(rs.getString("rtype")+"   ");
                System.out.println(rs.getString("price")+"   ");
            }
            System.out.println("Enter the room no : ");
            Db.roomno = sc.nextLine();
            

        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void bookreservation(){
        Statement stmt;
        ResultSet rs = null;
        System.out.println("Enter the checkin date (yyyy-mm-dd): ");
        Db.sdate = sc.nextLine();
        System.out.println("Enter the checkout date (yyyy-mm-dd): ");
        Db.edate =sc.nextLine();
        try{
            String query = String.format("select id from client where mobile = '%s';",Db.mobile);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            int clid = rs.getInt("id");
            String rquery = String.format("insert into reservation(rid,clid,sdate,edate) values('%s','%s','%s','%s');",Db.roomno,clid,Db.sdate,Db.edate);
            stmt.executeUpdate(rquery);
            System.out.println("reserved room "+roomno+" under the name "+name);
            String upquery = String.format("update room set isavailablle = false where id = '%s';",roomno);
            stmt.executeUpdate(upquery);

        }catch(Exception e){
            System.out.println(e);
        }
    
    }
}
