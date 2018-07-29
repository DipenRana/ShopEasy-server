package SEDatabase;

import java.io.Serializable;

public class DBDetails implements Serializable {

	public String msg=null;
	public String bcodedetail=null;
	public String bill_no=null;
	public String date=null;

	public DBDetails(String msg) {
		this.msg = msg;
	}

}
