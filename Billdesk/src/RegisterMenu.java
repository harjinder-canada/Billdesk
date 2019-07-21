import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class RegisterMenu extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel username,password,first_name, last_name, email, phone_no, city, state, zip_code, assign_work;
	JTextField username_txt,password_txt, first_name_txt, last_name_txt, email_txt, phone_txt, zip_code_txt;
	JComboBox<String> city_select, state_select, select_work;
	JButton register, reset;
	JPanel panel, master;
	String username_val,password_val, first_name_val, last_name_val, email_val, phone_val, zip_code_val,city_val, state_val;
	int assigned_work_val=0;
	
	public void actionPerformed(ActionEvent g){
		if(g.getSource() == register){
			username_val = username_txt.getText();
			password_val = password_txt.getText();
			first_name_val = first_name_txt.getText();
			last_name_val = last_name_txt.getText();
			email_val = email_txt.getText();
			phone_val = phone_txt.getText();
			city_val = (String) city_select.getSelectedItem();
			state_val = (String) state_select.getSelectedItem();
			zip_code_val=zip_code_txt.getText();
			
			switch((String) select_work.getSelectedItem()) {
				case "Challan Collector": 
					assigned_work_val  = 1;
					break;
				case "Water Bill Collector":
					assigned_work_val  = 2;
					break;
				case "Electricity Bill Collector":
					assigned_work_val  = 3;
					break;
				default :
					assigned_work_val  = 0;
					break;
			}
			
			if(username_val.equals("") || email_val.equals("") || city_val.equals("=Select city=")|| assigned_work_val == 0){
				JOptionPane.showMessageDialog(master,"All fields are require:");
			}
			else{
				try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
				Statement st=connect.createStatement();
				String qr="insert  into employees (username, password, first_name, last_name, email_id, phone_no, city, state, zip, work_assigned) values ('"+username_val+"', '"+password_val+"', '"+first_name_val+"', '"+last_name_val+"', '"+email_val+"', '"+phone_val+"', '"+city_val+"', '"+state_val+"', '"+zip_code_val+"', '"+assigned_work_val+"')";
				int a=st.executeUpdate(qr);
				if(a>0){
					JOptionPane.showMessageDialog(master,"Employee Successfully Entered");
					setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(master,"There is some issue entering data. Please try again later.");
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(master,"There is some issue entering data. Please try again later.");
				System.out.println("Exception is :"+e);
			}
			}
		}
		 
		if(g.getSource()==reset){
			username_txt.setText("");
			password_txt.setText("");
			first_name_txt.setText("");
			last_name_txt.setText("");
			email_txt.setText("");
			phone_txt.setText("");
			city_select.setSelectedItem("=Select City=");
			state_select.setSelectedItem("=Select State=");
			email_txt.setText("");
			zip_code_txt.setText("");
			
		}
	}
	
	RegisterMenu(){
		setTitle("Employee Registration form :");
		setSize(500,500);
		username=new JLabel("Username :");
		password = new JLabel("Password :");
		first_name = new JLabel("First Name :");
		last_name = new JLabel("Last Name :");
		email = new JLabel("Email :");
		phone_no = new JLabel("Phone No :");
		city = new JLabel("City :");
		state = new JLabel("State :");
		zip_code =  new JLabel("Zip Code :");
		assign_work=new JLabel("Assign Work :");
		
		username_txt = new JTextField(20);
		password_txt = new JPasswordField(20);
		first_name_txt = new JTextField(20);
		last_name_txt = new JTextField(20);
		email_txt = new JTextField(20);
		phone_txt = new JTextField(20);
		zip_code_txt = new JTextField(20);
		
		city_select = new JComboBox<String>();
		city_select.addItem("=Select City=");
		city_select.addItem("Toronto");
		city_select.addItem("Scarborough");
		city_select.addItem("Markham");
	
		
		state_select = new JComboBox<String>();
		state_select.addItem("=Select State=");
		state_select.addItem("Ontatio");
		state_select.addItem("Britsh Colombia");
		
		select_work=new JComboBox<String>();
		select_work.addItem("=Select Work=");
		select_work.addItem("Challan Collector");
		select_work.addItem("Water Bill Collector");
		select_work.addItem("Electricity Bill Collector");
		
		register = new JButton("Register");
		reset = new JButton("Reset");
		
		panel = new JPanel(new GridLayout(15,10,20,10));
		panel.setBackground(Color.cyan);
		panel.add(username);
		panel.add(username_txt);
		
		panel.add(password);
		panel.add(password_txt);
		
		panel.add(first_name);
		panel.add(first_name_txt);
		
		panel.add(last_name);
		panel.add(last_name_txt);
		
		panel.add(email);
		panel.add(email_txt);
		
		panel.add(phone_no);
		panel.add(phone_txt);
		
		panel.add(city);
		panel.add(city_select);
		
		panel.add(state);
		panel.add(state_select);
		
		panel.add(zip_code);
		panel.add(zip_code_txt);
		
		panel.add(assign_work);
		panel.add(select_work);
		
		panel.add(register);
			register.addActionListener(this);
		panel.add(reset);
			reset.addActionListener(this);
		
		master=new JPanel();
			master.setBackground(Color.blue);
			master.add(panel);
		add(master);
		setVisible(true);
	}
}
