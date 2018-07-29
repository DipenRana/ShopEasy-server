import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import SEDatabase.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class UInterface extends JFrame implements ActionListener {

	JMenuBar bar;
	JMenu dbedit,servermngt,records;
	
	ImageIcon icon = new ImageIcon("F:\\workspace\\ShopEasy server\\app_icon.png"); 
	JLabel thumb = new JLabel();
	JList lst;
	
	static JTable table;
	
	JFrame main,frame1;
	JMenuItem add, delete, update,server,stockupdate,displaytab;
	JPanel add_panel, delete_panel, update_panel, server_panel,logo,stock_panel,display_panel;
	JLabel l1 = new JLabel("Enter item barcode");
	JLabel l2 = new JLabel("Enter item name");
	JLabel l3 = new JLabel("Enter item Price");
	JLabel l4 = new JLabel("Enter item barcode to be deleted");
	JLabel l5 = new JLabel("Enter item barcode to updated");
	JLabel l6 = new JLabel("Enter item new Item Price");
	JLabel l7 = new JLabel("");
	JLabel l8 = new JLabel("");
	JLabel l9 = new JLabel("");
	JLabel l10 = new JLabel("");
	JLabel l11 = new JLabel("Enter item ID");
	JLabel l12 = new JLabel("Enter item brand");
	JLabel l13 = new JLabel("");
	JLabel lserver = new JLabel("Press start Button to start the server");
	JLabel ladd = new JLabel("Enter details of item to be added");
	JLabel ldel = new JLabel("Enter details of item to be Deleted");
	JLabel lupd = new JLabel("Enter details of item to be updated");
	JLabel lsto = new JLabel("Enter details of Stock to be updated");
	JLabel lstock1 = new JLabel("Enter item ID");
	JLabel lstock2 = new JLabel("Enter Stock added");
	JLabel lstock3 = new JLabel("Enter minimum stock limit");
	JLabel ldisp = new JLabel("Select table to be displayed");
	JTextField tf1, tf2, tf3, tf4, tf5, tf6,tf7,tf8,tf_stock1,tf_stock2,tf_stock3;
	JButton b1, b2, b3,server_button,stock_b,b_disp;
	final static String ADDPANEL = "add";
	final static String DELETEPANEL = "delete";
	final static String UPDATEPANEL = "update";
	final static String DISPLAYPANEL = "display";
	final static String SERVERMenu = "server";
	final static String LOGO = "logo";
	final static String Stockupdate = "stocks";

	public UInterface() {
		setSize(600, 600);
		setTitle("Shop Easy");
		setLayout(new CardLayout());
		bar = new JMenuBar();
		dbedit = new JMenu("Edit Database");
		servermngt = new JMenu("Server management");
		records = new JMenu("Records");
		
		add = new JMenuItem("Add new item");
		delete = new JMenuItem("Delete item");
		update = new JMenuItem("Update item");
		server = new JMenuItem("Server");
		stockupdate = new JMenuItem("Stock update");
		displaytab=new JMenuItem("Display tables");
		tf1 = new JTextField(12);
		tf2 = new JTextField(12);
		tf3 = new JTextField(12);
		tf4 = new JTextField(12);
		tf5 = new JTextField(12);
		tf6 = new JTextField(12);
		tf7 = new JTextField(12);
		tf8 = new JTextField(12);
		tf_stock1=new JTextField(12);
		tf_stock2=new JTextField(12);
		tf_stock3=new JTextField(12);
		
		String list[]={"Items","Stocks","Customer","Bill"};
		
		lst=new JList(list);
		b1 = new JButton("Add Item");
		b2 = new JButton("Delete Item");
		b3 = new JButton("Update Price");
		b_disp =new JButton("Show table");
		stock_b= new JButton("Update Stock");
		server_button=new JButton("Start");
		dbedit.add(add);
		dbedit.add(delete);
		dbedit.add(update);
		dbedit.add(stockupdate);
		servermngt.add(server);
		records.add(displaytab);
		bar.add(dbedit);
		bar.add(servermngt);
		bar.add(records);
		setJMenuBar(bar);

		thumb.setIcon(icon);

		add_panel = new JPanel(null);
		delete_panel = new JPanel(null);
		update_panel = new JPanel(null);
		server_panel = new JPanel(null);
		stock_panel =new JPanel(null);
		display_panel=new JPanel(null);
		logo=new JPanel(null);
		
		logo.add(thumb);
		thumb.setBounds(50,60, 400, 400);
		
		int x=80,y=120;
		
		display_panel.add(ldisp);
		ldisp.setBounds(x+20, y-50, 400, 25);
		display_panel.add(lst);
		lst.setBounds(x+50, y, 150, 100);
		display_panel.add(b_disp);
		b_disp.setBounds(x+250, y, 150, 20);
		
		server_panel.add(lserver);
		lserver.setBounds(x,y, 450, 20);
		server_panel.add(server_button);
		server_button.setBounds(x+50,y+50, 150, 20);
		server_panel.add(l10);
		l10.setBounds(x+50,y+80, 150, 20);

		ladd.setFont (ladd.getFont ().deriveFont (20.0f));
		ldel.setFont (ldel.getFont ().deriveFont (20.0f));
		lupd.setFont (lupd.getFont ().deriveFont (20.0f));
		lsto.setFont (lupd.getFont ().deriveFont (20.0f));
		lserver.setFont (lupd.getFont ().deriveFont (20.0f));
		ldisp.setFont (lupd.getFont ().deriveFont (20.0f));
		
		stock_panel.add(lsto);
		lsto.setBounds(x+20, y-50, 400, 25);
		stock_panel.add(lstock1);
		lstock1.setBounds(x+50, y, 150, 20);
		stock_panel.add(tf_stock1);
		tf_stock1.setBounds(x+220, y, 150, 20);
		stock_panel.add(lstock2);
		lstock2.setBounds(x+50,y+30, 150, 20);
		stock_panel.add(tf_stock2);
		tf_stock2.setBounds(x+220, y+30, 150, 20);
		stock_panel.add(lstock3);
		lstock3.setBounds(x+50, y+60, 150, 20);
		stock_panel.add(tf_stock3);
		tf_stock3.setBounds(x+220, y+60, 150, 20);
		stock_panel.add(stock_b);
		stock_b.setBounds(x+150, y+90, 150, 20);
		stock_panel.add(l13);
		l13.setBounds(x+150, y+120, 150, 20);
		
		add_panel.add(ladd);
		ladd.setBounds(x+20, y-50, 400, 25);
		add_panel.add(l11);
		l11.setBounds(x+50, y, 150, 20);
		add_panel.add(tf7);
		tf7.setBounds(x+220, y, 150, 20);
		add_panel.add(l1);
		l1.setBounds(x+50,y+30, 150, 20);
		add_panel.add(tf1);
		tf1.setBounds(x+220, y+30, 150, 20);
		add_panel.add(l2);
		l2.setBounds(x+50, y+60, 150, 20);
		add_panel.add(tf2);
		tf2.setBounds(x+220, y+60, 150, 20);
		add_panel.add(l12);
		l12.setBounds(x+50, y+90, 150, 20);
		add_panel.add(tf8);
		tf8.setBounds(x+220, y+90, 150, 20);
		add_panel.add(l3);
		l3.setBounds(x+50, y+120, 150, 20);
		add_panel.add(tf3);
		tf3.setBounds(x+220, y+120, 150, 20);
		add_panel.add(b1);
		b1.setBounds(x+120, y+180, 100, 20);
		add_panel.add(l7);
		l7.setBounds(x+140, y+210, 150, 50);

		delete_panel.add(ldel);
		ldel.setBounds(x+20, y-30, 400, 25);
		delete_panel.add(l4);
		l4.setBounds(x+50, y+30, 200, 20);
		delete_panel.add(tf4);
		tf4.setBounds(x+280, y+30, 150, 20);
		delete_panel.add(b2);
		b2.setBounds(x+120, y+70, 100, 20);
		delete_panel.add(l8);
		l8.setBounds(x+140, y+90, 150, 50);

		update_panel.add(lupd);
		lupd.setBounds(x+20, y-30, 400, 25);
		update_panel.add(l5);
		l5.setBounds(x+50,y+30, 200, 20);
		update_panel.add(tf5);
		tf5.setBounds(x+280, y+30, 150, 20);
		update_panel.add(l6);
		l6.setBounds(x+50, y+60, 200, 20);
		update_panel.add(tf6);
		tf6.setBounds(x+280, y+60, 150, 20);
		update_panel.add(b3);
		b3.setBounds(x+120, y+100, 150, 20);
		update_panel.add(l9);
		l9.setBounds(x+140, y+130, 150, 50);
		
		add(logo,LOGO);
		add(add_panel,ADDPANEL);
		add(delete_panel,DELETEPANEL);
		add(update_panel,UPDATEPANEL);
		add(server_panel,SERVERMenu);
		add(stock_panel,Stockupdate);
		add(display_panel,DISPLAYPANEL);

		setResizable(false);

		add.addActionListener(this);
		delete.addActionListener(this);
		update.addActionListener(this);
		stockupdate.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b_disp.addActionListener(this);
		stock_b.addActionListener(this);
		displaytab.addActionListener(this);
		server.addActionListener(this);
		server_button.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		String str;
	
		CardLayout cd= (CardLayout)getContentPane().getLayout();
		str = ae.getActionCommand();
		if (str.equals("Add new item")) {

			
			cd.show(getContentPane(), ADDPANEL);
			
		} else if (str.equals("Add Item")) {
            
			String id=tf7.getText().trim();
			String barcode = tf1.getText().trim();
			String name = tf2.getText().trim();
			String brand = tf8.getText().trim();
			String pr = tf3.getText().trim();
			AddDBItem(id,barcode, name,brand , pr);
			System.out.println("row added");
			
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			tf7.setText("");
			tf8.setText("");
		
		}

		else if (str.equals("Delete item")) {
		
			cd.show(getContentPane(), DELETEPANEL);
			
		} else if (str.equals("Delete Item")) {
			String bcode = tf4.getText().trim();
			DeleteItem(bcode);
			System.out.println(" row deleted");
			l8.setText("Item Deleted");
			tf4.setText("");
			
		}

		else if (str.equals("Update item")) {
		
			cd.show(getContentPane(), UPDATEPANEL);
		
		} else if (str.equals("Update Price")) {
			String bcode = tf5.getText().trim();
			String pr = tf6.getText().trim();
			UpdateItem(bcode, pr);
			System.out.println("row updated");
			l9.setText("Item price updated");
			tf5.setText("");
			tf6.setText("");
			
		}
		else if (str.equals("Stock update")) {
			
			cd.show(getContentPane(), Stockupdate);
		} 
		else if (str.equals("Update Stock")) {
			int id=0,stock=0,min=0;
			try {
			id= Integer.parseInt(tf_stock1.getText().trim());
			stock= Integer.parseInt(tf_stock2.getText().trim());
			min=Integer.parseInt(tf_stock3.getText().trim());
			} catch (NumberFormatException ne) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Please enter valid value", "Dialog",
						JOptionPane.ERROR_MESSAGE);
				tf_stock1.setText("");
				tf_stock2.setText("");
				tf_stock3.setText("");
				setVisible(true);
			}
			StockUpdate(id,stock,min);
			tf_stock1.setText("");
			tf_stock2.setText("");
			tf_stock3.setText("");
		} 
		
		
		else if(str.equals("Server")){
			cd.show(getContentPane(),SERVERMenu);
			
		}
		else if(str.equals("Start")){
				SwingWorker<Void,String> myWorker = new Worker();
				myWorker.execute();
			l10.setText("Status: Online");
		}
		
		else if(str.equals("Display tables")){
			cd.show(getContentPane(),DISPLAYPANEL);
			
		}
		else if(str.equals("Show table")){
			 showTableData();
		}

	}

	private void StockUpdate(int id, int stock, int min) {
		
			
			java.sql.Connection dbcon = null;

			try {
				 Statement stmt = null;
				Class.forName("com.mysql.jdbc.Driver");
				dbcon = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/shopeasy",
								"root", "tiger");
				String sql = "INSERT INTO Stocks (id,available,min_limit)"
						+ "VALUES (?,?,?);";
				String sql1 = "Update Stocks set min_limit="+min+",available=available+"+
						stock +" Where id="+id+";";
				stmt = dbcon.createStatement();
				int i=stmt.executeUpdate(sql1);
				System.out.println(i+"");
					 PreparedStatement ps = dbcon.prepareStatement(sql);
				
				ps.setInt(1, id);
				ps.setInt(2,stock);
				ps.setInt(3, min);
				 if(i!=1){
				 ps.executeUpdate();
				 }
				l13.setText("Stock Updated");
				dbcon.close();
			} catch (SQLException e) {
				e.printStackTrace();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private void UpdateItem(String bcode, String pr) {
		// TODO Auto-generated method stub
		try {
			float price = Float.parseFloat(pr);
			java.sql.Connection dbcon = null;

			try {

				Class.forName("com.mysql.jdbc.Driver");
				dbcon = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/shopeasy",
								"root", "tiger");
				String sql = "UPDATE Items set price=? WHERE barno = ?;";
				PreparedStatement ps = dbcon.prepareStatement(sql);
				ps.setFloat(1, price);
				ps.setString(2, bcode);

				ps.executeUpdate();
				dbcon.close();

			} catch (SQLException e) {
				e.printStackTrace();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (NumberFormatException ne) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Please enter valid price value", "Dialog",
					JOptionPane.ERROR_MESSAGE);
			tf3.setText("");
			setVisible(true);
		}
	}

	private void AddDBItem(String id,String barcode, String name, String brand, String pr) {
		// TODO Auto-generated method stub
		try {
			float price = 0;
			int I_id=0;
			I_id= Integer.parseInt(id);
			price = Float.parseFloat(pr);
			
			java.sql.Connection dbcon = null;

			try {

				Class.forName("com.mysql.jdbc.Driver");
				dbcon = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/shopeasy",
								"root", "tiger");
				String sql = "INSERT INTO Items (id,barno, name, brand , price)"
						+ "VALUES (?,?,?, ?, ?)";
				PreparedStatement ps = dbcon.prepareStatement(sql);
				ps.setInt(1, I_id);
				ps.setString(2, barcode);
				ps.setString(3, name);
				ps.setString(4,brand);
				ps.setFloat(5, price);
				int i = ps.executeUpdate();
				l7.setText("Item Added");
				dbcon.close();
			} catch (SQLException e) {
				e.printStackTrace();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NumberFormatException ne) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Please enter valid price value", "Dialog",
					JOptionPane.ERROR_MESSAGE);
			tf3.setText("");
			setVisible(true);
		}
	}

	private void DeleteItem(String bcode) {
		// TODO Auto-generated method stub
		java.sql.Connection dbcon = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			dbcon = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/shopeasy", "root", "tiger");
			String sql = "DELETE FROM Items WHERE barno = ?";
			PreparedStatement ps = dbcon.prepareStatement(sql);
			ps.setString(1, bcode);

			int i = ps.executeUpdate();
			dbcon.close();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				UInterface ui = new UInterface();
			}
		});
	}
	
	class Worker extends SwingWorker<Void, String> {
	   

	    @Override
	    protected Void doInBackground() throws Exception {
	    	Start.main(null);
	        return null;
	    }

	       
	}
	
	  public void showTableData() {
		  
		  String[] columnItems = {"ID", "Barcode", "Name", "Brand","Price"};
		  String[] columnStocks = {"ID", "Available", "Min. Limit"};
		  String[] columnCustomer = {"ID", "Name", "Address", "Contact","BillNo"};
		  String[] columnBill = {"Bill No.", "Date", "PaymentID","Amount"};
		  String from=null;
	        frame1 = new JFrame("Database Search Result");
	       
	     
	        frame1.setLayout(new BorderLayout());
	
	        from = (String) lst.getSelectedValue();
	        
	        DefaultTableModel model = new DefaultTableModel();
	        if(from.equals("Items")){
	        model.setColumnIdentifiers(columnItems);
	        }else if(from.equals("Stocks")){
		        model.setColumnIdentifiers(columnStocks);
		        }else if(from.equals("Customer")){
			        model.setColumnIdentifiers(columnCustomer);
		        }else if(from.equals("Bill")){
			        model.setColumnIdentifiers(columnBill);
		        }
	
	        table = new JTable();
	        table.setModel(model);
	        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	        table.setFillsViewportHeight(true);
	        JScrollPane scroll = new JScrollPane(table);
	        scroll.setHorizontalScrollBarPolicy(
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scroll.setVerticalScrollBarPolicy(
	                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        
	
	        String a = "";
	        String b = "";
	        String c = "";
	        String d = "";
	        String e = "";
	 
	        java.sql.Connection dbcon = null;

			try {

				Class.forName("com.mysql.jdbc.Driver");
				dbcon = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/shopeasy",
								"root", "tiger");
			if(from.equals("Items")){
				String sql = "Select * from Items;";
				PreparedStatement ps = dbcon.prepareStatement(sql);	
				 ResultSet rs = ps.executeQuery();
				 int i = 0;
	            while(rs.next()) {
	                a = rs.getString("id");
	                b = rs.getString("barno");
	                c = rs.getString("name");
	                d = rs.getString("brand");
	                e = rs.getString("price");
	                model.addRow(new Object[]{a,b,c,d,e});
	                i++;
	            }
	            if (i < 1) {
	                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	          }
			else if(from.equals("Stocks")){
				String sql = "Select * from Stocks;";
				PreparedStatement ps = dbcon.prepareStatement(sql);	
				 ResultSet rs = ps.executeQuery();
				 int i = 0;
	            while(rs.next()) {
	                a = rs.getString("id");
	                b = rs.getString("available");
	                c = rs.getString("min_limit");
	               
	                model.addRow(new Object[]{a,b,c});
	                i++;
	            }
	            if (i < 1) {
	                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	          }
			else if(from.equals("Customer")){
				String sql = "Select * from Customer;";
				PreparedStatement ps = dbcon.prepareStatement(sql);	
				 ResultSet rs = ps.executeQuery();
				 int i = 0;
	            while(rs.next()) {
	                a = rs.getString("id");
	                b = rs.getString("name");
	                c = rs.getString("address");
	                d = rs.getString("contact");
	                e = rs.getString("bill_no");
	                model.addRow(new Object[]{a,b,c,d,e});
	                i++;
	            }
	            if (i < 1) {
	                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	          }
			else if(from.equals("Bill")){
				String sql = "Select * from Bill;";
				PreparedStatement ps = dbcon.prepareStatement(sql);	
				 ResultSet rs = ps.executeQuery();
				 int i = 0;
	            while(rs.next()) {
	                a = rs.getString("bill_no");
	                b = rs.getString("date");
	                c = rs.getString("payment_id");
	                d = rs.getString("bill_amt");
	               
	                model.addRow(new Object[]{a,b,c,d});
	                i++;
	            }
	            if (i < 1) {
	                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	          }
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	        frame1.add(scroll);
	        frame1.setVisible(true);
	        frame1.setSize(600, 500);
	    }

}
