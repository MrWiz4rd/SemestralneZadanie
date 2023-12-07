package zadanie;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String [] args) throws SQLException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Virtualna biblioteka pre pokracovanie stlacte cislo:\n");
		while (true) {  
	
		System.out.print("1 - Prihlasenie\n");
		System.out.print("2 - Registracia\n");
		//3System.out.print("3 - Zbierka knih\n");

	    int cislo = scanner.nextInt();
	      
		switch(cislo) {
		case 1: 
			prihlasenie();
			break;
		case 2:
			registracia();
			break;
		default:
			System.out.print("Zadali ste zle cislo, skuste znova\n");
		
			
		}
		Thread.sleep(2000);
	}
	}
	
	
	private static void zbierka() throws SQLException{
		Connection con = Server.connect();
		PreparedStatement citaj = null;
		ResultSet rs = null;
		 
		try {
		     String sql = "SELECT * FROM Knihy";
		     citaj = con.prepareStatement(sql);
		     rs = citaj.executeQuery();
		    
		    
		     System.out.println("Všetky knihy v databaze");
		     
		     while(rs.next()) {
		    	 String nazov = rs.getString("Nazov");
		    	 String autor = rs.getString("Autor");
		    	 String zaner = rs.getString("Zaner");
		    	 int rok = rs.getInt("Rok");
		    	
		    	 if (nazov.isEmpty() || autor.isEmpty() || zaner.isEmpty()) {
		    		 
		    		 System.out.println("V databaze nie su ziadne knihy");
		    	 
		    	 }
		    	 
		    	 System.out.println("\nNazov: " + nazov);
		    	 System.out.println("Autor: " + autor);
		    	 System.out.println("Zaner: " + zaner);
		    	 System.out.println("Rok vydania: " + rok);
		     
		     }
		   
		     
		    
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
		
	
	
	
	private static void prihlasenie() throws SQLException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		
		Connection con = Server.connect();
		PreparedStatement citaj = null;
		ResultSet rs = null;
		boolean isLoggedIn = false;
		int cislo;
	
		System.out.print("Username: ");
		String meno = scanner.nextLine();
		System.out.print("Heslo: ");
		String heslo = scanner.nextLine();
		
		try {
		     String sql = "SELECT * FROM Pouzivatelia";
		     citaj = con.prepareStatement(sql);
		     rs = citaj.executeQuery();
		    
		     
		     while(rs.next()) {
		    	 String username = rs.getString("username");
		    	 String DatabazoveHeslo = rs.getString("password");
		    	 int uid = rs.getInt("uid");
		    	 
		    	 	if(username.equals(meno) && DatabazoveHeslo.equals(heslo) && uid == 1) { // podla uid zistujem kto je admin a kto klasicky pouzivatel
		    	 		System.out.print("Bol si prihlaseny vitaj admin " + username);
		    	 		isLoggedIn = true;
		    	 		con.close();
		    
		    	 		
		    	 		if(isLoggedIn == true) {
		    	 			char naspat = 0;
		    	 			while(true) {
		    	 			System.out.print("\n\n1 - Zobrazit knihy v databaze\n");
		    	 			System.out.print("2 - Pridat knihu\n");
		    	 			System.out.print("3 - Odstranit knihu\n");
		    	 			System.out.print("4 - Odstranit pouzivatela\n");
		    	 			System.out.print("5 - Odhlasit sa\n");
		    	 			cislo = scanner.nextInt();
		    	 			switch(cislo) { // ADMIN
		    	 				case 1:
		    	 					zbierka();
		    	 					break;
		    	 				case 2:
		    	 					knihy();
		    	 					break;
		    	 				case 3:
		    	 					odstranitknihu();
		    	 					break;
		    	 				case 4:
		    	 					odstranitpouzivatela();
		    	 					break;
		    	 				case 5:
		    	 					naspat = 1;
		    	 					System.out.print("Bol si odhlaseny\n");
		    	 					break;
		    	 					
		    	 				default: 
		    	 					System.out.print("Zadal si nepravny vstup");
		    	 				}
		    	 			if(naspat == 1) {
		    	 				isLoggedIn = false;
		    	 				con.close();
		    	 				break;
		    	 			}
		    	 			Thread.sleep(2000);
		    	 		}
		    	 		}
		    		
		    		 break;
		    	 }
		    	
		    	else if(username.equals(meno) && DatabazoveHeslo.equals(heslo)) {
	    	 		System.out.print("Bol si prihlaseny vitaj pouzivatel " + username);
	    	 		isLoggedIn = true;
	    	 		con.close();
	    
	    	 		
	    	 		if(isLoggedIn == true) {
	    	 			char naspat = 0;
	    	 			while(true) {
	    	 			System.out.print("\n\n1 - Zobrazit knihy v databaze\n");
	    	 			System.out.print("2 - Pridat knihu\n");
	    	 			System.out.print("3 - Odhlasit sa\n");
	    	 			cislo = scanner.nextInt();
	    	 			switch(cislo) { // obcajny pouzivatel
	    	 				case 1:
	    	 					zbierka();
	    	 					break;
	    	 				case 2:
	    	 					knihy();
	    	 					break;
	    	 				case 3:
	    	 					naspat = 1;
	    	 					System.out.print("Bol si odhlaseny\n");
	    	 					break;
	    	 					
	    	 				default: 
	    	 					System.out.print("Zadal si nepravny vstup");
	    	 				}
	    	 			if(naspat == 1) {
	    	 				isLoggedIn = false;
	    	 				con.close();
	    	 				break;
	    	 			}
	    	 			Thread.sleep(2000);
	    	 		}
	    	 		}
	    		
	    		 break;
		    	//System.out.print("NEPrihlaseny!!" + username);
		    		
		    	}
		    	
		     }

		     rs.close();
		     
		}	catch(SQLException e) {
				System.out.println(e+"");
		
		}
		
	}
	
	
	private static void odstranitpouzivatela() {
		
	}
	
	
	private static void odstranitknihu() {
		
	}
	
	
	private static void registracia() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("email: ");
		String email = scanner.nextLine();
		System.out.print("username: ");
		String meno = scanner.nextLine();
		System.out.print("heslo: ");
		String heslo = scanner.nextLine();
		System.out.print("potvrdte heslo: ");
		String heslo2 = scanner.nextLine();
		
		if (heslo2.equals(heslo)) {
			//Connection con = Server.connect();			
			PridanieUzivatelov(meno, heslo, email);
			System.out.print("Uzivatel vytvoreny!\n\n");
		
			
		}
		else {
			System.out.print("Uzivatel nevytvoreny - hesla sa nezhoduju!! ");
			
	}
} 
	
	

