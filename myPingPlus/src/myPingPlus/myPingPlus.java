package myPingPlus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

import javax.swing.*;

class pingTarget {
	
	String targetName;
	String ipAddress;
	
	// ---------------------------------
	//      METHODS BEGIN HERE
	// ---------------------------------
	// =================================
	// setTargetName(String inTargetName) 
	// =================================
	void setTargetName(String inTargetName) {
		targetName = inTargetName;
		try {
			setIpAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// =================================
	// setIpAddress()
	// =================================
	void setIpAddress() throws UnknownHostException {
		
		InetAddress address = InetAddress.getByName(targetName);
		String strOutPing = address.toString();
		int start = strOutPing.indexOf("/");
		int end = strOutPing.length();
		char buf[] = new char[end - start];
		strOutPing.getChars(start+1 , end, buf, 0);
		ipAddress = String.valueOf(buf);
		
	}
	
	// =================================
	// getTargetName()
	// =================================
	String getTargetName() {
		return targetName;
	}
	
	// =================================
	// getIpAddress()
	// =================================
	String getIpAddress() {
		return ipAddress;
	}
	
	// ================================= 
	// getTargetStatus
	// =================================
	boolean getTargetStatus() {
		try {
			InetAddress address = InetAddress.getByName(targetName);
			return address.isReachable(5000);
		}
		catch (Exception e) {
			return false;
		}	
	}
}

public class myPingPlus {
static JTextField txtPING, txtTwo;
	
	public static void main(String[] args) throws IOException {
		
		pingTarget myPingTarget = new pingTarget();
		String strTarget, strIP;
		String outPing = "";
		int i;
		boolean reachable;
				
		myPingTarget.setTargetName("8.8.8.8"); //("www.nytimes.com");
		strTarget = myPingTarget.getTargetName();
		strIP = myPingTarget.getIpAddress();
		
		System.out.println("Target ==>  " + strTarget);
		System.out.println("IP ==>  " + strIP);
		//System.exit(0);
				
		JFrame myFrame = new JFrame("myPING v3"); 
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setPreferredSize(new Dimension(300,100));
		
		JLabel outputLabel = new JLabel();
		outputLabel.setOpaque(true);
		
		JLabel firstLabel = new JLabel("PING STATUS");
		JLabel secondLabel = new JLabel("      ");
		firstLabel.setOpaque(true);
		secondLabel.setOpaque(true);
		
		myFrame.add(firstLabel, BorderLayout.NORTH);
		myFrame.add(secondLabel, BorderLayout.SOUTH);
		myFrame.add(outputLabel, BorderLayout.CENTER);
		
		i = 0;
		while (true) {
			++i;
			outPing = "";
					
			if(i == 1000) i = 0;
			
			reachable = myPingTarget.getTargetStatus();
			
			if(reachable) {
				firstLabel.setBackground(Color.GREEN);
				secondLabel.setBackground(Color.GREEN);
			}
			else {
				firstLabel.setBackground(Color.RED);
				secondLabel.setBackground(Color.RED);
			}
			
			outPing = "   " + strTarget + "  :  " + strIP + "  :  " + reachable;
			outputLabel.setText(outPing);
				
			//System.out.println(i + "==>  " + outPing);
			
			myFrame.pack();
			myFrame.setVisible(true);
			myFrame.setAutoRequestFocus(false);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
