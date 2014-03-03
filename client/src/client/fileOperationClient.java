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
public class fileOperationClient {

    public int send(String path, String user, ObjectInputStream ois, ObjectOutputStream oos, OutputStream os) {
        int respond = 0;
        try {

            File f = new File(path);
            oos.writeLong(f.length());//outping the size of the file and the name and user name
            oos.writeObject(f.getName());
            oos.writeObject(user);

            //send file
            System.out.println("Outputing info finished");
            byte[] mybytearray = new byte[(int) f.length()];//get the byte array has the same size as the file
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
            os.write(mybytearray, 0, mybytearray.length);//use os to output file
            os.flush();
            System.out.println("Outputing file finished");
            respond = Integer.parseInt(ois.readObject().toString());
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respond;
    }

    public void receive(String name, String user, String path, ObjectInputStream ois, ObjectOutputStream oos, InputStream is) {
        try {
            oos.writeObject(name);//outputing the user name and file name
            oos.writeObject(user);
            String size = (String) ois.readObject();//getting the size of the file to prepare byte array
            System.out.println("size is:" + size);
            int bytesRead;
            int current = 0;
            byte[] mybytearray = new byte[Integer.parseInt(size)];//byte writer
            FileOutputStream fos = new FileOutputStream(path + "/" + name);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bytesRead = is.read(mybytearray, 0, mybytearray.length);//read from 0 to the length
            current = bytesRead;
            do {//buffer zone.
                bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
                if (bytesRead >= 0) {
                    current += bytesRead;
                }
                System.out.println(current);
            } while (current != mybytearray.length || current == -1);
            //System.out.println("pending to flush");
            bos.write(mybytearray, 0, current);
            bos.flush();
            
            bos.close();
            fos.close();
            bos.close();
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
