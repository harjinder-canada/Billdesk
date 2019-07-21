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
				Connection con=DriverManager.getConnection("jdbc:mysql:///project","root","");
				Statement st=con.createStatement();
				String qr="select * from water where meter_no='"+smeter+"'";
				ResultSet rs=st.executeQuery(qr);
				if(rs.next()){
					if(rs.getString(6).equals("1")){
						JOptionPane.showMessageDialog(null,"electricity BILL is already paid");
					}else{
						txtunit.setText(rs.getString(3));
						txtbill.setText(rs.getString(4));
					}
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
		}
		if(gm.getSource()==submit){
			smeter=txtmeter.getText();
			sbill=txtbill.getText();
			samount=txtamount.getText();
			int amt=Integer.parseInt(samount);
			int bill=Integer.parseInt(sbill);
			if(amt<bill){
				JOptionPane.showMessageDialog(null,"Please Enter proper ammount");
				
			}
		else{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql:///project","root","");
				Statement st=con.createStatement();
				String qr="update water set status=1,paid='"+sbill+"' where meter_no='"+smeter+"'";
				
				int a=st.executeUpdate(qr);
				if(a>0){
					JOptionPane.showMessageDialog(null,"Paid");
					}else{
					JOptionPane.showMessageDialog(null,"Error while paying please try again");
					}
				}catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
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