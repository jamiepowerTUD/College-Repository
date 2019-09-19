package CA1;
// Jamie Power
// C17316471
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;

import CA1.flightReservationModel;

public class flightReservationFrame extends JFrame {
	
	
	JLabel departL = new JLabel("Depature"); 
	JLabel destL = new JLabel("Destination"); 
	JLabel noTickets = new JLabel("Number of tickets : ");
	// Combo box destinations
	String[] destinations = {"Copenhagen", "Dublin", "Edinburgh", "London", "New York", "Oslo", "San Francisco"} ; 
	String[] departures = {"Copenhagen", "Dublin", "Edinburgh", "London", "New York", "Oslo", "San Francisco"} ; 
	JComboBox depart = new JComboBox(departures);
	JComboBox dest = new JComboBox(destinations);
	JButton search = new JButton("Search"); 
	JButton showAll= new JButton("Show All"); 
	JButton purchase = new JButton("Purchase"); 
	JTextField tickField = new JTextField(10); 
	JPanel pnl = new JPanel(); 
	JPanel pnl2 = new JPanel(); 
	JPanel pnl3 = new JPanel(); 
	Date today = new Date();
	// Date formatter 
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	String strDate = df.format(today);
	JLabel date = new JLabel("Todays date : " + strDate); 
	JCheckBox check = new JCheckBox() ; 
	 
	flightReservationModel model = new flightReservationModel(); 
	 JTable table = new JTable(model);      
 	// Scroll table
	 JScrollPane scrollPane = new JScrollPane(table);
 	TableRowSorter<flightReservationModel> sort  = new TableRowSorter<flightReservationModel>(model);
	// Arraylist to be populated by filters 
 	ArrayList filters = new ArrayList(); 
 
	
	public flightReservationFrame()
	{
	
		
		FlowLayout lay = new FlowLayout(); 
	setLayout(lay); 
	lay.setVgap(0);
	add(pnl);	 
	pnl.add(departL);
	dest.setSelectedIndex(1);
	depart.setSelectedIndex(1);
	pnl.add(depart); 
	pnl.add(destL); 
	pnl.add(dest); 
	pnl.add(search);
	pnl.add(showAll); 
	
	pnl2.add(scrollPane); 
	add(pnl2);
 	table.setFillsViewportHeight(true);
	table.setPreferredScrollableViewportSize(new Dimension(700, 70));
	table.setRowSorter(sort); 
	add(pnl3); 
	pnl3.add(noTickets);
 // formatted text field 
	  NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    JFormattedTextField field = new JFormattedTextField(formatter);
	    field.setColumns(10);
	    pnl3.add(field); 
	pnl3.add(purchase); 
	pnl3.add(date); 
	date.setForeground(Color.BLUE);
	setVisible(true); 
	setSize(750,250); 
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	// Search functionality 
	search.addActionListener(new ActionListener()
			{
			
		public void actionPerformed(ActionEvent e)
		{
	if (dest.getSelectedItem().toString().equalsIgnoreCase(depart.getSelectedItem().toString()))
			{
			JOptionPane.showMessageDialog(rootPane, "Error : Departures and Destinations cannot be the same");
			
			dest.setSelectedIndex(0);
			depart.setSelectedIndex(0);
			}
	else {
		  	filters.clear(); 
		 
		  // Using an AND filter , the table will only display values when both filters are TRUE
		  	// these filters are stored in an arrayList above
		  	filters.add(RowFilter.regexFilter("(?i)" + depart.getSelectedItem()));
		   filters.add(RowFilter.regexFilter("(?i)" + dest.getSelectedItem()));
		 
		    
		   sort.setRowFilter(RowFilter.andFilter(filters)); 
		   
		
		  	}
	}});
	// Show all functionality
	showAll.addActionListener(new ActionListener()
			{	
		public void actionPerformed(ActionEvent e )
		{
			sort.setRowFilter(null);
		}
	
	
	});
	
	 
 
	}
	
	
	
	
	public static void main(String[] args)
	{
		new flightReservationFrame(); 
	}
}
