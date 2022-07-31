import java.sql.*;

public class HotelReservation{
    static{
        System.loadLibrary("hotel");
    }

    public native void consoleApp();
    public static void main(String args[]){
        System.out.println("Hello... calling native app");
    
        HotelReservation hr = new HotelReservation();
        hr.consoleApp();

    }
    
}