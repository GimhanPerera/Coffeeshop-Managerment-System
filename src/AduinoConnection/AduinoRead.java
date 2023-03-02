/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AduinoConnection;
import DBconnection.LoyaltyCard;
import com.fazecast.jSerialComm.*;
/**
 *
 * @author Gimhan
 */
public class AduinoRead implements Runnable{
    private String correctCardid="";
    private int value=0;
    public AduinoRead(String cid) throws Exception{
        
    }
    public void run(){
        SerialPort [] AvailablePorts = SerialPort.getCommPorts();
        //Open the first Available port
        SerialPort MySerialPort = AvailablePorts[0];
        int BaudRate = 9600;
        int DataBits = 8;
        int StopBits = SerialPort.ONE_STOP_BIT;
        int Parity   = SerialPort.NO_PARITY;
        //Sets all serial port parameters at one time
        MySerialPort.setComPortParameters(BaudRate,DataBits,StopBits,Parity);
        //Set Read Time outs
        MySerialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,1000,0);
        MySerialPort.openPort(); //open the port
                                 //Arduino May get reset 
        //Thread.sleep(2000);
        if (MySerialPort.isOpen())//Check whether port open/not
              System.out.println("is Open ");
        else
           System.out.println(" Port not open ");
        String S = "00";char[] c = new char[8];
        try 
         {
             int time=0;
            while (true)//set time here
            {
                System.out.println(time);time++;
                byte[] readBuffer = new byte[100];
                int numRead = MySerialPort.readBytes(readBuffer,readBuffer.length);
                System.out.print("Read " + numRead + " bytes -");
                    //Convert bytes to String
                S = new String(readBuffer, "UTF-8"); 
                System.out.println("Received -> "+ S);     
                for(int i=0;i<8;i++)
                        c[i]=S.charAt(i);
                System.out.println("Length: "+ S.length());  
                //for(int i=0;i<8;i++)
                //   System.out.println("Re -> "+ c[i]);  
                if(numRead==10){//Didn't read
                    if(correctCardid.equals(S)){
                        System.out.println("CORRECT CARD");
                        value=1;
                        //lbl_ok.setVisible(true);
                        //lbl_notOk.setVisible(false);
                        //lbl_waiting.setVisible(false);
                        //lbl_scaninfo.setVisible(false);
                    }else{
                    System.out.println("INCORRECT CARD");
                    value=2;
                    //lbl_ok.setVisible(false);
                    //lbl_notOk.setVisible(true);
                    //lbl_waiting.setVisible(false);
                    //lbl_scaninfo.setVisible(false);
                    //btn_scanmanage.setText("Scan again");
                    }
                    break;
                }
                if(time==10){//Didn't read
                    System.out.println("TIMEOUT: Didn't read"); 
                    value=3;
                    //btn_scanmanage.setText("Scan again");
                    //lbl_ok.setVisible(false);
                    //lbl_notOk.setVisible(false);
                    //lbl_waiting.setVisible(true);
                    //lbl_scaninfo.setVisible(false);
                    break;
                }
                    
      
                Thread.sleep(1000);
            }
    
        } 
        catch (Exception e) {
            e.printStackTrace(); 
        }
        MySerialPort.closePort(); //Close the port
        String a = new String(c);
        System.out.println(S.length());
        System.out.println(a.length());
    }
    
    public int getValue(){
        return value;
    }
}
