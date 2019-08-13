import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class CustomerMenu extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar mbar;
	JMenu op,pro,log;
	JMenuItem chalan,water,elec,ed,change,sign;
	String name,id;
	JLabel type;
	JTextField txttype;
	JPanel master,panl;
	String etype,wt;
	public void actionPerformed(ActionEvent gm){
		if(gm.getSource()==change){
			//ChangePassword ob=new ChangePassword(name,id,"employee");
		}
		if(gm.getSource()==sign){
			new Login();
			setVisible(false);
		}
		if(gm.getSource()==ed){
				//new Edit_emp(name,id);
		}
		if(gm.getSource()==chalan){
			new Chalan();
		}
		if(gm.getSource()==water){
			new WaterBill();
		}
		if(gm.getSource()==elec){
			new ElectricityBill();
		}
	}
	CustomerMenu(String username,String cust_id){
		//etype=stype;
		setTitle("Employee Menu bar");
		setSize(500,500);
		name=username;
		id=cust_id;
		mbar=new JMenuBar();
		
		log=new JMenu("LOGOUT");
		
		chalan=new JMenuItem("CHALAN");
			chalan.addActionListener(this);
		water=new JMenuItem("WATER");
			water.addActionListener(this);
		elec=new JMenuItem("ELECTRICITY");
			elec.addActionListener(this);
			ed=new JMenuItem("EDIT");
		ed.addActionListener(this);
		change=new JMenuItem("CHANGE PASSWORD");
			change.addActionListener(this);
		sign=new JMenuItem("SIGN OUT");
			sign.addActionListener(this);
		wt="";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:///billdesk","root","Mysql1234!");
			Statement st=con.createStatement();
			String qr="select * from employees where id='"+id+"'";
			ResultSet rs=st.executeQuery(qr);
			if(rs.next()){
				switch((String) rs.getString("work_assigned")) {
				case "1": 
					op.add(chalan);
					break;
				case "2":
					op.add(water);
					break;
				case "3":
					op.add(elec);
					break;
				default :
				}
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
		
		log.add(sign);
		
		mbar.add(op);
		mbar.add(log);
		
		setJMenuBar(mbar);
		type=new JLabel("Work Type");
		txttype=new JTextField(10);
		
		panl=new JPanel();
			panl.setBackground(Color.cyan);
			panl.add(type);
			panl.add(txttype);
		master=new JPanel();
			master.setBackground(Color.blue);
			master.add(panl);
			add(master);
		setVisible(true);
	}
}