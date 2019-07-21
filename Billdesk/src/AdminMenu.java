import java.awt.event.*;
import javax.swing.*;
class AdminMenu extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username,employee_id;
	JMenuBar menubar;
	JMenu operations, profile, logout;
	JMenuItem register, list_emp, edit, delete, change_password, sign_in;
	
	public void actionPerformed(ActionEvent m){
		if(m.getSource()==sign_in){
			new Login();
			setVisible(false);
		}
		if(m.getSource()==change_password){
			new ChangePassword(username, employee_id, "admin");
		}
		if(m.getSource()==register){
			new RegisterMenu();
		}
		if(m.getSource()==delete){
			new Delete();
		}
		if(m.getSource()==edit){
			new Edit();
		}
	}
	AdminMenu(String user_name,String user_id){
		username= user_name;
		employee_id= user_id;
		setTitle("Menu Bar");
		setSize(500,500);
		menubar = new JMenuBar();
		
		operations = new JMenu("Operation");
		profile = new JMenu("Profile");
		logout = new JMenu("Logout");
		
		register = new JMenuItem("Registration");
		register.addActionListener(this);
		list_emp = new JMenuItem("List of employe");
		edit = new JMenuItem("Edit Task");
		edit.addActionListener(this);
		delete = new JMenuItem("Delete");
		delete.addActionListener(this);
		change_password = new JMenuItem("Change Password");
		
		change_password.addActionListener(this);
		sign_in = new JMenuItem("SIGN OUT");
		sign_in.addActionListener(this);
		operations.add(register);
		operations.add(list_emp);
		operations.add(edit);
		operations.add(delete);
		
		profile.add(change_password);
		
		logout.add(sign_in);
			
		menubar.add(operations);
		menubar.add(profile);
		menubar.add(logout);
		
		setJMenuBar(menubar);
		setVisible(true);
		
	}
}