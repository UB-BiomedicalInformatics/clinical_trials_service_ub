package edu.buffalo.bmi;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONString;

import java.sql.*;


@Path("/query")
public class ClinicalTrialsQuery {

//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String returnAllTrials() throws Exception{
//
//		PreparedStatement query = null;
//		Connection conn = null;
//		String returnString = null;
//
//		try{
//			conn = ClinicalTrialsDao.sqlDsConn().getConnection();
//			query = conn.prepareStatement("SELECT * FROM ClinicalTrialInfo");
//			ResultSet rs = query.executeQuery();
//			System.out.println("total results size: "+rs.getFetchSize());
//			ClinicalTrialsToJson converter = new ClinicalTrialsToJson();
//			JSONArray json = new JSONArray();
//			JSONObject jsonObj = new JSONObject();
//			jsonObj = converter.toJSONObject(rs);
//			query.close();
//
//			returnString = jsonObj.toString();
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		finally{
//			if(conn !=null ) conn.close();
//		}
//		return returnString;
//	}

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    //public void createUserProfile(String FromAddr, String ToAddr,String Subject,String Emailbody) throws Exception{
	public void createUserProfile(String inputjson) throws Exception{

		System.out.println("inside create user profile");

		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;
		String userEmailAddr = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("inputjson: "+inputjson);
		JSONObject jsonObj = new JSONObject(inputjson);

		// Getting JSON Array node
		String firstNameStr = jsonObj.getString("FirstName");
		String lastNameStr = jsonObj.getString("LastName");
		String streetAddrStr = jsonObj.getString("StreetAddress");
		String cityStr = jsonObj.getString("City");
		String zipcodeStr = jsonObj.getString("Zipcode");
		String emailAddrStr = jsonObj.getString("Email");
		String phoneNoStr = jsonObj.getString("PhoneNumber");
		String fromAddrStr = jsonObj.getString("FromAddr");
		String toAddrStr = jsonObj.getString("ToAddr");
		String subjectStr = jsonObj.getString("Subject");
		String emailStr = jsonObj.getString("Emailbody");


		System.out.println("firstNameStr: "+firstNameStr);
		System.out.println("lastNameStr: "+lastNameStr);
		System.out.println("streetAddrStr: "+streetAddrStr);
		System.out.println("cityStr: "+cityStr);
		System.out.println("zipcodeStr: "+zipcodeStr);
		System.out.println("emailAddrStr: "+emailAddrStr);
		System.out.println("phoneNoStr: "+phoneNoStr);
		//System.out.println("FromAddrStr: "+fromAddrStr);
		if(emailAddrStr!=null) {
		fromAddrStr = emailAddrStr;
		}
		System.out.println("FromAddrStr: "+fromAddrStr);
		System.out.println("ToAddrStr: "+toAddrStr);
		System.out.println("SubjectStr: "+subjectStr);
		System.out.println("EmailStr: "+emailStr);
		System.out.println("timestamp: "+timestamp);

		try{
			System.out.println("inside tryyy");
			conn = ClinicalTrialsDao.sqlDsConn().getConnection();
			System.out.println("after conn");
			//query = conn.prepareStatement("INSERT INTO ClinicalTrials.dbo.CONTACTSC_INFO(FROMADDR,TOADDR,SUBJECT,EMAILBODY,ACCESSTIME) VALUES(?,?,?,?,?)");
			query = conn.prepareStatement("INSERT INTO ClinicalTrials.dbo.CONTACTSC_INFO_UB(FIRSTNAME,LASTNAME,STREET,CITY,ZIPCODE,PHONENUMBER,FROMADDR,TOADDR,SUBJECT,EMAILBODY,ACCESSTIME) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			System.out.println("after query");
			//ResultSet rs = query.executeQuery();
			System.out.println("result set");


//			query.setString(1, fromAddrStr);
//			query.setString(2, toAddrStr);
//			query.setString(3, subjectStr);
//			query.setString(4, emailStr);
//			query.setTimestamp(5, timestamp);

			query.setString(1, firstNameStr);
			query.setString(2, lastNameStr);
			query.setString(3, streetAddrStr);
			query.setString(4, cityStr);
			query.setString(5, zipcodeStr);
			query.setString(6, phoneNoStr);
			query.setString(7, fromAddrStr);
			query.setString(8, toAddrStr);
			query.setString(9, subjectStr);
			query.setString(10, emailStr);
			query.setTimestamp(11, timestamp);

			query.executeUpdate();
			query.close();


		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(conn !=null ) conn.close();
		}
	}
//
//	@GET
//    @Produces(MediaType.APPLICATION_JSON)
//	@Path("/trialInfo")
//	public String getTrialInfo(String trialId) throws Exception{
//
//		System.out.println("getTrialInfo");
//
//		PreparedStatement query = null;
//		Connection conn = null;
//		String SCEmailAddr = null;
//
//		String locationId = getLocationId(trialId);
//
//		try{
//			conn = ClinicalTrialsDao.sqlDsConn().getConnection();
//			query = conn.prepareStatement("SELECT EmailAddress FROM ClinicalTrials.dbo.ClinicalTrialLocationContact where LocationId=?");
//			query.setString(1, locationId);
//			ResultSet rs = query.executeQuery();
//			System.out.println("total results size in getTrialInfo: "+rs.getFetchSize());
//			ClinicalTrialsToJson converter = new ClinicalTrialsToJson();
//			JSONArray json = new JSONArray();
//			JSONObject jsonObj = new JSONObject();
//			jsonObj = converter.toJSONObject(rs);
//			query.close();
//
//			SCEmailAddr = jsonObj.toString();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		finally{
//			if(conn !=null ) conn.close();
//		}
//		return SCEmailAddr;
//	}
//
//	public String getLocationId(String trialId) throws Exception{
//
//		System.out.println("getLocationId");
//
//		PreparedStatement query = null;
//		Connection conn = null;
//		String locationId = null;
//
//		try{
//			conn = ClinicalTrialsDao.sqlDsConn().getConnection();
//			query = conn.prepareStatement("SELECT LocationId FROM ClinicalTrials.dbo.ClinicalTrialLocation where trialId=?");
//			query.setString(1, trialId);
//
//			ResultSet rs = query.executeQuery();
//			System.out.println("total results size in getLocationId: "+rs.getFetchSize());
//			ClinicalTrialsToJson converter = new ClinicalTrialsToJson();
//			JSONArray json = new JSONArray();
//			JSONObject jsonObj = new JSONObject();
//			jsonObj = converter.toJSONObject(rs);
//			query.close();
//
//			locationId = jsonObj.toString();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		finally{
//			if(conn !=null ) conn.close();
//		}
//		return locationId;
//	}

//	private static java.sql.Timestamp getCurrentTimeStamp() {
//
//		java.util.Date today = new java.util.Date();
//		return new java.sql.Timestamp(today.getTime());
//
//	}



	}
