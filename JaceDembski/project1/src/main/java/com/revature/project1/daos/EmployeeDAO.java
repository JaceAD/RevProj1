package com.revature.project1.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project1.beans.Employee;

public class EmployeeDAO {

	
	
	public static Employee getEmployeeById(int id) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT EMP_ID, EMP_FNAME, EMP_LNAME, EMP_PASSWORD, EMP_EMAIL, EMP_ACCESS_LEVEL "
					+ "FROM EMPLOYEES WHERE EMP_ID = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet results = st.executeQuery();
			Employee emp = null;
			while(results.next()) {
				emp = new Employee(results.getInt("EMP_ID"), results.getString("EMP_FNAME"),
						results.getString("EMP_LNAME"), results.getString("EMP_PASSWORD"),
						results.getString("EMP_EMAIL"), results.getString("EMP_ACCESS_LEVEL").charAt(0));
			}
			return emp;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static List<Employee> getAllEmployees() {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT EMP_ID, EMP_FNAME, EMP_LNAME, EMP_PASSWORD, EMP_EMAIL, EMP_ACCESS_LEVEL "
					+ "FROM EMPLOYEES";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet results = st.executeQuery();
			ArrayList<Employee> emps = new ArrayList<Employee>();
			while(results.next()) {
				emps.add(new Employee(results.getInt("EMP_ID"), results.getString("EMP_FNAME"),
						results.getString("EMP_LNAME"), results.getString("EMP_PASSWORD"),
						results.getString("EMP_EMAIL"), results.getString("EMP_ACCESS_LEVEL").charAt(0))
						);
			}
			return emps;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static void addEmployee(Employee emp) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO EMPLOYEES (EMP_ID, EMP_FNAME, EMP_LNAME, EMP_PASSWORD, EMP_EMAIL, EMP_ACCESS_LEVEL) "
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, emp.getId());
			st.setString(2, emp.getfName());
			st.setString(3, emp.getlName());
			st.setString(4, emp.getpWord());
			st.setString(5, emp.getEmail());
			st.setString(6, String.valueOf(emp.getAccessLvl()));
			st.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void updateEmployee(Employee emp) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE EMPLOYEES SET "
					+ "EMP_FNAME = ?, EMP_LNAME = ?, EMP_PASSWORD = ?, EMP_EMAIL = ?, EMP_ACCESS_LEVEL = ? "
					+ " WHERE EMP_ID = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, emp.getfName());
			st.setString(2, emp.getlName());
			st.setString(3, emp.getpWord());
			st.setString(4, emp.getEmail());
			st.setString(5, String.valueOf(emp.getAccessLvl()));
			st.setInt(6, emp.getId());
			st.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
