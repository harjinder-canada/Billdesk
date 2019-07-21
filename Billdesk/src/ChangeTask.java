import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
class Edit extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel usr,name,work;
	JButton edit,reset,search;
	JTextField txtusr,txtname;
	JComboBox<String> cmbwork;
	JPanel panl,panl1,master;
	String suname,sname;
	int swork = 0;
	public void actionPerformed(ActionEvent m){
		if(m.getSource()==edit){
			suname=txtusr.getText();
			sname=txtname.getText();
			switch((String) cmbwork.getSelectedItem()) {
			case "Challan Collector": 
				swork  = 1;
				break;
			case "Water Bill Collector":
				swork  = 2;
				break;
			case "Electricity Bill Collector":
				swork  = 3;
				break;
			default :
				swork  = 0;
				break;
		}
			if(swork == 0){
				JOptionPane.showMessageDialog(master,"Select Work");
			}else{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
					Statement state=con.createStatement();
					String q="update employees set work_assigned='"+swork+"' where username='"+suname+"'";
					int a=state.executeUpdate(q);
					if(a>0){
						JOptionPane.showMessageDialog(null,"Work Updated");
						edit.setEnabled(false);
						txtusr.setText("");
						txtusr.setEnabled(true);
						txtname.setText("");
						cmbwork.setSelectedItem("=Select Work=");
						cmbwork.setEnabled(false);
					}
					else{
						JOptionPane.showMessageDialog(null,"Work Not Updated");
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
					
		}
		
		if(m.getSource()==search){
			suname=txtusr.getText();
			if(suname.equals("")){
				JOptionPane.showMessageDialog(master,"User_name field is require:");
			}else{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
					Statement state=con.createStatement();
					String q="select * from employees where username='"+suname+"'";
					ResultSet rs=state.executeQuery(q);
					if(rs.next()){
						txtusr.setEnabled(false);
						txtname.setText(rs.getString("username"));
						cmbwork.setSelectedItem(rs.getString("work_assigned"));
						cmbwork.setEnabled(true);
						edit.setEnabled(true);
					}
					else{
						JOptionPane.showMessageDialog(null,"NO RECORD FOUND");
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
					System.out.println("Exception is :"+e);
				}
			}
			
		}
		if(m.getSource()==reset){
			edit.setEnabled(false);
			txtusr.setText("");
			txtusr.setEnabled(true);
			txtname.setText("");
			cmbwork.setSelectedItem("=Select Work=");
			cmbwork.setEnabled(false);
		}

	}
	Edit(){
		usr=new JLabel("USER NAME");
		name= new JLabel("NAME");
		work=new JLabel("WORK");
		
		txtusr=new JTextField(10);
		txtname=new JTextField(20);
			txtname.setEnabled(false);
		cmbwork=new JComboBox<String>();
			cmbwork.addItem("=Select Work=");
			cmbwork.addItem("Challan Collector");
			cmbwork.addItem("Water Bill Collector");
			cmbwork.addItem("Electricity Bill Collector");
		cmbwork.setEnabled(false);
		edit=new JButton("EDIT");
			edit.addActionListener(this);
			edit.setEnabled(false);
		reset=new JButton("RESET");
			reset.addActionListener(this);
		search=new JButton("SEARCH");
			search.addActionListener(this);
		
		panl1=new JPanel();
			panl1.setBackground(Color.cyan);
			panl1.add(txtusr);
			panl1.add(search);
		panl=new JPanel(new GridLayout(4,2,5,5));
		panl.add(usr);
		panl.add(panl1);
		
		panl.add(name);
		panl.add(txtname);
		
		panl.add(work);
		panl.add(cmbwork);
		
		panl.add(edit);
		panl.add(reset);
		
		master=new JPanel();
			master.add(panl);
		add(master);
		setSize(800,400);
			setTitle("EDIT FORM");
			setVisible(true);
	}
}