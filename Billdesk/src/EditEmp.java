import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class EditEmp extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel username,password,first_name, last_name, email, phone_no, city, state, zip_code, assign_work;
	JTextField username_txt,password_txt, first_name_txt, last_name_txt, email_txt, phone_txt, zip_code_txt;
	JComboBox<String> city_select, state_select, select_work;
	JButton register, reset, edit;
	JPanel panel, master;
	String username_val,password_val, first_name_val, last_name_val, email_val, phone_val, zip_code_val,city_val, state_val, user_name, employee_id;
	int assigned_work_val=0;
	
	public void actionPerformed(ActionEvent g){
		if(g.getSource()==edit){
			username_val = username_txt.getText();
			password_val = password_txt.getText();
			first_name_val = first_name_txt.getText();
			last_name_val = last_name_txt.getText();
			email_val = email_txt.getText();
			phone_val = phone_txt.getText();
			city_val = (String) city_select.getSelectedItem();
			state_val = (String) state_select.getSelectedItem();
			zip_code_val=zip_code_txt.getText();
						
			if(email_val.equals("") || phone_val.equals("") || state_val.equals("=Select State=")){
				JOptionPane.showMessageDialog(master,"All fileds is require");
			}else{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
					Statement st=cn.createStatement();
					String q="update employee set email='"+email_val+"',phone_no='"+phone_val+"',state='"+state_val+"',city='"+city_val+"' where username='"+username_val+"' and id='"+employee_id+"'  ";
					System.out.println(q);
					int a=st.executeUpdate(q);
					if(a>0){
						JOptionPane.showMessageDialog(null,"Values updated");
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null,"Values not updated");
					}
				 }catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
		}
		if(g.getSource()==reset){
			try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
			Statement st=con.createStatement();
			String qr="select * from employee where username='"+user_name+"' and id="+employee_id;
			ResultSet rs=st.executeQuery(qr);
			if(rs.next()){
				username_txt.setText(rs.getString(2));
				email_txt.setText(rs.getString(6));
				state_select.setSelectedItem(rs.getString(9));
				city_select.setSelectedItem(rs.getString(8));
				phone_txt.setText(rs.getString(7));
				
			}
		}	
		catch(Exception e){
			JOptionPane.showMessageDialog(master,e);
				}
		}
	}
	
	EditEmp(String user,String user_id){
	user_name=user;
	employee_id= user_id;
	username=new JLabel("USERNAME");
	email=new JLabel("EMAIL");
	phone_no=new JLabel("PHONE");
	state=new JLabel("STATE");
	city=new JLabel("CITY");
	
	username_txt = new JTextField(10);
	username_txt.setEnabled(false);
	
	email_txt = new JTextField(20);
	phone_txt = new JTextField(10);

	city_select=new JComboBox<String>();
	city_select.addItem("=Select City=");
	city_select.addItem("Toronto");
	city_select.addItem("Scarborough");
	city_select.addItem("Markham");
		
	state_select=new JComboBox<String>();
	state_select.addItem("=Select State=");
	state_select.addItem("Ontario");
	state_select.addItem("British Columbia");
	
	edit=new JButton("EDIT");
	edit.addActionListener(this);
	
	panel=new JPanel(new GridLayout(7,2,5,5));
	setSize(500,500);
	setTitle("EDIT EMPLOYEE");
	panel.add(username);
	panel.add(username_txt);
	
	panel.add(email);
	panel.add(email_txt);
	
	panel.add(phone_no);
	panel.add(phone_txt);
	
	panel.add(state);
	panel.add(state_select);
	
	panel.add(city);
	panel.add(city_select);
	
	panel.add(edit);
		edit.addActionListener(this);
	panel.add(reset);
		reset.addActionListener(this);
	
	master=new JPanel();
		master.add(panel);
	add(master);	
	
	try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:///project","root","");
			Statement st=con.createStatement();
			String qr="select * from employee where username='"+user_name+"' and id="+user_id;
			ResultSet rs=st.executeQuery(qr);
			if(rs.next()){
				username_txt.setText(rs.getString(2));
				email_txt.setText(rs.getString(6));
				state_select.setSelectedItem(rs.getString(9));
				city_select.setSelectedItem(rs.getString(8));
				phone_txt.setText(rs.getString(7));
				
			}
		}	
		catch(Exception e){
			JOptionPane.showMessageDialog(master,e);
				}
	setVisible(true);	
	}
}	