package HouseApplication;
import java.text.NumberFormat;
 
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class HouseSummaryDialog extends JFrame {
	JPanel panel = new JPanel(); 
	JTextArea display = new JTextArea(); 
	ArrayList<House> houseList = new ArrayList();
	
	public HouseSummaryDialog(ArrayList ref)
	{
		houseList = ref ; 
		 
		panel.add(display); 
		display.setSize(800,350);
		display.append("House-id        Image-Location 	              Street  	      Bedrooms  	bathrooms  	       price-change  		contactNo");
		for (House c : houseList)
		{
			display.append("\n");
			display.append(c.toString()); .addWindowListener(l);
			 
		}
		
		add(panel);
		setSize(1100,150); 
		setVisible(true); 
		 
}
}
