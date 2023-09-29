package main;

import java.sql.SQLException;


import interfaces.WelcomePage;


public class Main {
	public static void main(String[] argvs ) throws SQLException  {
		
		WelcomePage ps= new WelcomePage();
		ps.setVisible(true); 
		ps.setResizable(false);
		
		
	}
	

}
