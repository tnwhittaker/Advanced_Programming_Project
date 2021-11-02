package accounts;

import java.util.UUID;

public class Employee extends User{
	private User employee;
	private String staffId;
	private final int type = 1;
	
	public Employee(String email, String password)
	{
		super(email,password);
//		setStaffId(UUID.randomUUID().toString());
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public int getType() {
		return type;
	}
	
	
	
	
}
