package accounts;

import java.util.UUID;

public class Customer extends User{
	private User customer;
	private String customerId;
	private final int type = 2;
	
	public Customer(String email, String password)
	{
		super(email,password);
		setCustomerId(UUID.randomUUID().toString()); 
		
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getType() {
		return type;
	}
	
	
}
