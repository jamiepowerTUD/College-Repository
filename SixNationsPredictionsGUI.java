package labExam;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


import java.awt.event.*;
import java.awt.*;
import java.io.*;
// Jamie Power 
// C17316471

public class SixNationsPredictionsGUI extends JFrame{

	// Menu structure
	JMenuBar myBar;
	JMenu fileMenu, recordMenu,  exitMenu;
	JMenuItem fileLoad, fileSaveAs, addPrediction, removePrediction, exitProgram;
	
		
	// Array of data types to be used in combo box when defining new structure
	private String[] teamsArr = {"Ireland", "England", "Wales", "France", "Scotland", "Italy"};
	private JComboBox teamComboBox = new JComboBox(teamsArr);
    
	// Table Model
	JPanel p;
	MyTableModel tm;
	JTable myTable;
	JScrollPane myPane;
	
	ArrayList<MyPrediction> predictions = new ArrayList<MyPrediction>(); 
	
	// Used to indicate whether data is already in a file
	File currentFile;
	private JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.dir"))); 
	public SixNationsPredictionsGUI(){  
		// Setting up menu
		createMenuBar();

		p = new JPanel();
		tm = new MyTableModel(predictions);
		myTable = new JTable(tm);
		myTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
		myPane = new JScrollPane(myTable);
		myTable.setSelectionForeground(Color.white);
		myTable.setSelectionBackground(Color.red);
		myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		//setup combobox
        setUpTeamColumn(myTable, myTable.getColumnModel().getColumn(1));

        // Associating event listeners with menu items

        //TODO:(1) File - Open - Loading the contents of a file into the table, including all warning messages
        // Use provided function: readDataFile() to save the data into the file
        
        fileLoad.addActionListener(new ActionListener()
				
			{

				public void actionPerformed(ActionEvent e) 
				{
					int ret = jfc.showOpenDialog(rootPane); 
					//jfc.setCurrentDirectory(new File(System.getProperty("Jamie_Power")));
					ArrayList<MyPrediction> predictions = new ArrayList();
					
					if(ret == JFileChooser.APPROVE_OPTION){
						
						if (JOptionPane.showConfirmDialog(rootPane, "You will replace the existing data are you sure you want to do this?") == JOptionPane.YES_OPTION)
						{
						jfc.setDialogTitle("Open Directory");
						File fileOpenPath = jfc.getSelectedFile();
						readDataFile(fileOpenPath);
						// I can't seem to load the data that was provided but when i created my own predictions that seemed to work i included them in the folder under "MyRecords.dat"
						}
				
					
				}
					}
				}
					
					);
        
        
        
        
        //TODO:(2) Save the data from the table into the file. 
		// Use provided function: writeDataFile() to save the data into the file
		fileSaveAs.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) 
					{
						if (predictions.isEmpty())
						{
							JOptionPane.showMessageDialog(rootPane, "Please add predictions to the system ",null ,JOptionPane.ERROR_MESSAGE);
						}
						else 
						{
							
							jfc.showSaveDialog((rootPane)); 
							jfc.setDialogTitle("Save Directory");
							File fileSavePath = jfc.getSelectedFile() ; 
							writeDataFile(fileSavePath); 
						}}});
		
		

        //TODO:(3) Prediction - Add Row
		addPrediction.addActionListener(new ActionListener()
				
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						MyPrediction mp = new MyPrediction("","",0,0.0,"");
						predictions.add(mp);
						tm.fireTableDataChanged();
					}
				
				
				
				}
				);
		
		
		
		
        //TODO:(4) Prediction - Remove Row including warning messages
		removePrediction.addActionListener(new ActionListener()
				
				{

					@Override
					public void actionPerformed(ActionEvent e) {
				 if (predictions.isEmpty())
				 {
					 JOptionPane.showMessageDialog(rootPane, "There are no predictions to delete");
				 }
				 else
						if (myTable.getSelectedRow() == -1)
						{
							JOptionPane.showMessageDialog(rootPane, "Please select a row to delete ");
						}
						
					else
					{
						
						if (JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete the selected row?", "WARNING",JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION)
						{
						predictions.remove(myTable.getSelectedRow());
						tm.fireTableDataChanged();
					}
			
				}
					}
				}
				);
		

		// exits program from menu
		exitProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDown();
			}
		});

		// exits program by closing window
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				closeDown();
			}
		}); // end windowlistener
		
		// Adding menu bar and table to panel
		this.setJMenuBar(myBar);
		this.add(myPane, BorderLayout.CENTER);
		
		this.setTitle("2019 Six Nations Rubgy");
		this.setVisible(true);
		this.pack();
	} // constructor

	private void createMenuBar() {
		fileLoad = new JMenuItem("Open");
		fileSaveAs = new JMenuItem("Save As");
		
		fileMenu = new JMenu("File");
		fileMenu.add(fileLoad);
		fileMenu.add(fileSaveAs);
		
		addPrediction = new JMenuItem("Add");
		removePrediction = new JMenuItem("Remove");
		
		recordMenu = new JMenu("Prediction");
		recordMenu.add(addPrediction);
		recordMenu.add(removePrediction);
		
		
		exitProgram = new JMenuItem("Exit Program");
		exitMenu = new JMenu("Exit");
		exitMenu.add(exitProgram);
		
		myBar = new JMenuBar();
		myBar.add(fileMenu);
		myBar.add(recordMenu);
		myBar.add(exitMenu);
	}

    public void setUpTeamColumn(JTable table,TableColumn teamColumn) {	  
      	  teamColumn.setCellEditor(new DefaultCellEditor(teamComboBox));
      	
          //Set up tool tips for the sport cells.
          DefaultTableCellRenderer renderer =
                  new DefaultTableCellRenderer();
          renderer.setToolTipText("Click for combo box");
          teamColumn.setCellRenderer(renderer);
      }
    
	public void writeDataFile(File f)
	{
		ObjectOutputStream out = null;
		   try {        	
	            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(jfc.getSelectedFile())));
	            
	          
	                out.writeObject(predictions);
		   }
	            
	            catch (IOException x )
	            {
	            	x.printStackTrace();
	            }
	            finally 
	            {
	            	try {
						out.close();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
	            }
		 
	}

	



	

	public void readDataFile(File f)
	{
		ArrayList<MyPrediction> temp = new ArrayList(); 
		ObjectInputStream  in = null ;
		File fileOpenPath =  f ; 
		
		try 
		{
			try {
				in = new  ObjectInputStream(new BufferedInputStream(new FileInputStream(fileOpenPath)));
				try {
				
					
					temp = (ArrayList<MyPrediction>)in.readObject();
					predictions.clear();
					predictions.addAll(temp);
				    for(int i = 0; i<temp.size(); i++) {

                          tm.fireTableDataChanged();

                  

                   }
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		 
			
		}
		finally
		{
			try {
				in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
		
		
	

	public void closeDown() {
			System.exit(0);
	}

	public static void main (String args[]){
		new SixNationsPredictionsGUI();
	} // main
} //class