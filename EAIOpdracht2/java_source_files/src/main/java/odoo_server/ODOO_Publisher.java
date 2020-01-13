package main.java.odoo_server;
import main.java.odoo_api.ODOO_API;
import javax.xml.ws.Endpoint;


public class ODOO_Publisher {
	public static void main(String[] args) {
		try {
			//String localHost = InetAddress.getLocalHost().getHostAddress();
			String localHost = "localhost";
			Endpoint.publish("http://"+ localHost + ":8888/ODOOServices", new ODOO_API());
			System.out.println("now serving ODOO at http://" + localHost + ":8888/ODOOServices");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
