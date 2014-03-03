/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.*;

/**
 *
 * @author tinyjingle
 */
public class login4client {

    public String login(String user, String password, ObjectInputStream ois, ObjectOutputStream oos) {
        String respond = "0";
        try {
            oos.writeObject(user);//outputing user name and password to check
            oos.writeObject(password);
            System.out.println("send info.");
            respond = (String) ois.readObject();
            System.out.println("get respond.");
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respond;
    }

    public String findpass(String user, ObjectInputStream ois, ObjectOutputStream oos) {
        String respond = "false";
        try {
            oos.writeObject(user);//outputing user and get respond
            System.out.println("1");
            respond = (String) ois.readObject();
            System.out.println("1");
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respond;
    }

    public String register(String[] info, ObjectInputStream ois, ObjectOutputStream oos) {
        String in = "false";
        try {
            System.out.println("Outputing info.");
            for (int i = 0; i < 7; i++) {
                oos.writeObject(info[i]);//outputing the new info
                System.out.println(info[i]);
            }
            in = (String) ois.readObject();//getting respond
            System.out.println("in: " + in);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}
