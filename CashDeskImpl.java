package bg.uni.sofia.fmi.mjt.supermarket;

public class CashDeskImpl implements CashDesk {

    private static final int MAX_CASH = 100;
    private double currentAmount;

    public CashDeskImpl() {	
    	this.currentAmount=0;
    }

    @Override
    public void serveCustomer(Customer customer) {
    	synchronized(this){
    		System.out.println("Sale is currently ongoing");
    	this.setAmount(customer.getTotalPrice());
    	}
    }

    @Override
    public double getAmount() {
        return this.currentAmount;
    }

    @Override
    public void setAmount(double amount) {
        this.currentAmount=amount;
    }
    public static void main(String[] args) {
    	 
    	CashDesk test =new CashDeskImpl();
    	Customer cust= new Customer(test,2,5);
    	
    	test.serveCustomer(cust);
    	System.out.println(test.getAmount());
    	
	}

}