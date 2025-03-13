package esercizi;

import java.util.Optional;

public interface Account<T> {
	
	/* Inserisce un utente, true se inserimento effettuato */
	boolean addUser(String idUser, String nome, String Cognome, String indirizzo);
	
	/* Inserisce una mail utente, true se inserimento effettuato */
	boolean addMail(String idUser, String mail);
	
	/* Restituisce true se l'utente esiste */
	boolean existsUser(String idUser);
	
	/* Restituisce true se l'utente ha almeno una mail */
	boolean userHasMail(String idUser);
	
	/* Restituisce l'utente con l'id fornito */
	Optional<T> user(String idUser);
	
	/* Restituisce tutti gli idUser sortati */
	String[] idUsers(EnumSortType sortType);
	
	/* Restituisce tutte le mail dell'utente */
	String[] userMails(String idUser);
	
	/* Restituisce tutti gli oggetti utente sortati (nome, cognome) */ 
	T[] users(EnumSortType sortType);
	
	/* Restituisce il primo utente (sono ordinati per nome, cognome) */ 
	T firstUser();
	
	/* Restituisce l'ultimo utente (sono ordinati per nome, cognome) */ 
	T lastUser();
	
	/* Restituisce i primi numUsers utenti (sono ordinati per nome, cognome) */
	T[] firstUsers(int numUsers);
	
	/* Restituisce gli ultimi numUsers utenti (sono ordinati per nome, cognome) */
	T[] lastUsers(int numUsers);
	
	/* Restituisce tutte le righe scartate, perchè doppie o errate */
	String[] discardedRows();
	
	/* Restituisce tutte le mail in ordine ascendente */
	String[] allMails();
}















