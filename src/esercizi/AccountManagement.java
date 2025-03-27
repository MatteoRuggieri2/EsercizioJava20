package esercizi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class AccountManagement implements Account<User> {
	
	// TODO: - Controlla metodi già scritti
	// TODO: - Implementa quelli mancanti
	// TODO: - Fai i test JUnit
	
	
	// TODO: Metti le variabili final
	
	static private final String pathFile = "src/text_files/accounts_list.txt";
	
	private List<String> fileRowsContent = new ArrayList<String>(); // Contiene le righe del file così come sono
	
	private List<String> analyzedRows = new ArrayList<String>(); // Contiene le righe corrette analizzate (serve per verificare eventuali doppioni)
	
	private List<String> discardedRows = new ArrayList<String>(); // Contiene tutte le righe scartate
	
	private Map<String, User> users;
	
	

	public static void main(String[] args) {
		AccountManagement am = new AccountManagement(pathFile);
		User[] sortedUsers = am.firstUsers(10);
		System.out.println(sortedUsers);
		System.out.println("PROGRAMMA TERMINATO CON SUCCESSO");
		am.firstUser();
		
	}
	
	
	
	public AccountManagement(String fileName) {
		// Leggo ogni riga e separo nome, cognome, indirizzo, email
		this.users = new HashMap<String, User>();
		readFile(fileName);
		
	}

	
	// CHECKED
	@Override
	public boolean addUser(String userId, String name, String surname, String address) {
		
		// Verifico se esiste l'utente, altrimenti ne creo uno
		User user = user(userId).orElse(new User());
		if (userId != null) { user.setId(userId); }
		if (name != null) { user.setName(name); }
		if (surname != null) { user.setLastname(surname); }
		if (address != null) { user.setAddress(address); }
		
		// Aggiungo l'utente alla mappa
		try {
			this.users.put(userId, user);
			return true;
			
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
	
	// CHECKED
	@Override
	public boolean addMail(String userId, String mail) {
		Optional<User> userToUpdate = Optional.ofNullable(users.get(userId));
		if (userToUpdate.isEmpty()) {
			addUser(userId, null, null, null);
		}
		userToUpdate.get().addMailToList(mail);
		return true;
	}

	// CHECKED
	@Override
	public boolean existsUser(String userId) {
		return user(userId).isPresent();
	}

	// CHECKED
	@Override
	public boolean userHasMail(String userId) {
		return userMails(userId).length > 0 ? true : false;
	}

	// CHECKED
	@Override
	public Optional<User> user(String userId) {
		return Optional.ofNullable(this.users.get(userId));
	}

	// CHECKED
	@Override
	public String[] userIds(EnumSortType sortType) {
		
		Set<String> sortedUserIds = new TreeSet<String>(
				new Comparator<String>() {

					@Override
					public int compare(String s1, String s2) {
						// s1, s2 ordine alfabetico | s2, s1 ordine alf invertito
						if (sortType == EnumSortType.SORT_DESCENDING) {
							return s2.compareTo(s1);
						} else {
							return s1.compareTo(s2);
						}
					}
				
				}
		);
		
		for (Entry<String, User> entryUser : this.users.entrySet()) {
			sortedUserIds.add(entryUser.getValue().getId());
		}
		
		return sortedUserIds.toArray(new String[0]);
	}

	// CHECKED
	@Override
	public String[] userMails(String userId) {
		Optional<User> user = user(userId);
		if (user(userId).isEmpty()) { return null; }
		return user.get().getMailList().toArray(new String[0]);
	}

	// CHECKED
	@Override
	public User[] users(EnumSortType sortType) {
		
		Set<User> sortedUsers = new TreeSet<User>(
				new Comparator<User>() {

					@Override
					public int compare(User o1, User o2) {
						// o1, o2 ordine alfabetico | o2, o1 ordine alf invertito
						if (sortType == EnumSortType.SORT_DESCENDING) {
							return o2.compareTo(o1);
						} else {
							return o1.compareTo(o2);
						}
					}
				
				}
		);
		
		// Aggiungo gli utenti al TreeSet con ordinamento variabile in base al parametro
		for (Entry<String, User> user : this.users.entrySet()) {
			sortedUsers.add(user.getValue());
		}
		
		return sortedUsers.toArray(new User[0]);
	}
	
	// CHECKED
	@Override
	public User firstUser() {
		User[] users = users(EnumSortType.SORT_ASCENDING);
		return users.length > 0
				? users[0]
				: null;
	}

	// CHECKED
	@Override
	public User lastUser() {
		User[] users = users(EnumSortType.SORT_ASCENDING);
		return users.length > 0
				? users[users.length - 1]
				: null;
	}

	// CHECKED
	@Override
	public User[] firstUsers(int numUsers) {
		return firstArrayElements(numUsers, users(EnumSortType.SORT_ASCENDING));
	}

	@Override
	public User[] lastUsers(int numUsers) {
		// TODO Auto-generated method stub
		return null;
	}

	// CHECKED
	@Override
	public String[] discardedRows() {
		return this.discardedRows.toArray(new String[0]);
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
	
	// CHECKED
	// Questo metodo analizza le righe del file e richiama i metodi per il salvataggio
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
			Optional<String> userId = Optional.empty();
			Optional<String> userEmail = Optional.empty();
			Optional<String> userName = Optional.empty();
			Optional<String> userLastname = Optional.empty();
			Optional<String> userAddress = Optional.empty();
			
			// Dato che la riga non è vuota, salvo lo userId
			userId = Optional.of(sc.next());
			
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
				
				userEmail = Optional.of(secondToken);
				
			/* Altrimenti se c'è un terzo token vuol dire che il secondo
			 * è il nome, e il terzo è il cognome */
			} else if (sc.hasNext()) {
				userName = Optional.of(secondToken);
				userLastname = Optional.of(sc.next());

				// Se c'è il quarto token salvo l'indirizzo dell'utente, altrimenti scarto riga
				if (sc.hasNext()) {
					userAddress = Optional.of(sc.nextLine().trim());
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
			
			// Salvo l'utente
			storeIdentity(userId, userEmail, userName, userLastname, userAddress);
			
			sc.close();
			
		}
		
	}
	
	// CHECKED
	// Questo metodo si occupa di salvare i dati dell'utente ricavati dalla riga
	private void storeIdentity(Optional<String> userId,
							   Optional<String> userEmail,
							   Optional<String> userName,
							   Optional<String> userLastname,
							   Optional<String> userAddress) {
		
		String userIdStr = userId.orElse(null);
		String userEmailStr = userEmail.orElse(null);
		String userNameStr = userName.orElse(null);
		String userLastnameStr = userLastname.orElse(null);
		String userAddressStr = userAddress.orElse(null);
	    
		addUser(userIdStr, userNameStr, userLastnameStr, userAddressStr);

		// Se la mail non è vuota, la aggiungo
		if (userEmailStr != null) {
			addMail(userIdStr, userEmailStr);
		}
	}
	
	// CHECKED
	// Questa funzione ritorna un valore booleano, true se la email è formattata correttamente, altrimenti false.
	public boolean emailCheck(String email) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,6}$";

	   // Blocco le doppie estensioni come .it.it
	   if (email.matches(".*\\.[a-zA-Z]{2,6}\\.[a-zA-Z]{2,6}$")) { return false; }
	   Pattern p = Pattern.compile(regex);
	   return p.matcher(email).matches();
	}
	
	// CHECKED
	// Questo metodo aggiunge alla lista di righe scartate quella passata come parametro.
	private void discardRow(String row) {
		this.discardedRows.add(row);
	}

	/* Questo metodo ritorna i primi elementi di una lista.
	 * I parametri richiesti sono 2, il numero di elementi da estrarre
	 * e l'array da cui estrarli. */
	private <T> T[] firstArrayElements(int numEl, T[] arr) {
		if (numEl > arr.length) {
            numEl = arr.length; // Per evitare IndexOutOfBoundsException
        }
		
//		Il metodo Arrays.copyOf(arr, numEl):
//		Se numEl è minore o uguale alla lunghezza di arr:
//			→ Copia i primi numEl elementi e restituisce un nuovo array di quella lunghezza.
//		Se numEl è maggiore della lunghezza di arr
//			→ Copia tutti gli elementi disponibili e riempie il resto con null (per i tipi reference) o con il valore di default (per i tipi primitivi, es. 0 per int).
        return Arrays.copyOf(arr, numEl);
	}
}






























