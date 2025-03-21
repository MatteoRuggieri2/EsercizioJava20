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
	
	
	
	
	static private final String pathFile = "src/text_files/accounts_list.txt";
	
	private List<String> fileRowsContent = new ArrayList<String>(); // Contiene le righe del file così come sono
	
	private List<String> analyzedRows = new ArrayList<String>(); // Contiene le righe corrette analizzate (serve per verificare eventuali doppioni)
	
	private List<String> discardedRows = new ArrayList<String>(); // Contiene tutte le righe scartate
	
	private Map<String, User> users;
	
	

	public static void main(String[] args) {
		AccountManagement accountManagement = new AccountManagement(pathFile);
		accountManagement.addMail("1", "test");
	}
	
	
	
	AccountManagement(String fileName) {
		// Leggo ogni riga e separo nome, cognome, indirizzo, email
		this.users = new HashMap<String, User>();
		readFile(fileName);
		
	}

	
	// CHECKED
	@Override
	public boolean addUser(String userId, String name, String surname, String address) {
		
		User newUser = new User();
		newUser.setNome(name);
		newUser.setCognome(surname);
		newUser.setIndirizzo(address);
		
		// Aggiungo l'utente alla mappa
		try {
			this.users.put(userId, newUser);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	
	// CHECKED
	@Override
	public boolean addMail(String idUser, String mail) {
		Optional<User> userToUpdate = Optional.ofNullable(users.get(idUser));
		if (userToUpdate.isEmpty()) {
			return false;
		}
		userToUpdate.get().addMailToList(mail);
		return true;
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
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.rowsAnalyzer(this.fileRowsContent);
	}
	
	
	private void rowsAnalyzer(List<String> rows) {
		for (String row : rows) {

			Scanner sc = new Scanner(row);
			
			// Se la riga è doppia o è vuota la scarto
			if (this.analyzedRows.contains(row)) {
				discardRow(row);
				continue;
			} else if (!sc.hasNext()) {
				continue;
			}
			
			/* Per ottimizzazione, le variabili le inizializzo
			solo dopo essermi accertato che la riga non sia da scartare */
			String userId = null;
			String userEmail = null;
			String userName = null;
			String userLastname = null;
			String userAddress = null;

//			Optional<String> userId = Optional.ofNullable(null);
//			Optional<String> userEmail = Optional.ofNullable(null);
//			Optional<String> userName = Optional.ofNullable(null);
//			Optional<String> userLastname = Optional.ofNullable(null);
//			Optional<String> userAddress = Optional.ofNullable(null);
			
			
			
			// Dato che la riga non è vuota, salvo lo userId
			userId = sc.next();
			
			// Se non c'è altro oltre l'id, la riga è errata, quindi la scarto
			if (!sc.hasNext()) {
				discardRow(row);
				continue;
			}
			
			// Se c'è qualcosa dopo l'id controllo di che dato si tratta
			String secondToken = sc.next();
			
			// EMAIL CHECK
			if (secondToken.contains("@")) {
				
				//	Controllo il formato della meail, se errata scarto la riga
				if (!emailCheck(secondToken)) {
					discardRow(row);
					continue;
				}
				
				userEmail = secondToken;
//				System.out.println(userEmail);
				
			/* Altrimenti se c'è un terzo token vuol dire che il secondo
			 * è il nome, e il terzo è il cognome */
			} else if (sc.hasNext()) {
				userName = secondToken;
				userLastname = sc.next();

				// Se c'è il quarto token salvo l'indirizzo dell'utente, altrimenti scarto riga
				if (sc.hasNext()) {
					userAddress = sc.nextLine().trim();
				} else {
					discardRow(row);
					continue;
				}
				
			} else {
				discardRow(row);
				continue;
			}
			
			// Dopo che ha superato tutti i controlli la metto nella lista delle righe analizzate
			this.analyzedRows.add(row);
			
			
			// TODO -> Salva gli users
			// SE TROVA ID AGGIUNGI DATI NON NULL
			// SE NON TROVA CREA CON ID E AGGIUNGI I DATI NON NULL
			
			
			System.out.println("\nuserId: " + userId);
			System.out.println("userEmail: " + userEmail);
			System.out.println("userName: " + userName);
			System.out.println("userLastname: " + userLastname);
			System.out.println("userAddress: " + userAddress);
			
		}
		
	}
	
	


	// Questa funzione ritorna un valore booleano, true se la email è formattata correttamente, altrimenti false.
	public boolean emailCheck(String email) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,6}$";

	   // Blocco le doppie estensioni come .it.it
	   if (email.matches(".*\\.[a-zA-Z]{2,6}\\.[a-zA-Z]{2,6}$")) { return false; }
	   Pattern p = Pattern.compile(regex);
	   return p.matcher(email).matches();
	}
	
	// Questo metodo aggiunge alla lista di righe scartate quella passata come parametro.
	private void discardRow(String row) {
		this.discardedRows.add(row);
	}

}






























