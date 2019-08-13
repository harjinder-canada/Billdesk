import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class Login extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel username,password,login_type;
	JTextField username_txt;
	JPasswordField password_txt;
	JComboBox<String> select_box;
	JButton login,reset;
	JPanel panel,master;
	String username_val,password_val,selected_val;
	
	public void actionPerformed(ActionEvent g){
		if(g.getSource()==login){
			username_val=username_txt.getText();
			password_val=new String (password_txt.getPassword());
			selected_val=(String) select_box.getSelectedItem();
			if(username_val.equals("") || password_val.equals("")){
				JOptionPane.showMessageDialog(master,"all flieds is require :");
			}
			else{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection connect=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
					Statement st=connect.createStatement();
					
					if(selected_val.equals("ADMIN")){
						String query="select * from admin where username='"+username_val+"' and password='"+password_val+"'";
						ResultSet rs=st.executeQuery(query);
						if(rs.next()){
							new AdminMenu(username_val, rs.getString(1));
							setVisible(false);
						}else{
							JOptionPane.showMessageDialog(master,"User name & password are wrong:");
						}
					}else if(selected_val.equals("Employee")){
						String query="select * from employees where username='"+username_val+"' and password='"+password_val+"'";
						ResultSet rs=st.executeQuery(query);
						if(rs.next()){
							new CustomerMenu(username_val, rs.getString(1));
							setVisible(false);
						}else{
							JOptionPane.showMessageDialog(master,"User name & password are wrong:");
						}
					}
				}
				catch(Exception gm){
					JOptionPane.showMessageDialog(master,gm);
				}
			}
		}
		if(g.getSource()==reset){
			username_txt.setText("");
			password_txt.setText("");
			select_box.setSelectedItem("=Select type=");
		}
	}
	
	Login(){
			setTitle("Login Form");
			setSize(500,500);
			username =new JLabel("Username :");
			password=new JLabel("Password :");
			login_type=new JLabel("Login Type :");
			username_txt=new JTextField(20);
			password_txt=new JPasswordField(20);
			login=new JButton("Login");
			reset=new JButton("Reset");
			
			select_box=new JComboBox<String>();
			select_box.addItem("=Select type=");
			select_box.addItem("ADMIN");
			select_box.addItem("Employee");
			
			panel=new JPanel(new GridLayout(5,10,10,10));
			
			panel.setBackground(Color.orange);
			panel.add(login_type);
			panel.add(select_box);
			panel.add(username);
			panel.add(username_txt);
			
			panel.add(password);
			panel.add(password_txt);
			
			panel.add(login);
			login.addActionListener(this);
			panel.add(reset);
			reset.addActionListener(this);
			
			master=new JPanel();
			master.setBackground(Color.blue);
			master.add(panel);
			add(master);
			setVisible(true);
			
	}
	public static void main(String arg[]){
		//new Login();
		new ElectricityBill();
	}
}
