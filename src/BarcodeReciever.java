import java.io.EOFException;

import SEDatabase.DBDetails;
import SEDatabase.InsertIntoBill;
import SEDatabase.InsertIntocustomer;
import SEDatabase.retbillno;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.shopeasy.CustomerDetails;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Line;

import SEDatabase.DBConnection;

public class BarcodeReciever {

	private ConnectionSE con;
	Integer billno;
	
	ArrayList<ItemDetails> details = new ArrayList<ItemDetails>();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();

	public BarcodeReciever(ConnectionSE server) {
		this.con = server;
	}

	public void start() throws IOException, EOFException {
		
		
		while (true) {
			String name;
			try {
				ObjectInputStream ois = new ObjectInputStream(con.getInputStream());

				CustomerDetails bcode = (CustomerDetails) ois.readObject();
				
				if (bcode.msg.equals("barcode")) {
					System.out.println(bcode.barcode);
					String arr[] = bcode.barcode.split("@");
					String qty = arr[1];
					
					//Retrieving data from DB
					name = DBConnection.main(arr[0]);
					
					DBDetails db;
					System.out.println(name);

					if (name =="Not Found") {
						db=new DBDetails("wrong");
						BarcodeSender bs = new BarcodeSender(con);
						bs.send(db);
					} else {
						String DBarr[] = name.split("@");
						details.add(new ItemDetails(arr[0], DBarr[0], DBarr[1],
								arr[1]));
						db=new DBDetails("right");
						db.bcodedetail=name;
						BarcodeSender bs = new BarcodeSender(con);
						bs.send(db);
					}
				}else if(bcode.msg.equals("addrdetail")){
					System.out.println(bcode.Name);
				    billno = retbillno.main();
				    billno++;
					System.out.println(dateFormat.format(date));
					
					String curdate=dateFormat.format(date);
					 Document document = new Document();
				      try
				      {
				         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Bill"+billno+".pdf"));
				         document.open();
				         document.add(new Paragraph("ShopEasy Invoice Deatils."));
				         document.add(new Paragraph("\n\nBill NO:#"+billno));
				         document.add(new Paragraph("Date:" +curdate));
				         document.add(new Paragraph("Name:" + bcode.Name));
				         document.add(new Paragraph("Address:\n" + bcode.Address1+"\n"+bcode.Address2+"\n"+bcode.Address3+" Pin:"+bcode.Pincode));
				         document.add(new Paragraph("Contact:" + bcode.Contact));
				        
				         document.add(new Paragraph("\nList of Items"));
				         int size= details.size();
				         float total= 0;
				         ItemDetails s1=null;
				         for(int i=0;i<size;i++)
				         {
				        	 s1=details.get(i);
				        	 int quan=Integer.parseInt(s1.qty);
				        	 float prc=Float.parseFloat(s1.price);
				        	 prc=prc*quan;
				        	  total+=Float.parseFloat(s1.qty)*Float.parseFloat(s1.price);
				         document.add(new Paragraph((i+1)+"." +s1.name+"   "+s1.qty+"   "+prc));
				         }
				         total+=50;
				         document.add(new Paragraph("\nShipping charges: Rs.50"));
				         document.add(new Paragraph("\nTotal: Rs." +total));
				         document.add(new Paragraph("\n\n*************THANK YOU*****************"));
				        		 document.close();
				         writer.close();
				        
				         String addr=bcode.Address1+","+bcode.Address2+","+bcode.Address3+".";
				        
							new InsertIntoBill(curdate,152032,total);
							
							 DBDetails db;
							 db=new DBDetails("bill");
								db.bill_no=billno+"";
								db.date=curdate;
								BarcodeSender bs = new BarcodeSender(con);
								bs.send(db);
				           
							new InsertIntocustomer(bcode.Name,addr,bcode.Contact,billno);
				      } catch (DocumentException e)
				      {
				         e.printStackTrace();
				      } catch (FileNotFoundException e)
				      {
				         e.printStackTrace();
				      }
				}
				else if(bcode.msg.equals("home")){
					Document document = new Document();
					 billno = retbillno.main();
					    billno++;
					System.out.println(dateFormat.format(date));
					String curdate=dateFormat.format(date);
				      try
				      {
				         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Bill"+billno+".pdf"));
				         document.open();
				         document.add(new Paragraph("ShopEasy Invoice Deatils."));
				         document.add(new Paragraph("\n\nBill NO:#"+billno));
				         document.add(new Paragraph("\nDate:"+curdate));
				         document.add(new Paragraph("\nList of Items"));
				         int size= details.size();
				         float total= 0;
				         ItemDetails s1=null;
				         for(int i=0;i<size;i++)
				         {
				        	 s1=details.get(i);
				        	 int quan=Integer.parseInt(s1.qty);
				        	 float prc=Float.parseFloat(s1.price);
				        	 prc=prc*quan;
				        	  total+=Float.parseFloat(s1.qty)*Float.parseFloat(s1.price);
				         document.add(new Paragraph("\n"+(i+1)+"." +s1.name+"   "+s1.qty+"   "+prc));
				         }
				         document.add(new Paragraph("\nTotal: Rs." +total));
				         document.add(new Paragraph("\n\n*************THANK YOU*****************"));
				        		 document.close();
				         writer.close();
				         
				         DBDetails db;
				         
				         db=new DBDetails("bill");
							db.bill_no=billno+"";
							db.date=curdate;
							BarcodeSender bs = new BarcodeSender(con);
							bs.send(db);
							
							new InsertIntoBill(curdate,152032,total);
				         
				      } catch (DocumentException e)
				      {
				         e.printStackTrace();
				      } catch (FileNotFoundException e)
				      {
				         e.printStackTrace();
				      }
				}
				else if(bcode.msg.equals("remove")){
					
					 int index = 0,size= details.size();
					 String name1=bcode.Name,qty=bcode.qty;
					 
			         ItemDetails s1=null;
			         for(int i=0;i<size;i++)
			         {
			        	 s1=details.get(i);
			        	 if(s1.name.equals(name1) && s1.qty.equals(qty))
			        	 {
			        		 index=i;
			        		 break;
			        	 }
			         }
					
					details.remove(index);
					System.out.println("Removd:"+name1);
				}
				
				else if(bcode.msg.equals("edit")){
					
					 int index = 0,size= details.size();
					 String name1=bcode.Name,qty=bcode.qty,newqty=bcode.Nqty;
					 
			         ItemDetails s1=null;
			         for(int i=0;i<size;i++)
			         {
			        	 s1=details.get(i);
			        	 if(s1.name.equals(name1) && s1.qty.equals(qty))
			        	 {
			        		s1.qty=newqty;
			        		details.set(i, s1);
			        		index=1;
			        	 }
			         }
					
					if(index==1){
					System.out.println("Qty changed:"+name1);
					}
				}


			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}

}
