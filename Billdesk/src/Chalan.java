import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Chalan extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel bike,chalan,fine,paid;
	JButton search,submit,reset;
	JTextField txtbike,txtchalan,txtfine,txtpaid;
	JPanel panl,master,grp;
	String sbike,sfine,spaid;
	
	public void actionPerformed(ActionEvent gm){
		sbike=txtbike.getText();
		
		if(gm.getSource()==search){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql:///project","root","");
				Statement st=con.createStatement();
				String qr="select * from chalan where bike_no='"+sbike+"'";
				ResultSet rs=st.executeQuery(qr);
				if(rs.next()){
					if(rs.getString(6).equals("1")){
						JOptionPane.showMessageDialog(null,"Chllaan is already paid");
					}else{
						txtchalan.setText(rs.getString(3));
						txtfine.setText(rs.getString(4));
					}
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
		}
		if(gm.getSource()==submit){
			sbike=txtbike.getText();
			sfine=txtfine.getText();
			spaid=txtpaid.getText();
			int fine=Integer.parseInt(sfine);
			int paid=Integer.parseInt(spaid);
			if(paid<fine){
				JOptionPane.showMessageDialog(null,"Please eneter full amount");
			}else{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql:///project","root","");
				Statement st=con.createStatement();
				String qr="update chalan set status=1,paid='"+spaid+"' where bike_no='"+sbike+"'";
				
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
		txtbike.setText("");
		txtchalan.setText("");
		txtfine.setText("");
		txtpaid.setText("");
	}
}
	Chalan(){
		bike=new JLabel("BIKE_NO");
		chalan=new JLabel("CHALAN_TYPE");
		fine=new JLabel("FINE");
		paid=new JLabel("PAID AMOUNT");
		
		txtbike=new JTextField(10);
		txtchalan=new JTextField(10);
			txtchalan.setEnabled(false);
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
			grp.add(txtbike);
			grp.add(search);
			grp.setBackground(Color.blue);
		
		panl=new JPanel(new GridLayout(5,2,5,5));
		setSize(500,500);
		setTitle("PAY CHALAN");
		panl.add(bike);
		panl.add(grp);
		
		panl.add(chalan);
		panl.add(txtchalan);
		
		panl.add(fine);
		panl.add(txtfine);
		
		panl.add(paid);
		panl.add(txtpaid);
				
		panl.add(submit);
		panl.add(reset);
		
		master=new JPanel();
			master.setBackground(Color.cyan);
			master.add(panl);
		add(master);
		setVisible(true);
	}
}