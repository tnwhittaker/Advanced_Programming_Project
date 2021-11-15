package project.client;

public class Driver {

	public static void main(String[] args) {
		Client client= new Client();
		//Student student= new Student("1234","Torrike","Whittaker","whittakertorrike@gmail.com");
		/*
		client.sendAction("Add Student");
		System.out.println("Message sending to server");
		client.sendStudent(student);
		System.out.println("Record sending to server");
		client.receiveResponse();
		System.out.println("Response received from server");
		*/
		client.sendAction("Find Student");
		client.sendStudentID("1234");
		client.receiveResponse();
		client.closeConnection();

	}

}
