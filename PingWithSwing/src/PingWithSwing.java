import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


import javax.swing.*;

public class PingWithSwing{
	static final int row = 3;
	static final int col = 2;
	
	JFrame myFrame = new JFrame();
	//JLabel myLabel = new JLabel();
	JPanel myPanel = new JPanel();
	JPanel[][] panelHolder = new JPanel[row][col];  
	
	PingWithSwing() {
		
		String outText, outPing;
		boolean reachable = false;
		Color myColor = null;
		
	
		// set title, duh
		myFrame.setTitle("Ping with Swing");
		
		// use GridLayout
		myFrame.setLayout(new GridLayout(row, col));
		
		// give the frame an initial size
		myFrame.setSize(420, 190);
				
		// terminate the program when the user closes the application
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				
		//========================
	
		for(int curRow = 0; curRow < row; curRow++) {
			for(int curCol = 0; curCol < col; curCol++) {
					
					if (curRow == 0) {
						myColor = Color.magenta;
					
						if (curCol == 0) {
							outText = "Target";
						}
						else {
							outText = "Status";
						}
					}
					else {
						InetAddress address = null;
						try {
							address = InetAddress.getByName("www.nytimes.com");
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						outPing = address.toString();
						
						try {
							reachable = address.isReachable(5000);
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (curCol == 0) {
							//String strOutPing = address.toString();
							int start = 0;
							int end = outPing.indexOf("/");
							char buf[] = new char[end - start];
							outPing.getChars(start , end, buf, 0);
							outText = String.valueOf(buf);
							//outText = outPing;
							myColor = Color.LIGHT_GRAY;
							
						}
						else {
							outText = Boolean.toString(reachable);
							if (reachable) {
								myColor = Color.GREEN;
							}
							else {
								myColor = Color.RED;
							}
						}
					}
					
					Label lbl = new Label(outText);
					lbl.setText(outText);
					lbl.setBackground(myColor);
					lbl.setAlignment(Label.CENTER);
					Font myFont = new Font("sanSerif", Font.BOLD, 15);
					lbl.setFont(myFont);
					myFrame.add(lbl);
					
					JLabel myLabel = new JLabel();
					myLabel.setText(outText);
					myLabel.setBackground(Color.BLACK);
					myLabel.setForeground(myColor);
					myLabel.setHorizontalTextPosition(JLabel.RIGHT);
					//myFrame.add(myLabel);
					
					//myPanel.add(lbl);
					//myFrame.add(myPanel);
										
					System.out.println(outText);
					myFrame.setVisible(true);
					
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		
		
		//myFrame.setVisible(true);
		
		
		//========================
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PingWithSwing();
			}
		});
		
		
	}

}


