/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;
public interface EchoInt extends java.rmi.Remote {
    public String echo(String input)throws
    java.rmi.RemoteException;
}
