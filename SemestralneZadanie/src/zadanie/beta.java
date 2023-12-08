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

	    String cislo = scanner.nextLine();
	      
		switch(cislo) {
		case "1": 
			prihlasenie();
			break;
		case "2":
			registracia();
			break;
		default:
			System.out.print("Zadali ste zly vstup, skuste znova\n");
		
			
		}
		Thread.sleep(1000);
	}
	}
	
	
	private static void zbierkaKnih() throws SQLException{
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
		    	 String pridal = rs.getString("Pridal");
		    	 int rok = rs.getInt("Rok");
		    	
		    	 if (nazov.isEmpty() || autor.isEmpty() || zaner.isEmpty()|| pridal.isEmpty()) {
		    		 
		    		 System.out.println("V databaze nie su ziadne knihy");
		    	 
		    	 }
		    	 
		    	 System.out.println("\nNazov: " + nazov);
		    	 System.out.println("Autor: " + autor);
		    	 System.out.println("Zaner: " + zaner);
		    	 System.out.println("Rok vydania: " + rok);
		    	 System.out.println("Pridal: " + pridal);
		    	 
		     
		     }
		   
		     
		    
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	
	
	private static void pouzivatelia() throws SQLException{
		Connection con = Server.connect();
		PreparedStatement citaj = null;
		ResultSet rs = null;
		 
		try {
		     String sql = "SELECT * FROM Pouzivatelia";
		     citaj = con.prepareStatement(sql);
		     rs = citaj.executeQuery();
		    
		    
		     System.out.println("Všetci pouzivatelia v databaze");
		     
		     while(rs.next()) {
		    	 String username = rs.getString("username");	 
		    	 System.out.println("\nUsername: " + username);

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
		String cislo;
	
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
		    
		    	 		
		    	 		if(isLoggedIn) {
		    	 			char naspat = 0;
		    	 			while(true) {
		    	 			System.out.print("\n\n1 - Zobrazit knihy v databaze\n");
		    	 			System.out.print("2 - Zobrazit pouzivatelov\n");
		    	 			System.out.print("3 - Pridat knihu\n");
		    	 			System.out.print("4 - Pridat pouzivatela\n");
		    	 			System.out.print("5 - Odstranit knihu\n");
		    	 			System.out.print("6 - Odstranit pouzivatela\n");
		    	 			System.out.print("7 - Odhlasit sa\n");
		    	 			cislo = scanner.nextLine();
		    	 			switch(cislo) { // ADMIN
		    	 				case "1":
		    	 					zbierkaKnih();
		    	 					System.out.print("\nPre pokracovanie stlac hocijake cislo");
		    	 					scanner.nextLine();
		    	 					break;
		    	 				case "2":
		    	 					pouzivatelia();
		    	 					System.out.print("\nPre pokracovanie stlac hocijake cislo");
		    	 					scanner.nextLine();
		    	 					break;
		    	 				case "3":
		    	 					Scanner scanner2 = new Scanner(System.in);
		    	 					System.out.print("Nazov knihy: ");
		    	 					String nazov = scanner2.nextLine();
		    	 					System.out.print("Autor knihy: ");
		    	 					String autor = scanner2.nextLine();
		    	 					System.out.print("Zaner knihy ");
		    	 					String zaner = scanner2.nextLine();
		    	 					System.out.print("Rok vydania knihy ");
		    	 					int rok = scanner2.nextInt();
		    	 					  
		    	 					PridanieKnihy(nazov, autor, zaner, rok, username); 
		    	 					  
		    	 					  System.out.print("Kniha pridana!");
		    	 					break;
		    	 				case "4":
		    	 					registracia();
		    	 					break;
		    	 				case "5":
		    	 					odstranenieKnihy();
		    	 					System.out.printf("Kniha bola uspesne odstranena!\n");
		    	 					break;
		    	 				case "6":
		    	 					odstraneniePouzivatela();
		    	 					System.out.printf("Uzivatel odstraneny!\n");
		    	 					break;
		    	 				case "7":
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
		    	 			Thread.sleep(1000);
		    	 		}
		    	 		}
		    		
		    		 break;
		    	 }
		    	
		    	else if(username.equals(meno) && DatabazoveHeslo.equals(heslo)) {
	    	 		System.out.print("Bol si prihlaseny vitaj pouzivatel " + username);
	    	 		isLoggedIn = true;
	    	 		con.close();
	    
	    	 		
	    	 		if(isLoggedIn) {
	    	 			char naspat = 0;
	    	 			
	    	 			while(true) {
	    	 			System.out.print("\n\n1 - Zobrazit knihy v databaze\n");
	    	 			System.out.print("2 - Pridat knihu\n");
	    	 			System.out.print("3 - Odhlasit sa\n");
	    	 			cislo = scanner.nextLine();
	    	 			switch(cislo) { // obcajny pouzivatel
	    	 				case "1":
	    	 					zbierkaKnih();
	    	 					System.out.print("\nPre pokracovanie stlac hocijake cislo");
	    	 					scanner.nextLine();
	    	 					break;
	    	 				case "2":
	    	 					Scanner scanner2 = new Scanner(System.in);
	    	 					System.out.print("Nazov knihy: ");
	    	 					String nazov = scanner2.nextLine();
	    	 					System.out.print("Autor knihy: ");
	    	 					String autor = scanner2.nextLine();
	    	 					System.out.print("Zaner knihy ");
	    	 					String zaner = scanner2.nextLine();
	    	 					System.out.print("Rok vydania knihy ");
	    	 					int rok = scanner2.nextInt();
	    	 					  
	    	 					PridanieKnihy(nazov, autor, zaner, rok, username); 
	    	 					  
	    	 					System.out.print("Kniha pridana!");
	    	 					Thread.sleep(1000);
	    	 					break;
	    	 				case "3":
	    	 					naspat = 1;
	    	 					System.out.print("Bol si odhlaseny\n");
	    	 					Thread.sleep(1000);
	    	 					break;
	    	 					
	    	 				default: 
	    	 					System.out.print("Zadal si nepravny vstup");
	    	 					Thread.sleep(1000);
	    	 				}
	    	 			if(naspat == 1) {
	    	 				isLoggedIn = false;
	    	 				con.close();
	    	 				break;
	    	 			}
	    	 			
	    	 		}
	    	 		}
	    		
	    		 break;
		    	
		    		
		    	}
		    	 	
		    	
		     }
		     System.out.print("Neprihlaseny\n");
	    	 	

		     con.close();
		     
		}	catch(SQLException e) {
				System.out.println(e+"");
		
	}
		
}
	
	
	private static void odstranenieKnihy() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Zadaj nazov knihy ktoru chces odstranit: ");
			String nazov = scanner.nextLine();
			odstranitknihu(nazov);
		
	}
	
	
	private static void odstranitknihu(String nazov) throws SQLException {
		Connection con = Server.connect();
	    PreparedStatement zmaz = null;
	    try {
	        String sql = "delete from Knihy WHERE Nazov = ?";
	        zmaz = con.prepareStatement(sql);
	        zmaz.setString(1, nazov);
	        zmaz.execute();
	      
		} catch(Exception e) {
			System.out.println(e+"");
		}
	}
	
	private static void odstraneniePouzivatela() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Zadaj username pouzivatela ktoreho chces odstranit: ");
			String meno = scanner.nextLine();
			odstranpouzivatela(meno);
	}
	
	
	
	
	private static void odstranpouzivatela(String meno) throws SQLException {
		Connection con = Server.connect();
	    PreparedStatement zmaz = null;
	    try {
	        String sql = "delete from Pouzivatelia WHERE username = ?";
	        zmaz = con.prepareStatement(sql);
	        zmaz.setString(1, meno);
	        zmaz.execute();
	      
		} catch(Exception e) {
			System.out.println(e+"");
		}
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
	
	




private static void PridanieKnihy(String nazov, String autor, String zaner, int rok, String pridal) throws SQLException {
	Connection con = Server.connect();
    PreparedStatement zapisKnih= null;
    
    try {
        String sql = "INSERT INTO Knihy(Nazov, Autor, Zaner, Rok, Pridal) VALUES(?, ?, ?, ?, ?)";
        zapisKnih= con.prepareStatement(sql);
        zapisKnih.setString(1, nazov);
        zapisKnih.setString(2, autor);
        zapisKnih.setString(3, zaner);
        zapisKnih.setInt(4, rok);
        zapisKnih.setString(5, pridal);
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


