package AduinoConnection;
//import com.fazecast.jSerialComm.SerialPort;

import com.fazecast.jSerialComm.*;


public class Connection3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(s);

    }
    @SuppressWarnings("empty-statement")
    public String read(){
        SerialPort [] AvailablePorts = SerialPort.getCommPorts();
       
        //Open the first Available port
        SerialPort MySerialPort = AvailablePorts[0];
        int BaudRate = 9600;
        int DataBits = 8;
        int StopBits = SerialPort.ONE_STOP_BIT;
        int Parity   = SerialPort.NO_PARITY;

        //Sets all serial port parameters at one time
        MySerialPort.setComPortParameters(BaudRate,
                                  DataBits,
                                  StopBits,
                                    Parity);

        //Set Read Time outs
        MySerialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 
                                 1000, 
                                    0);
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
            while (time<=6)//set time here
            {
                System.out.println(time);time++;
                byte[] readBuffer = new byte[100];

                int numRead = MySerialPort.readBytes(readBuffer,
                                           readBuffer.length);

                System.out.print("Read " + numRead + " bytes -");
      
                    //Convert bytes to String
                S = new String(readBuffer, "UTF-8"); 

                System.out.println("Received -> "+ S);
      
                for(int i=0;i<8;i++)
                        c[i]=S.charAt(i);
                //for(int i=0;i<8;i++)
                //   System.out.println("Re -> "+ c[i]);
      
        if(numRead==10)
             break;
      
        Thread.sleep(500);
    }
    
} 
catch (Exception e) 
{

      e.printStackTrace(); 
}
        MySerialPort.closePort(); //Close the port
        String a = new String(c);
        System.out.println(S.length());
        System.out.println(a.length());
       return a; 
        
        
        
    }
}
