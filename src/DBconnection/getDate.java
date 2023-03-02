/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBconnection;
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Gimhan
 */
public class getDate {
    /*public static void main(String[]args){
        //System.out.println(yearMonthOnly());
        //System.out.println(dateAndTime());
        //System.out.println(timeOnly());
    }*/
    public String dateOnly(){
        LocalDate date=java.time.LocalDate.now();
        return date.toString();
    }
    public int monthOnly(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM");
        String formattedDate = myDateObj.format(myFormatObj);
        //System.out.println("After formatting: " + formattedDate);
        return Integer.parseInt(formattedDate);
    }
    public int yearOnly(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        //System.out.println("After formatting: " + formattedDate);
        return Integer.parseInt(formattedDate);
    }
    public String dateAndTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = myDateObj.format(myFormatObj);
        //System.out.println("After formatting: " + formattedDate);
        return formattedDate.toString();
    }
    public String timeOnly(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = myDateObj.format(myFormatObj);
        //System.out.println("After formatting: " + formattedDate);
        return formattedDate.toString();
    }
    public String yearMonthOnly(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = myDateObj.format(myFormatObj);
        //System.out.println("After formatting: " + formattedDate);
        return formattedDate.toString();
    }
    public String lastdayofmonth(){//yyyy-MM-dd
        LocalDate currentDate = LocalDate.now();
        currentDate.getMonth();
        LocalDate lastDayOfMonthDate  = currentDate.withDayOfMonth(
                                        currentDate.getMonth().length(currentDate.isLeapYear()));
        //System.out.println("Last date of the month: "+lastDayOfMonthDate);
        return lastDayOfMonthDate.toString();
    }
    public int lastdayofmonth(int y, int m){//yyyy-MM-dd
        String date="01/"+Integer.toString(m)+"/"+Integer.toString(y);
        LocalDate currentDate = LocalDate.now();
        LocalDate givenDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate lastDayOfMonthDateGivenDate  = givenDate.withDayOfMonth(
                                                 givenDate.getMonth().length(givenDate.isLeapYear()));
 
        System.out.println("Last date of the month for date 13/01/2022: "+lastDayOfMonthDateGivenDate);
        //System.out.println("Last date of the month: "+lastDayOfMonthDate);
        String day=lastDayOfMonthDateGivenDate.toString();
        day=day.substring(8);
        return Integer.parseInt(day);
    }
}
