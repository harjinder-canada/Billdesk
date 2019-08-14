import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Chalan extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel chalan_no,vehical_no,fine,paid;
	JButton search,submit,reset;
	JTextField txtchalan,txtvehical,txtfine,txtpaid;
	JPanel panel,master,grp;
	String chalan_no_val,fine_val,paid_val;
	
	public void actionPerformed(ActionEvent gm){
		chalan_no_val=txtchalan.getText();
		
		if(gm.getSource()==search){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
				Statement st=con.createStatement();
				String qr="select * from chalan where chalan_no='"+chalan_no_val+"'";
				ResultSet rs=st.executeQuery(qr);
				if(rs.next()){
					if(rs.getString(5).equals("1")){
						JOptionPane.showMessageDialog(master,"Chalan is already paid");
					}else{
						txtvehical.setText(rs.getString(2));
						txtfine.setText(rs.getString(3));
					}
				}else {
					JOptionPane.showMessageDialog(master,"No Record Found!!");
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(master,e);
			}
		}
		if(gm.getSource()==submit){
			chalan_no_val=txtchalan.getText();
			fine_val=txtfine.getText();
			paid_val=txtpaid.getText();
			double fine=Double.parseDouble(fine_val);
			double paid=Double.parseDouble(paid_val);
			if(paid<fine || paid>fine){
				JOptionPane.showMessageDialog(master,"Please enter exact fine amount");
			}else{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
				Statement st=con.createStatement();
				String qr="update chalan set status=1, amount_paid='"+paid_val+"' where chalan_no='"+chalan_no_val+"'";
				
				int a=st.executeUpdate(qr);
				if(a>0){
					JOptionPane.showMessageDialog(master,"Fine Paid Successfully!!");
				}else{
					JOptionPane.showMessageDialog(master,"Error while paying please try again");
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(master,e);
			}
		}
	}
	if(gm.getSource()==reset){
		txtvehical.setText("");
		txtchalan.setText("");
		txtfine.setText("");
		txtpaid.setText("");
	}
}
	Chalan(){
		vehical_no=new JLabel("VEHICLE NO");
		chalan_no=new JLabel("CHALLAN");
		fine=new JLabel("FINE");
		paid=new JLabel("PAID AMOUNT");
		
		txtchalan=new JTextField(10);
		txtvehical=new JTextField(10);
			txtvehical.setEnabled(false);
		txtfine=new JTextField(10);
			txtfine.setEnabled(false);
		txtpaid=new JTextField(10);
		
		
		search=new JButton("SEARCH");
			 search.addActionListener(this);
		submit=new JButton("SUBMIT");
			submit.addActionListener(this);
		reset=new JButton("RESET");
			reset.addActionListener(this);
		grp=new JPanel();
			grp.add(txtchalan);
			grp.add(search);
			grp.setBackground(Color.blue);
		
		panel=new JPanel(new GridLayout(5,2,5,5));
		setSize(500,500);
		setTitle("PAY CHALLAN");
		panel.add(chalan_no);
		panel.add(grp);
		
		panel.add(vehical_no);
		panel.add(txtvehical);
		
		panel.add(fine);
		panel.add(txtfine);
		
		panel.add(paid);
		panel.add(txtpaid);
				
		panel.add(submit);
		panel.add(reset);
		
		master=new JPanel();
			master.setBackground(Color.cyan);
			master.add(panel);
		add(master);
		setVisible(true);
	}
}