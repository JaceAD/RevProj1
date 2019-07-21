package com.revature.utilities;

import java.util.Scanner;

import com.revature.DAOImps.AccountDAOImp;

public class Populator {
	public static Scanner s = new Scanner(System.in);
	
	public Populator() {
		
	}
	
	public static void main(String[] args) {
		
		boolean makeUsers=true;
		while(makeUsers) {
			createUser();
			System.out.println("Do you want to make another user? y or n");
			makeUsers = s.nextLine().equalsIgnoreCase("y");
		}
		
		
	}
	
	
	public static void createUser() {
		String userName;
		String password;
		String name;
		String email;
		AccountDAOImp acc;
		
		System.out.println("Enter Username: ");
		userName = s.nextLine();
		System.out.println("Enter Password: ");
		password = s.nextLine();
		System.out.println("Enter Name: ");
		name = s.nextLine();
		System.out.println("Enter Email: ");
		email = s.nextLine();
		
		//Call the AccountDAOImp.createAccount()
		
	}
}
