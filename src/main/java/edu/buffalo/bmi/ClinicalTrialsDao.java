package edu.buffalo.bmi;


import javax.naming.*;
import javax.sql.*;
public class ClinicalTrialsDao{
	
	private static DataSource ds = null;
	private static Context context = null;
	
	public static DataSource sqlDsConn() throws Exception{
		
		if(ds != null){
			return ds;
		}
		
		try{
			if(context == null){
				context = new InitialContext();
			}
			Context envCtx = (Context) context.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/sqlserv");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ds;
	}
		

}
