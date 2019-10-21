package HouseApplication;

import java.awt.BorderLayout;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class CreateHouseDialog extends JFrame {
	JLabel photoFilelbl = new JLabel("Photo File : "); 
	JTextField photofile = new JTextField("", 15); 
	JLabel addrs1lbl = new JLabel("Address Line 1 : ");
	JTextField addrs1 = new JTextField("", 15) ;
	JLabel addrs2lbl = new JLabel("Address Line 2 : "); 
	JTextField addrs2 = new JTextField("", 15) ;
	JLabel noBedroomslbl = new JLabel("Number of Bedrooms"); 
	JTextField noBedrooms = new JTextField("", 15); 
	JLabel noBathroomslbl = new JLabel("Number of Bathrooms : "); 
	JTextField noBathrooms = new JTextField("", 15); 
	JLabel pricelbl = new JLabel("Price : "); 
	JTextField price = new JTextField("", 15); 
	JLabel numberlbl = new JLabel("Conact number : "); 
	JTextField number = new JTextField("", 15);
	JButton add = new JButton("Add"); 
	JButton cancel = new JButton("Cancel"); 
	JPanel panel = new JPanel(); 
	JPanel buttons = new JPanel(); 
	static ArrayList<House> houseList = new ArrayList();
	
	public CreateHouseDialog(ArrayList ref ) {
		houseList = ref ;  
		  
		panel.setLayout(new MigLayout());
		panel.add(photoFilelbl , "newline"); 
		panel.add(photofile ); 
		panel.add(addrs1lbl, "newline"); 
		panel.add(addrs1 );
		panel.add(addrs2lbl, "newline"); 
		panel.add(addrs2 ); 
		panel.add(noBedroomslbl, "newline"); 
		panel.add(noBedrooms );
		panel.add(noBathroomslbl, "newline"); 
		panel.add(noBathrooms ); 
		panel.add(pricelbl, "newline" ); 
		panel.add(price ); 
		panel.add(numberlbl, "newline"); 
		panel.add(number); 
		add(panel);
		buttons.setLayout(new FlowLayout());
		panel.add(buttons,"newline, dock south", FlowLayout.CENTER); 
		buttons.add(add); 
		buttons.add(cancel); 
		
		cancel.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent e)
			{
				dispose(); 
			}
		});
		
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				House house = new House( addrs1.getText(), addrs2.getText(), Integer.parseInt(noBedrooms.getText()), Integer.parseInt(noBathrooms.getText())
				,Double.parseDouble(price.getText()), photofile.getText(), number.getText()); 
				 houseList.add(house); 
				 dispose(); 
			}
			
		});
		
		setVisible(true);
		setSize(400, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		new CreateHouseDialog(houseList);
	}
}
