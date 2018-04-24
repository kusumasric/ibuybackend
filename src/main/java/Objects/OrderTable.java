package Objects;

public class OrderTable {

	int orderid;	
	int Payment_transactionId;
	int CustomersTable_id;
	int amount;
	byte[] qrcodeImg;
	boolean qrcodePrinted;
	
	
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getPaymentTable_transactionId() {
		return Payment_transactionId;
	}
	public void setPaymentTable_transactionId(int paymentTable_transactionId) {
		Payment_transactionId = paymentTable_transactionId;
	}
	public int getCustomersTable_id() {
		return CustomersTable_id;
	}
	public void setCustomersTable_id(int customersTable_id) {
		CustomersTable_id = customersTable_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public byte[] getQrcodeImg() {
		return qrcodeImg;
	}
	public void setQrcodeImg(byte[] qrcodeImg) {
		this.qrcodeImg = qrcodeImg;
	}
	public boolean isQrcodePrinted() {
		return qrcodePrinted;
	}
	public void setQrcodePrinted(boolean qrcodePrinted) {
		this.qrcodePrinted = qrcodePrinted;
	}
	
}
