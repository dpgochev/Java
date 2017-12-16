package bg.uni.sofia.fmi.mjt.supermarket;

public class Customer implements Runnable {

	private int serviceTime;
	private double totalPrice;
	private CashDesk cashDesk;

	public Customer(CashDesk cashDesk, int serviceTime, double totalPrice) {
		this.cashDesk = cashDesk;
		this.totalPrice = totalPrice;
		this.serviceTime = serviceTime;
	}

	@Override
	public void run() {
		this.buyGoods();

	}

	/**
	 * Simulates one purchase: waits a bit and returns the amount of money spent
	 * (i.e. the goods price)
	 */
	public double buyGoods() {
		try {
			Thread.sleep(this.serviceTime);
			return this.getTotalPrice();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0.0;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public static void main(String[] args) {
		
	}

}