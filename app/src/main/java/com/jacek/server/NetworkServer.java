package com.jacek.server;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jacek on 21.05.2017.
 */

public class NetworkServer implements Runnable {

    TextView tv;
    public NetworkServer(TextView textView){
        tv = textView;
    }

    @Override
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(20001);
            Log.i("asd", "UTWORZONY SOCKET");
            while (true){
                Log.d("WSZEDLEM", "W WHILE");
                tv.setText(String.valueOf(serverSocket.getInetAddress().getHostAddress()));
                Log.d("IP SERWERA", serverSocket.getInetAddress().getHostAddress());
                Socket socket = serverSocket.accept();
                Log.d("COKOLWIEK", "PRZYSZLO");
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                String response = br.readLine();
                tv.setText(String.valueOf(response));
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
