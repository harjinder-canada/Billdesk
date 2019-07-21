import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

class ChangePassword extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel oldpass,newpass,confpass;
	JPasswordField oldp,newp,confp;
	JButton submit,reset;
	JPanel panl,master;
	String suname,sid,soldpass,snewpass,sconfpass,stype;
	
	public void actionPerformed(ActionEvent gk){
		if(gk.getSource()==submit){
			soldpass=new String(oldp.getPassword());
			snewpass=new String (newp.getPassword());
			sconfpass=new String (confp.getPassword());
			if(soldpass.equals("")||snewpass.equals("")||sconfpass.equals("")){
				JOptionPane.showMessageDialog(master,"All fields is require :");
			}
			else if(!snewpass.equals(sconfpass)){
				JOptionPane.showMessageDialog(null,"New password and Confirm Password is not match:");
			}
			else{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql:///project","root","");
					Statement st=con.createStatement();
					String g="select * from "+stype+" where username='"+suname+"' and password='"+soldpass+"' and id="+sid;
					ResultSet rs=st.executeQuery(g);
					if(rs.next()){
						String k="update "+stype+" set password='"+snewpass+"'where username='"+suname+"' and id='"+sid+"'";
						int a=st.executeUpdate(k);
						if(a>0){
							JOptionPane.showMessageDialog(null,"password updated");
							oldp.setText("");
							newp.setText("");
							confp.setText("");
						}
					}else{
							JOptionPane.showMessageDialog(null,"Old password not matched");
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
			
		}
		if(gk.getSource()==reset){
			oldp.setText("");
			newp.setText("");
			confp.setText("");
		}
	}
	ChangePassword(String name,String uid,String ty){
		suname=name;
		sid=uid;
		stype=ty;
		setTitle("Change Password");
		setSize(500,500);
		oldpass=new JLabel("Old password");
		newpass=new JLabel("new password");
		confpass=new JLabel("Conform password");
		
		oldp=new JPasswordField(10);
		newp=new JPasswordField(10);
		confp=new JPasswordField(10);
		
		submit=new JButton("SUBMIT");
			submit.addActionListener(this);
		reset=new JButton("RESET");
			reset.addActionListener(this);
		
		panl=new JPanel(new GridLayout(4,2,5,5));
		panl.add(oldpass);
		panl.add(oldp);
		
		panl.add(newpass);
		panl.add(newp);
		
		panl.add(confpass);
		panl.add(confp);
		
		panl.add(submit);
		panl.add(reset);
		
		master=new JPanel();
			master.add(panl);
		add(master);
		
		setVisible(true);
	}

}