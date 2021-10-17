package accounts;

import java.time.LocalDate;

import inventory.Inventory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer c= new Customer();
		//c.create(1001, "Password", "Whittaker", "E");
		//c.delete(0);
		c.readAll();
		Employee e= new Employee();
		Inventory i= new Inventory();
		//i.create("ABC1", "Shoes", "Large", "Online", "2021-10-17");
		
	}

}
