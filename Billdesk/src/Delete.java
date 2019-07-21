import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class Delete extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel uname,name;
	JTextField txt,txt1;
	JButton search,delete,reset;
	JPanel panl,master,pnl;
	String suname,sname;
	
	public void actionPerformed(ActionEvent gm){
		if(gm.getSource()==search){
			suname=txt.getText();
			if(suname.equals("")){
				JOptionPane.showMessageDialog(master,"User_name field is require:");
			}else{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql:///project","root","");
					Statement state=con.createStatement();
					String q="select * from employee where username='"+suname+"'";
					ResultSet rs=state.executeQuery(q);
					if(rs.next()){
						txt1.setText(rs.getString("name"));
					}
					else{
						JOptionPane.showMessageDialog(null,"NO RECORD FOUND");
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
			
		}
		if(gm.getSource()==delete){
			suname=txt.getText();
			if(suname.equals("")){
				JOptionPane.showMessageDialog(null,"User name is require:");
				
			}else{
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql:///project","root","");
					Statement st=cn.createStatement();
					String qr="delete from employee where username='"+suname+"'";
					int g=st.executeUpdate(qr);
					if(g>0){
						JOptionPane.showMessageDialog(null,g+" row delete");
					}
					else{
						JOptionPane.showMessageDialog(null,"NO row delete");
					}
				}
			catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
		}
	}
		if(gm.getSource()==reset){
			txt.setText("");
			txt1.setText("");}

	}
	public static void main(String arg[]){
		new Delete();
	}
	Delete(){
		uname=new JLabel("User Name :");
		name=new JLabel("Name :");
		
		
		txt=new JTextField(15);
		txt1=new JTextField(10);
		txt1.setEditable(false);
		search=new JButton("SEARCH");
		delete=new JButton("DELETE");
		reset=new JButton("RESET");
		pnl=new JPanel();
			pnl.add(txt);
			pnl.add(search);
			search.addActionListener(this);
		panl=new JPanel(new GridLayout(3,2,5,5));
			setTitle("This is search Form :");
			setSize(800,500);
			panl.add(uname);
			panl.add(pnl);
			
			panl.add(name);
			panl.add(txt1);
			
			panl.add(delete);
				delete.addActionListener(this);
			panl.add(reset);
				reset.addActionListener(this);
			master=new JPanel();
				master.setBackground(Color.cyan);
				master.add(panl);
			add(master);
			setVisible(true);
	}
}