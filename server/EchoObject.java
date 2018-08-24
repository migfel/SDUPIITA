/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
import rmi.EchoInt;
public class EchoObject implements EchoInt {
    String myURL="localhost";
    public EchoObject(){
        try {
            myURL=InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            myURL="localhost";
        }
    }
    public String echo(String input) throws java.rmi.RemoteException {
        Date h = new Date();
        String fecha =
        DateFormat.getTimeInstance(3,Locale.FRANCE).format(h);
        String ret = myURL + ":" + fecha + "> " + input;
        System.out.println("Procesando: '" + input + "'");
        try {
            Thread.sleep(3000); ret = ret + " (retrasada 3 segundos)";
        } catch (InterruptedException e) {}
        System.out.println("Procesamiento de '"+ input +"'terminado.");
        return ret;
    }
}
