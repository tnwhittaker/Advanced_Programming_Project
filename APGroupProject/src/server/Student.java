package server;

import java.io.Serializable;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	
	public Student()
	{
		
	}

	public Student(String ID, String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.id= ID;
		
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	/*
	public void create()
	{
		Session session= SessionFactoryBuilder.getSessionFactory().getCurrentSession();
		Transaction transaction= session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
	}
	
	public void update()
	{
		Session session= SessionFactoryBuilder.getSessionFactory().getCurrentSession();
		Transaction transaction= session.beginTransaction();
		Student stu= (Student) session.get(Student.class, this.id);
		stu.setFirstName(this.firstName);
		stu.setLastName(this.lastName);
		session.update(stu);
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Student> readAll()
	{
		List<Student> studentlist= new ArrayList<>();
		Session session= SessionFactoryBuilder.getSessionFactory().getCurrentSession();
		Transaction transaction= session.beginTransaction();
		studentlist= (List<Student>) session.createQuery("FROM Student").getResultList();
		transaction.commit();
		session.close();
		return studentlist;
	}

	public void delete()
	{
		Session session= SessionFactoryBuilder.getSessionFactory().getCurrentSession();
		Transaction transaction= session.beginTransaction();
		Student stu= (Student) session.get(Student.class, this.id);
		session.delete(stu);
		transaction.commit();
		session.close();
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	*/
	
}
