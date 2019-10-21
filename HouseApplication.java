package HouseApplication;
import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import net.miginfocom.swing.MigLayout;
import java.text.NumberFormat;
import java.util.*;

public class HouseApplication extends JFrame {
	
	ArrayList<House> houseList = new ArrayList<House>();

	String[][] records = { {"113 The Maltings", "Dublin 8", "2", "1", "155500.00", "House1.jpg", "(087) 9011135"},
			   {"78 Newington Lodge", "Dublin 14", "3", "2", "310000.00", "House2.jpg", "(087) 9010580"},
			   {"62 Bohernabreena Road", "Dublin 24", "3", "1", "220000.00", "House3.jpg", "(087) 6023159"},
			   {"18 Castledevitt Park", "Dublin 15", "3", "3", "325000.00", "House4.jpg", "(087) 9010580"},
			   {"40 Dunsawny Road", "Swords", "3", "19", "245000.00", "House5.jpg", "(087) 9011135"}};
	int currentItem;
	public static String passWord = "3175" ; 
	JPanel top = new JPanel() ;
	JPanel bottom = new JPanel(new GridLayout(1,5)); 
	JPanel upperBtm = new JPanel(new FlowLayout()); 
	MigLayout layout = new MigLayout(); 
	JTextField HouseID = new JTextField("1", 15) ;
	JLabel houseIDlbl = new JLabel("House ID :"); 
	JTextField addressLine1 = new JTextField("113 The maltings", 15) ;
	JLabel addrsLine1lbl = new JLabel("Address Line 1 : ") ; 
	JTextField addressLine2 = new JTextField("Dublin 8 " , 15) ;
	JLabel addrsLine2lbl = new JLabel("Address Line 2 : ") ; 
	JTextField NoBedrooms = new JTextField("2", 15) ;
	JLabel noBedroomslbl = new JLabel("Number of bedrooms : "); 
	JTextField NoBathrooms = new JTextField("1", 15) ;
	JLabel noBathroomslbl = new JLabel("Number of bathrooms : "); 
	JTextField price  = new JTextField("155,500.00", 15) ;
	JLabel pricelbl = new JLabel("price : "); 
	JFormattedTextField Pchange  = new JFormattedTextField(15) ;
	JLabel pChangelbl = new JLabel("Price change : "); 
	JTextField contactNo  = new JTextField("(087) 9011135", 15) ;
	JLabel contactLbl = new JLabel("Contact : ") ; 
	JButton first = new JButton(); 
	JButton previous = new JButton(); 
	JButton edit = new JButton("Edit"); 
	JButton next = new JButton(); 
	JButton last = new JButton(); 
	JLabel pic = new JLabel ();
	JMenuBar menu = new JMenuBar();
	JMenu Modify = new JMenu("Modify"); 
	JMenu Report = new JMenu("Report"); 
	JMenu close = new JMenu("Close"); 
	JMenuItem create = new JMenuItem("Create"); 
	JMenuItem delete = new JMenuItem("Delete"); 
	JMenuItem search = new JMenuItem("Search"); 
	JMenuItem summary = new JMenuItem("Summary"); 
	JOptionPane scan = new JOptionPane();  
	
	public HouseApplication() {
		super("Estate Agent Application");
	//Transform the array of data into Houses on the ArrayList 
		for (int i = 0; i < records.length; i++) {
			houseList.add(new House(records[i][0], records[i][1], Integer.parseInt(records[i][2]), 
					Integer.parseInt(records[i][3]), Double.parseDouble(records[i][4]), records[i][5], records[i][6]));
		}
			
		initComponents();
	}

