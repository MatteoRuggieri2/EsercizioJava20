package esercizi;

import java.util.Optional;

public interface Account<T> {
    
    /* Inserisce un utente, true se inserimento effettuato */
    boolean addUser(String userId, String name, String surname, String address);
    
    /* Inserisce una mail utente, true se inserimento effettuato */
    boolean addMail(String userId, String mail);
    
    /* Restituisce true se l'utente esiste */
    boolean existsUser(String userId);
    
    /* Restituisce true se l'utente ha almeno una mail */
    boolean userHasMail(String userId);
    
    /* Restituisce l'utente con l'id fornito */
    Optional<T> user(String userId);
    
    /* Restituisce tutti gli userId sortati*/
    String[] userIds(EnumSortType sortType);
    
    /* Restituisce tutte le mail dell'utente */
    String[] userMails(String userId);
    
    /* Restituisce tutti gli oggetti utente sortati (name, surname) */
    T[] users(EnumSortType sortType);
    
    /* Restituisce il primo utente (sono ordinati per name, surname) */
    T firstUser();
    
    /* Restituisce l'uktimo utente (sono ordinati per name, surname) */
    T lastUser();
    
    /* Restituisce i primi numUsers utenti (sono ordinati per name, surname) */
    T[] firstUsers(int numUsers);
    
    /* Restituisce gli ultimi numUsers utenti (sono ordinati per name, surname) */
    T[] lastUsers(int numUsers);
    
    /* Restituisce tutte le righe scartate, perchè doppie o errate */
    String[] discardedRows();
    
    /* Restituisce tutte le mail in ordine ascendente */
    String[] allMails();
}















