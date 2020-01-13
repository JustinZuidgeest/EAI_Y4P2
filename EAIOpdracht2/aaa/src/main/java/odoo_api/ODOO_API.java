package main.java.odoo_api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class ODOO_API {

	private static String url;
	private static String db;
	private static String username;
	private static String password;

	@WebMethod
	public boolean setConnection(@WebParam(name = "url") String url, @WebParam(name = "database") String database,
			@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
		ODOO_API.url = url;
		ODOO_API.db = database;
		ODOO_API.username = username;
		ODOO_API.password = password;
		boolean connectionSet = true;
		return connectionSet;
	}

	@WebMethod
	public int createSalesOrder(@WebParam(name = "salesorderObject") SalesOrderObject salesOrder) {
		int order_id = -1;
		try {
			if (salesOrder.partnerID == 12345) order_id = 33333;
			if (salesOrder.partnerID == 67890) order_id = 44444;
		} catch (Exception e) {
			System.out.println("Error while reading data from server:\n\n" + e.getMessage());
			e.printStackTrace();
		} finally {
			return order_id;
		}
	}

	@WebMethod
	public int createPartner(@WebParam(name = "partner") PartnerObject partner) {
		int partner_id = -1;
		try {

		} catch (Exception e) {
			System.out.println("Error while reading data from server:\n\n" + e.getMessage());
			e.printStackTrace();
		} finally {
			return partner_id;
		}
	}

	@WebMethod
	public int createSalesOrderLine(@WebParam(name = "salesorderlineObject") SalesOrderLineObject salesorderline) {
		int orderLine_id = -1;
		try {
			if (salesorderline.orderID == 33333 && salesorderline.productID == 11111) orderLine_id = 55555;
			if (salesorderline.orderID == 33333 && salesorderline.productID == 22222) orderLine_id = 66666;
			if (salesorderline.orderID == 44444 && salesorderline.productID == 11111) orderLine_id = 77777;
			if (salesorderline.orderID == 44444 && salesorderline.productID == 22222) orderLine_id = 88888;
		} catch (Exception e) {
			System.out.println("Error while reading data from server:\n\n" + e.getMessage());
			e.printStackTrace();
		} finally {
			return orderLine_id;
		}
	}

	@WebMethod
	public int getProductID(@WebParam(name = "productNaam") String productNaam) {
		int product_id = -1;
		try {
			if (productNaam.equals("Schroef")) product_id = 11111;
			if (productNaam.equals("Moer")) product_id = 22222;
		} catch (Exception e) {
			System.out.println("Error while reading data from server:\n\n" + e.getMessage());
			e.printStackTrace();
		} finally {
			return product_id;
		}
	}

	@WebMethod
	public int getPartnerID(@WebParam(name = "partnerNaam") String partnerNaam) {
		int partner_id = -1;
		try {
			if (partnerNaam.equals("Henk")) partner_id = 12345;
			if (partnerNaam.equals("Piet")) partner_id = 67890;
		} catch (Exception e) {
			System.out.println("Error while reading data from server:\n\n" + e.getMessage());
			e.printStackTrace();
		} finally {
			return partner_id;
		}
	}
}