	public void initComponents() {
		/* Set up your menus and menu items here */
		setJMenuBar(menu);  
		add(top);
		add(bottom); 
		menu.setVisible(false);
		setLayout(new MigLayout()); 
		top.setLayout(layout );
		menu.add(Modify);
		Modify.add(create); 
		Modify.add(delete); 
		menu.add(Report); 
		Report.add(search);
		Report.add(summary); 
		menu.add(close);  
		pic.setIcon(new ImageIcon("House1.jpg"));
		top.add(pic, " growx, span2, dock center");  
		top.add(houseIDlbl, "newline, gap unrelated" ); 
		top.add(HouseID); 
		HouseID.setEditable(false);
		top.add(addrsLine1lbl, "newline, gap unrelated"); 
		top.add(addressLine1 ,"wrap"); 
		addressLine1.setEditable(false);
		top.add(addrsLine2lbl , "newline, gap unrelated" ); 
		top.add(addressLine2  );
		addressLine2.setEditable(false);
		top.add(noBedroomslbl , "newline, gap unrelated"); 
		top.add(NoBedrooms); 
		NoBedrooms.setEditable(false);
		top.add(noBathroomslbl, "newline, gap unrelated"); 
		top.add(NoBathrooms); 
		NoBathrooms.setEditable(false);
		top.add(pricelbl, "newline, gap unrelated"); 
		top.add(price); 
		price.setEditable(false);
		top.add(pChangelbl, "newline, gap unrelated");
		top.add(Pchange);
		Pchange.setValue(new Double(0.0)); 
		Pchange.setColumns(15);
		Pchange.setEditable(false);
		top.add(contactLbl, "newline,  gap unrelated"); 
		top.add(contactNo);
		contactNo.setEditable(false);
		 
		first.add(new JLabel(new ImageIcon("first.png"))); 
		previous.add(new JLabel(new ImageIcon("prev.png"))); 
		next.add(new JLabel(new ImageIcon("next.png"))); 
		last.add(new JLabel(new ImageIcon("last.png"))); 
		bottom.add(first); 
		bottom.add(previous); 
		bottom.add(edit, "grow"); 
		bottom.add(next); 
		bottom.add(last); 
		
		first.addActionListener(new ActionListener()
				{
			
				public void actionPerformed(ActionEvent e)
				{
					pic.setIcon(new ImageIcon(houseList.get(0).getImageLocation()));
					HouseID.setText(Integer.toString(houseList.get(0).getId()));
					addressLine1.setText(houseList.get(0).getStreet());
					addressLine2.setText(houseList.get(0).getCity());	
					NoBedrooms.setText(Integer.toString(houseList.get(0).getBedrooms()));
					NoBathrooms.setText(Integer.toString(houseList.get(0).getBathrooms()));
					price.setText(Double.toString(houseList.get(0).getPrice()));
					Pchange.setText(Double.toString(houseList.get(0).getChange()));
					contactNo.setText((houseList.get(0).getContactNo()));
				}
			
				});
		
		last.addActionListener(new ActionListener()
				{
			
			public void actionPerformed(ActionEvent e)
			{
				pic.setIcon(new ImageIcon(houseList.get(houseList.size()-1).getImageLocation()));
				HouseID.setText(Integer.toString(houseList.get(houseList.size()-1).getId()));
				addressLine1.setText( houseList.get(houseList.size()-1).getStreet());
				addressLine2.setText(houseList.get(houseList.size()-1).getCity());	
				NoBedrooms.setText(Integer.toString(houseList.get(houseList.size()-1).getBedrooms()));
				NoBathrooms.setText(Integer.toString(houseList.get(houseList.size()-1).getBathrooms()));
				price.setText(Double.toString(houseList.get(houseList.size()-1).getPrice()));
				Pchange.setText(Double.toString(houseList.get(houseList.size()-1).getChange()));
				contactNo.setText((houseList.get(houseList.size()-1).getContactNo()));
			}
				});
		
		previous.addActionListener(new ActionListener() 
				{
					
				public void actionPerformed(ActionEvent e)
				{
					if (currentItem > 0)
					{
					currentItem --  ; 
					pic.setIcon(new ImageIcon(houseList.get(currentItem).getImageLocation()));
					HouseID.setText(Integer.toString(houseList.get(currentItem).getId()));
					addressLine1.setText(houseList.get(currentItem).getStreet());
					addressLine2.setText(houseList.get(currentItem).getCity());	
					NoBedrooms.setText(Integer.toString(houseList.get(currentItem).getBedrooms()));
					NoBathrooms.setText(Integer.toString(houseList.get(currentItem).getBathrooms()));
					price.setText(Double.toString(houseList.get(currentItem).getPrice()));
					Pchange.setText(Double.toString(houseList.get(currentItem).getChange()));
					contactNo.setText((houseList.get(currentItem).getContactNo()));
					}}
				});
		
		next.addActionListener(new ActionListener()
				{
			
				public void actionPerformed(ActionEvent e)
				{
					
					if (currentItem < houseList.size()-1)
					{
						currentItem ++ ; 
						pic.setIcon(new ImageIcon(houseList.get(currentItem).getImageLocation()));
						HouseID.setText(Integer.toString(houseList.get(currentItem).getId()));
						addressLine1.setText(houseList.get(currentItem).getStreet());
						addressLine2.setText(houseList.get(currentItem).getCity());	
						NoBedrooms.setText(Integer.toString(houseList.get(currentItem).getBedrooms()));
						NoBathrooms.setText(Integer.toString(houseList.get(currentItem).getBathrooms()));
						price.setText(Double.toString(houseList.get(currentItem).getPrice()));
						Pchange.setText(Double.toString(houseList.get(currentItem).getChange()));
						contactNo.setText((houseList.get(currentItem).getContactNo()));
					}}});
		
		edit.addActionListener(new ActionListener()
				{
				boolean selected = false ; 
				public void actionPerformed(ActionEvent e)
				{
					menu.setVisible(true);
					edit.setText("Save");
					price.setEditable(true);
					double old = Double.valueOf(price.getText()) ;
					 selected = true ; 
					{
					if (selected == true )
					{
						Double newP = Double.valueOf(price.getText()); 
						houseList.get(currentItem).setPrice(Double.valueOf(price.getText()));
						edit.setText("edit");
						price.setEditable(false);
						selected = false ; 
						Pchange.setText(String.valueOf(newP - old));
						
						if (newP - old > 0)
						{
							Pchange.setText("+" + (newP - old)); 
							Pchange.setForeground(Color.green);
						}
						else
						{
							Pchange.setText("-" + (newP - old)); 
							Pchange.setForeground(Color.red);
						}
					}
					
				
					}
				}
				});
		
		close.addMenuListener(new MenuListener()
		{
			
			 
			@Override
			public void menuSelected(MenuEvent e) {
			dispose(); 
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
		);
		
		create.addActionListener(new ActionListener()
		{
			 
			public void actionPerformed(ActionEvent e) {
			  	 
			if (getPassword(passWord))
			
			{
				 CreateHouseDialog createHouse = new CreateHouseDialog(houseList); 
				 menu.setVisible(false);
				 edit.setText("Edit");
			}
			 
			}

		 
			
			public boolean getPassword(String passWord)
			{
				boolean correct ; 
				String pass = scan.showInputDialog("Enter Password");	
				   
				if (pass.equalsIgnoreCase(passWord))
				{
					correct = true ; 
				}
				else
				{
					correct = false ; 
					JOptionPane.showMessageDialog(rootPane,"Password incorrect");
				}
				return correct ; 
				 }});
		
		delete.addActionListener(new ActionListener()
		{
			 
			public void actionPerformed(ActionEvent e) {
			  	 
			if (getPassword(passWord))
			{
				  
			int choice = JOptionPane.showConfirmDialog(rootPane, "This will delete the house are you sure?"); 
			if (choice == JOptionPane.YES_OPTION )
			{
			houseList.remove(currentItem);
				 if (currentItem < houseList.size()-1)
					{
						currentItem ++ ; 
						pic.setIcon(new ImageIcon(houseList.get(currentItem).getImageLocation()));
						HouseID.setText(Integer.toString(houseList.get(currentItem).getId()));
						addressLine1.setText(houseList.get(currentItem).getStreet());
						addressLine2.setText(houseList.get(currentItem).getCity());	
						NoBedrooms.setText(Integer.toString(houseList.get(currentItem).getBedrooms()));
						NoBathrooms.setText(Integer.toString(houseList.get(currentItem).getBathrooms()));
						price.setText(Double.toString(houseList.get(currentItem).getPrice()));
						Pchange.setText(Double.toString(houseList.get(currentItem).getChange()));
						contactNo.setText((houseList.get(currentItem).getContactNo()));
					}
				 else 
						if (currentItem > 0)
						{
						currentItem --  ; 
						pic.setIcon(new ImageIcon(houseList.get(currentItem).getImageLocation()));
						HouseID.setText(Integer.toString(houseList.get(currentItem).getId()));
						addressLine1.setText(houseList.get(currentItem).getStreet());
						addressLine2.setText(houseList.get(currentItem).getCity());	
						NoBedrooms.setText(Integer.toString(houseList.get(currentItem).getBedrooms()));
						NoBathrooms.setText(Integer.toString(houseList.get(currentItem).getBathrooms()));
						price.setText(Double.toString(houseList.get(currentItem).getPrice()));
						Pchange.setText(Double.toString(houseList.get(currentItem).getChange()));
						contactNo.setText((houseList.get(currentItem).getContactNo()));
						}
				 edit.setText("Edit");
				 menu.setVisible(false);
			}
			}
			}

		 
			
			public boolean getPassword(String passWord)
			{
				boolean correct ; 
				String pass = scan.showInputDialog("Enter Password");	
				   
				if (pass.equalsIgnoreCase(passWord))
				{
					correct = true ; 
				}
				else
				{
					correct = false ; 
					JOptionPane.showMessageDialog(rootPane,"Password incorrect");
				}
				return correct ; 
				 }});
		
		search.addActionListener(new ActionListener()
				{
		
				public void actionPerformed(ActionEvent e )
				{
					JComboBox box = new JComboBox(); 
					for (int i = 0 ; i< houseList.size() ; i ++ )
					{
					box.addItem(houseList.get(i).getId());
					}
					box.setEditable(false);
					String index = JOptionPane.showInputDialog(box); 
					
					
					for (int i = 0 ; i  <  houseList.size() ; i++)
					{
						if (index.equalsIgnoreCase(Integer.toString(i)))
						{
							JOptionPane.showMessageDialog(rootPane, houseList.get(i).toString()); 
						}
					}

					 
				}
				
				});
		
		summary.addActionListener(new ActionListener()
				{
				
				public void actionPerformed(ActionEvent e)
				{
					
					new HouseSummaryDialog(houseList); 
					 
				}
				}
				
				);
		
		
		add(top);
		
		add(upperBtm, "wrap, newline,  dock south");  
		upperBtm.add(bottom); 
		setSize(400, 550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	
	public static void main(String[] args) {
		HouseApplication HouseApp = new HouseApplication();
		
	}

}
