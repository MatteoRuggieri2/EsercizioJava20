package esercizi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountManagement implements Account<User> {
	
	// TODO: - Controlla metodi già scritti
	// TODO: - Implementa quelli mancanti
	// TODO: - Fai i test JUnit
	
	
	
	
	static String pathFile = "src/text_files/accounts_list.txt";
	
	List<String> fileRowsContent = new ArrayList<String>(); // Contiene le righe del file così come sono
	
	Map<String, User> users;
	
	
	

	public static void main(String[] args) {
		AccountManagement accountManagement = new AccountManagement(pathFile);
		accountManagement.addMail("1", "test");
	}
	
	AccountManagement(String fileName) {
		
		// Leggo il file e salvo ogni riga in fileRows
//		this.fileRows = readFile(this.pathFile);
		
		// Leggo ogni riga e separo nome, cognome, indirizzo, email
		this.users = new HashMap<String, User>();
		readFile(fileName);
		
	}

	@Override
	public boolean addUser(String idUser, String nome, String Cognome, String indirizzo) {
		
		// Istanzio un nuovo utente
		User newUser = new User();
		newUser.setNome(nome);
		newUser.setCognome(Cognome);
		newUser.setIndirizzo(indirizzo);
		
		// Aggiungo l'utente alla mappa
		try {
			this.users.put(idUser, newUser);
			return true;
			
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean addMail(String idUser, String mail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsUser(String idUser) {
		return this.users.keySet().contains(idUser);
	}

	@Override
	public boolean userHasMail(String idUser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional user(String idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] userIds(EnumSortType sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] userMails(String idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] users(EnumSortType sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User firstUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User lastUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] firstUsers(int numUsers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] lastUsers(int numUsers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] discardedRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] allMails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// CHECKED
	// Questa funzione prende il path del file, apre il file e ritorna una lista 
	private void readFile(String path) {
		
		// Apro il file
		File inputFile = new File(path);
		
		// Leggo il file con lo scanner
		try {
			Scanner scanner = new Scanner(inputFile);
			
			// FINCHE' ci sono righe, le salvo in fileRowsContent
			while (scanner.hasNextLine()) {
				this.fileRowsContent.add(scanner.nextLine());
			}
			
			// Chiudo lo scanner
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.rowsAnalyzer(fileRowsContent);

	}
	
	
	// Questa funzione legge ogni riga e separa i vari dati
	private void rowsAnalyzer(List<String> rows) {
		for (String row : rows) {
			
			String userId = null;
//			Optional<String> userId = Optional.ofNullable(null);
			String userEmail = null;
//			Optional<String> userEmail = Optional.ofNullable(null);
			String userName = null;
//			Optional<String> userName = Optional.ofNullable(null);
			String userLastname = null;
//			Optional<String> userLastname = Optional.ofNullable(null);
			String userAddress = null;
//			Optional<String> userAddress = Optional.ofNullable(null);
			Scanner scanner = new Scanner(row);
			
			// Se la riga non è vuota
			if (scanner.hasNext()) {
				
				// Salvo userId
				userId = scanner.next();
			}
			
			// Se c'è qualcosa dopo l'id
			if (scanner.hasNext()) {
				String secondToken = scanner.next();
				
				// Se il secondo token contiene @ vuol dire che è una mail
				if (secondToken.contains("@")) {
					
//					CONTROLLO, se la MAIL E' GIUSTA continuo, altrimenti scarto la riga e vado con la prox
//					emailCheck(secondToken);
//					if (emailCheck(secondToken)) {
//						userEmail = secondToken;
//					} else {
//						continue;
//					}
					
					userEmail = secondToken;
					System.out.println(userEmail);
					
					/* Altrimenti se c'è un terzo token vuol dire che il secondo
					 * è il nome, e il terzo è il cognome */
				} else if (scanner.hasNext()) {
					userName = secondToken;
					
					// Salvo il cognome
					userLastname = scanner.next();
				}
				
				// Se c'è il quarto token è l'indirizzo dell'utente
				if (scanner.hasNext()) {
					userAddress = scanner.nextLine();
				}
			}
			
			// TODO
			// Se l'utente non esiste ed è presente la email vuol dire che la riga ha solo id e mail
			if (!this.users.containsKey(userId) && userEmail != null) {
//			if (!this.users.containsKey(userId) && userEmail.isPresent()) {
				
				// Ho ID e MAIL, devo mettere altri dati falsi
				this.addUser(userId, userName, userLastname, userAddress);
//				this.addUser(userId, userName, userLastname, userAddress.orElse(""));
				
//				Gestire il caso in cui l'utente esiste già e bisogna aggiungere una email
				
				
				// Aggiungo la mail
//				user.setMailList.add(userEmail);
			} else if (!this.users.containsKey(userId)) {
//				this.addUser(userId, userName, userLastname, userAddress);
			}
			
			
		}
		
	}
	
	
	
	// Questa funzione ritorna un valore booleano, true se la email è formattata correttamente, altrimenti false.
	public boolean emailCheck(String email) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(email).matches();
	}
	

}






























