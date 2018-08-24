
package client;

import rmi.EchoInt;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;

class EchoObjectStub implements EchoInt {
    
    private Socket echoSocket = null;
    private PrintWriter os = null;
    private BufferedReader is = null;
    
    private String host = "localhost";
    private int port = 9;
    private String output = "Errorrrrrrrrrr fatal";
    private boolean connected = false;
    
    private int timeout = 50;
    private boolean firstTime = true;
    private Timeout to = null;
    
    public void setHostAndPort(String host, int port) //constructor
    {
        this.host = host; this.port = port;
    }
    
    public String echo(String input)throws
    java.rmi.RemoteException {
        if(!connected)
            connect();
        else
            to.cancel();
        if (echoSocket != null && os != null && is != null)
        {
            try 
            {
                os.println(input);
                os.flush();
                output= is.readLine();
            } 
            catch (IOException e)
            {
                System.err.println("I/O failed in reading/writing socket");
                throw new java.rmi.RemoteException("I/O failed in reading/writing socket");
            }
        }
        
        programDisconnection();
        return output;
    }
    
    private synchronized void connect() throws
    java.rmi.RemoteException 
    {
        try 
        {
            echoSocket = new Socket(host, port);
            os =  new PrintWriter(echoSocket.getOutputStream(), true);
            is = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            connected = true;
            System.out.println("Ya existe conexion");
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EchoObjectStub2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private synchronized void disconnect(){

            try {
                echoSocket.close();
                os.close();
                is.close();
                connected=false;
                firstTime=true;
                System.out.println("La conexion ha sido terminada");
            } catch (IOException ex) {
                Logger.getLogger(EchoObjectStub2.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    private synchronized void programDisconnection()
    {
        if(firstTime)
        {
            firstTime = false;
            to = new Timeout(5000); //timeout para 5 seg
            to.start();
        }
        
    }

    
    class Timeout 
    {
        private Timer timer;
        private TimeoutTask task;
        private final int ms;
        
        public Timeout (int ms) 
        {
            this.ms = ms;
        }
        
        public void start()
        {
             timer = new Timer();
             task = new TimeoutTask();
             timer.schedule(task, ms);
        }

        public void cancel() 
        {
            timer.cancel();
            timer.purge();
            timer = new Timer();
            task = new TimeoutTask();
            timer.schedule(task, ms);
        }
        
        class TimeoutTask extends TimerTask 
        {
            @Override
            
            public void run() 
            {
                disconnect();
            }
        }
    }
}
