package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
//import java.sql.*;

public class SingletonConnection {
Properties props=new Properties();
private static String user;
private static String password;
private static String url;
private static Connection connect;
private SingletonConnection(){
	
	try {
	
	props.load(new FileInputStream("config.properties"));
	url=props.getProperty("jdbc.url");
	user=props.getProperty("jdbc.user");
	password=props.getProperty("jdbc.password");
	connect = DriverManager.getConnection(url, user, password);
     System.out.println("connecte");
} 
catch (SQLException e)
{ e.printStackTrace();; 
}
catch(IOException e)
{
	e.printStackTrace();
}
}
//Méthode qui retourne l’instance et la créer si elle n'existe pas
public static Connection getInstance(){
if(connect == null){
	
new SingletonConnection();
}
return connect;

}

}


