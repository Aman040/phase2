package com.utiliity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.entity.Project;


public class HiberanteUtlity {
	private static SessionFactory sf;
	static {
		sf=new AnnotationConfiguration()
				.addAnnotatedClass(Project.class)
				.configure()
				.buildSessionFactory();
	}
	public static Session getSession() {
		Session session=sf.openSession();
		return session;
	}

}
