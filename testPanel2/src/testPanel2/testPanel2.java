package testPanel2;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;



public class testPanel2{
	JFrame myFrame = new JFrame("My Test Panel 2");
	
	String outPing = "";
	
	public testPanel2() {
		
		InetAddress address = null;
		String strTarget1 = "8.8.8.8";
		String strTarget2 = "www.nytimes.com";
		
		boolean reachable = false;
		
		int i = 3;
		int j = 3;
		int curRow, curCol;
		int header = 0;
		JPanel[][] panelHolder = new JPanel[i][j];    
		JLabel[][] lblHolder = new JLabel[i][j];
		
		//set up the frame
		myFrame.setLayout(new GridLayout(i,j,5,1));
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(new Dimension(500 , 120));
		myFrame.setVisible(true);
		
		// initialize the panels in the grid
		for(curRow = 0; curRow < i; curRow++) {
		   for(curCol = 0; curCol < j; curCol++) {
			   lblHolder[curRow][curCol] = new JLabel();
			   panelHolder[curRow][curCol] = new JPanel();
			   panelHolder[curRow][curCol].setBackground(Color.GREEN);
			   panelHolder[curRow][curCol].add(lblHolder[curRow][curCol]);
			   myFrame.add(panelHolder[curRow][curCol]);
		   }
		 }
		 
		// set the header
		lblHolder[header][0].setText("Target");
		lblHolder[header][1].setText("IP");	
		lblHolder[header][2].setText("Status");
		panelHolder[header][0].setBackground(Color.MAGENTA);
		panelHolder[header][1].setBackground(Color.MAGENTA);
		panelHolder[header][2].setBackground(Color.MAGENTA);
			
		while(true){
			//
			// set status for each
			//
			// Target 1
			try {
				address = InetAddress.getByName(strTarget1);
			} catch (UnknownHostException e) {
				//e.printStackTrace();
			}
			
			outPing = address.toString();
			
			try {
				reachable = address.isReachable(5000);
			} catch (IOException e) {
				//e.printStackTrace();
				reachable = false;
			}
			
			if(reachable) {
				panelHolder[1][0].setBackground(Color.GREEN);
				panelHolder[1][1].setBackground(Color.GREEN);
				panelHolder[1][2].setBackground(Color.GREEN);
			}
			else {
				panelHolder[1][0].setBackground(Color.RED);
				panelHolder[1][1].setBackground(Color.RED);
				panelHolder[1][2].setBackground(Color.RED);
			}
			
			lblHolder[1][0].setText(strTarget1);
			lblHolder[1][1].setText(strTarget1);
			lblHolder[1][2].setText(Boolean.toString(reachable));
				
			// Target 2
			try {
				address = InetAddress.getByName(strTarget2);
			} catch (UnknownHostException e) {
				//e.printStackTrace();
			}
					
			outPing = address.toString();
						
			try {
				reachable = address.isReachable(5000);
			} catch (IOException e) {
				//e.printStackTrace();
				reachable = false;
			}
			
			if(reachable) {
				panelHolder[2][0].setBackground(Color.GREEN);
				panelHolder[2][1].setBackground(Color.GREEN);
				panelHolder[2][2].setBackground(Color.GREEN);
			}
			else {
				panelHolder[2][0].setBackground(Color.RED);
				panelHolder[2][1].setBackground(Color.RED);
				panelHolder[2][2].setBackground(Color.RED);
			}
						
			lblHolder[2][0].setText(strTarget2);
			lblHolder[2][1].setText(strTarget2);
			lblHolder[2][2].setText(Boolean.toString(reachable));
		 
			//System.out.println("about to sleep again");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}  // end while(true) loop
	}

	public static void main(String[] args) {
		
		System.out.println("Starting testPanel2");
		new testPanel2();
	}
}
