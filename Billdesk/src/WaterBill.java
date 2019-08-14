import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class WaterBill extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel meter,unit,amount,bill;
	JButton search,submit,reset;
	JTextField txtmeter,txtbill,txtunit,txtamount;
	JPanel panl,master,grp;
	String smeter,samount,sbill;
	
	public void actionPerformed(ActionEvent gm){
		smeter=txtmeter.getText();
		if(gm.getSource()==search){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
				Statement st=con.createStatement();
				String qr="select * from water_meter where meter_id='"+smeter+"'";
				ResultSet rs=st.executeQuery(qr);
				if(rs.next()){
					if(rs.getString(6).equals("1")){
						JOptionPane.showMessageDialog(master,"Water BILL is already paid");
					}else{
						int new_reading = Integer.parseInt(rs.getString(3)) - Integer.parseInt(rs.getString(2));
						txtunit.setText(Integer.toString(new_reading));
						txtbill.setText(rs.getString(4));
					}
				}else {
					JOptionPane.showMessageDialog(master,"No Record Found!!");
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(master,e);
			}
		}
		if(gm.getSource()==submit){
			smeter=txtmeter.getText();
			sbill=txtbill.getText();
			samount=txtamount.getText();
			double bill=Double.parseDouble(sbill);
			double amount=Double.parseDouble(samount);
			if(amount<bill || amount>bill){
				JOptionPane.showMessageDialog(master,"Please enter exact bill amount.");
			}else{
			
				try{
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
					Statement st=con.createStatement();
					String qr="update water_meter set status=1, amount_paid='"+sbill+"' where meter_id='"+smeter+"'";
					
					int a=st.executeUpdate(qr);
					if(a>0){
						JOptionPane.showMessageDialog(master,"Bill Paid Successfully!!");
						}else{
						JOptionPane.showMessageDialog(master,"Error while paying please try again");
						}
					}catch(Exception e){
					JOptionPane.showMessageDialog(master,e);
					}
				}
			}
		if(gm.getSource()==reset){
			txtmeter.setText("");
			txtbill.setText("");
			txtunit.setText("");
			txtamount.setText("");
		}
	}
	WaterBill(){
		meter=new JLabel("Meter _No");
		unit=new JLabel("Unit_Consume");
		bill=new JLabel("Bill");
		amount=new JLabel("Amount_paid");
		
		
		txtmeter=new JTextField(10);
		txtunit=new JTextField(10);
			txtunit.setEnabled(false);
		txtbill=new JTextField(10);
			txtbill.setEnabled(false);
		txtamount=new JTextField(10);
		
		search=new JButton("SEARCH");
		 search.addActionListener(this);
		submit=new JButton("SUBMIT");
			submit.addActionListener(this);
		reset=new JButton("RESET");
			reset.addActionListener(this);
		grp=new JPanel();
			grp.add(txtmeter);
			grp.add(search);
			grp.setBackground(Color.blue);
		
		panl=new JPanel(new GridLayout(5,2,5,5));
		setTitle("PAY Water BILL");
		setSize(500,500);
		panl.add(meter);
		panl.add(grp);
		
		panl.add(unit);
		panl.add(txtunit);
		
		panl.add(bill);
		panl.add(txtbill);
		
		panl.add(amount);
		panl.add(txtamount);
		
		
		panl.add(submit);
		panl.add(reset);
		
		master=new JPanel();
			master.setBackground(Color.cyan);
			master.add(panl);
		add(master);
		setVisible(true);
	}
	public static void main(String arg[]){
		new WaterBill();
	}
}