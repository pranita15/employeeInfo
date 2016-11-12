package com.EmpInfo.DAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.EmpInfo.Beans.EmpMasterBean;
import com.EmpInfo.DAO.EmpMasterDAO;

@Repository
public class EmpMasterDAOImpl implements EmpMasterDAO {

	Statement stmt = null;
	Connection con = null;

	public String InsertEmpDataDAO(String empName, String dept, String designation, String salary, String joiningDate)
			throws Exception {
		int i = 0;
		String success = "";
		String sql = "INSERT INTO empInfo " + "(empName, dept, designation,salary,joiningDate) VALUES (?, ?, ?, ?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/citiswd_db", "root", "");
			stmt = con.createStatement();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, empName);
			ps.setString(2, dept);
			ps.setString(3, designation);
			ps.setInt(4, Integer.parseInt(salary));
			ps.setString(5, joiningDate);
			i = ps.executeUpdate();
			if (i > 0)
				success = "success";
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		finally {
			if (con != null) {
				con.close();
			}
		}
		return success;
	}

	public List<EmpMasterBean> getListEmpDataDAO() throws Exception {

		List<EmpMasterBean> empMasterArrayList = new ArrayList<EmpMasterBean>();
		String sql = "SELECT * FROM empInfo ";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/citiswd_db", "root", "");
			stmt = con.createStatement();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Integer empId = rs.getInt("empId");
				String empName = rs.getString("empName");
				String dept = rs.getString("dept");
				String designation = rs.getString("designation");
				Integer salary = rs.getInt("salary");
				String joiningDate = rs.getString("joiningDate");

				EmpMasterBean empMasterBean = new EmpMasterBean(empId, empName, dept, designation, salary, joiningDate);
				empMasterArrayList.add(empMasterBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empMasterArrayList;
	}

	public List<EmpMasterBean> getEmpDetailsByIdDAO(Integer empId) throws Exception {

		List<EmpMasterBean> empData = new ArrayList<EmpMasterBean>();
		String sql = "SELECT * FROM empInfo where empId= ?";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/citiswd_db", "root", "");
			stmt = con.createStatement();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				empId = rs.getInt("empId");
				String empName = rs.getString("empName");
				String dept = rs.getString("dept");
				String designation = rs.getString("designation");
				Integer salary = rs.getInt("salary");
				String joiningDate = rs.getString("joiningDate");

				EmpMasterBean empMasterBean = new EmpMasterBean(empId, empName, dept, designation, salary, joiningDate);
				empData.add(empMasterBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empData;
	}

	public HashMap<String, Object> getEmpDetailsByEmpIdDAO(Integer empId) throws Exception {

		HashMap<String, Object> empData = new HashMap<String, Object>();
		String sql = "SELECT * FROM empInfo where empId= ?";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/citiswd_db", "root", "");
			stmt = con.createStatement();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columns; ++i) {
					empData.put(md.getColumnName(i), rs.getObject(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empData;
	}

	public String updateEmpDataDAO(Integer empId,String empName,String dept,String designation,Integer salary,String joiningDate) throws Exception {
		String s = "";
		String sql=" UPDATE empInfo SET empName = ?, dept = ?, designation =?, salary =?, joiningDate=? WHERE empId = ? ";
		int  i =0;
		    
		    
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/citiswd_db", "root", "");
			stmt = con.createStatement();
			PreparedStatement ps = con.prepareStatement(sql);
		    ps.setString(1,empName);
		    ps.setString(2,dept);
		    ps.setString(3,designation);
		    ps.setInt(4,salary);
		    ps.setString(5,joiningDate);
		    ps.setInt(6,empId);
			i = ps.executeUpdate();
			if (i > 0)
				s = "success";
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public String deleteEmpDataDAO(Integer empId) throws Exception{
		String s="";
		int i=0;
		String sql="DELETE empInfo where empId= ? ";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/citiswd_db", "root", "");
			stmt = con.createStatement();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, empId);
			i = ps.executeUpdate();
			if (i > 0)
				s = "success";
			ps.close();
			
		}catch(Exception e){ 
			e.printStackTrace();
		}
		return s;
	}
	
	
}
