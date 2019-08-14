import java.awt.Color;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ListEmployees extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] columnNames = {"ID", "User name", "Name", "Task Assigned", "Email", "Phone No"};
	Object emp_data[][] = new Object[6][10];
	
	String first_name_val, last_name_val, email_val, phone_val, id;
	JPanel master, panel;
	JTable table;
	
	public void actionPerformed(ActionEvent gm){	

	}

	ListEmployees(){
		setTitle("Employees List");
		setSize(800,500);
		 
		try {

				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
				Statement state=con.createStatement();
				String q="select * from employees";
				ResultSet rs=state.executeQuery(q);
				int i = 0;
					while (rs.next()) {
						emp_data[i][0] = rs.getString("id");
						emp_data[i][1] = rs.getString("username");
						emp_data[i][2] = rs.getString("first_name");
						switch((String) rs.getString("work_assigned")) {
						case "1": 
							 emp_data[i][3] = "Challan Collector";
							break;
						case "2":
							emp_data[i][3]  = "Water Bill Collector";
							break;
						case "3":
							emp_data[i][3]  = "Electricity Bill Collector";
							break;
						default :
							emp_data[i][3]  = "No Work Assigned";
							break;
					}
						emp_data[i][4] = rs.getString("email_id");
						emp_data[i][5] = rs.getString("phone_no");
						i++;
					}
				
		} catch (Exception ex) {
				JOptionPane.showMessageDialog(master, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		System.out.println(emp_data);
		DefaultTableModel model = new DefaultTableModel(emp_data, columnNames);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		 
		
		panel=new JPanel();
		setTitle("List of Employees");
		setSize(800,500);
		panel.add(new JScrollPane(table));
		
		 master=new JPanel();
		 master.add(panel);
		 master.setBackground(Color.cyan);
		 add(master);
		 setVisible(true);
	}

}
