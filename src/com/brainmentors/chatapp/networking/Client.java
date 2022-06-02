package com.brainmentors.chatapp.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.brainmentors.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket = new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
		out = socket.getOutputStream();
		in = socket.getInputStream();
		this.textArea = textArea;
		
		readMessage();
		
		/*
		System.out.println("Client arrived...");
		
		System.out.println("Enter your message : ");
		Scanner scanner = new Scanner(System.in);
		String message = scanner.nextLine();
		OutputStream out =  socket.getOutputStream();
		
		scanner.close();
		out.write(message.getBytes());
		out.close();
		
		socket.close();	
		*/
	}
	
	public void sendMessage(String message) throws IOException {
		System.out.println("Message is : "+message);
		out.write(message.getBytes());
	}
	
	public void readMessage() {
		worker = new ClientWorker(in, textArea);
		worker.start();
	}
	
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		Client client = new Client(new JTextArea());
//		while(true) {
//			Scanner scanner = new Scanner(System.in);
//			String msg = scanner.next();
//			client.sendMessage(msg);
//		}
//		
//	}
}
