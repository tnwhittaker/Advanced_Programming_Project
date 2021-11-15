package project.connectionFiles;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import project.accounts.User;


public class SessionFactoryBuilder {
	private static SessionFactory sessionfactory=null;
	
	
	public static SessionFactory getSessionFactory()
	{
		if (sessionfactory==null) {
			sessionfactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
		}
		
		
		return sessionfactory;
	}
	
	public static void closeSessionFactory()
	{
		if(sessionfactory!=null)
		{
			sessionfactory.close();
		}
	}
	
	

}
