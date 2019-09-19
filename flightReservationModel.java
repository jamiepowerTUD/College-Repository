 package CA1;
//Jamie Power
//C17316471
 
import javax.swing.table.AbstractTableModel;

public class flightReservationModel extends AbstractTableModel {

	private String[] title = {"Departure", "Destination",  "Flight Num",  "Number of Txx", "First Class Available"}; 
	
	
	private Object[][] flightData = {{"Dublin", "Copenhagen", "SK358", new Integer(200), new Boolean(true)},
										{"Dublin", "Oslo", "DY1363", new Integer(27), new Boolean(false)},
										{"San Francisco", "Dublin", "EI147", new Integer(30), new Boolean(true) },
										{"Edinburgh", "Dublin", "EI147", new Integer(50), new Boolean(true)},
										{"New York", "Dublin", "EI109 ", new Integer(40),  new Boolean(false) }
	}; 
	
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return title.length; 
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return flightData.length ;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return flightData[rowIndex][columnIndex];
	}
	
	public String getColumnName(int column) {
        return title[column] ; 
    }

	
	public boolean isCellEditable(int row, int col) {

        if (col < 2 ) {
            return false;
        } else {
            return true;
        }
    }
	
	 public Class getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }
}
