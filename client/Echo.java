/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.io.*;
import java.net.*;
public class Echo {
    private static EchoObjectStub ss;
    
    public static void main(String[] args) {
        String cadena="";
        if (args.length<2) {
            System.out.println("Usage: Echo <host> <port#>");
            System.exit(1);
        }
        ss = new EchoObjectStub();//EJERCICIO: crear una instancia del stub
        ss.setHostAndPort(args[0],Integer.parseInt(args[1]));
        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
        PrintWriter stdOut = new PrintWriter(System.out);
        String input,output;
        try {
        //EJERCICIO: el bucle infinito:
        //EJERCICIO: Leer de teclado
        //EJERCICIO: Invocar el stub
        //EJERCICIO: Imprimir por pantalla
            while(true){
                BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
                cadena=in.readLine();
                System.out.println(ss.echo(cadena));
            }
        } //catch (UnknownHostException e) {
            //System.err.println("Don't know about host: "+ args[0]);
        //} 
        catch (IOException e) {
            System.err.println("I/O failed for connection to: "+args[0]);
        }
    }
}