private static void knihy() throws SQLException {
	
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Nazov knihy: ");
	String nazov = scanner.nextLine();
	System.out.print("Autor knihy: ");
	String autor = scanner.nextLine();
	System.out.print("Zaner knihy ");
	String zaner = scanner.nextLine();
	System.out.print("Rok vydania knihy ");
	int rok = scanner.nextInt();
	  
	PridanieKnihy(nazov, autor, zaner, rok); 
	  
	  System.out.print("Kniha pridana!");
    
 
}


private static void PridanieKnihy(String nazov, String autor, String zaner, int rok) throws SQLException {
	Connection con = Server.connect();
    PreparedStatement zapisKnih= null;
    
    try {
        String sql = "INSERT INTO Knihy(Nazov, Autor, Zaner, Rok) VALUES(?, ?, ?, ?)";
        zapisKnih= con.prepareStatement(sql);
        zapisKnih.setString(1, nazov);
        zapisKnih.setString(2, autor);
        zapisKnih.setString(3, zaner);
        zapisKnih.setInt(4, rok);
        zapisKnih.execute();
       
	} catch(SQLException e) {
		System.out.println(e+"");
	}
}



private static void PridanieUzivatelov(String username, String password, String email) throws SQLException {
	Connection con = Server.connect();
    PreparedStatement zapis = null;
    try {
        String sql = "INSERT INTO Pouzivatelia(username, password, email) VALUES(?, ?, ?)";
        zapis = con.prepareStatement(sql);
        zapis.setString(1, username);
        zapis.setString(2, password);
        zapis.setString(3, email);
        zapis.execute();
       
	} catch(SQLException e) {
		System.out.println(e+"");
	}
}


} // mainova zatvorka


