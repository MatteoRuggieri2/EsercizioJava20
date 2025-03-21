package esercizi;

import java.util.Objects;
import java.util.Set;

// Implementa il Comparator o il Comparable per avere la funzione compareTo per il sort
// In genere crea sempre le funzioni toString e hashCode, equals, Source > Generate...
public class User implements Comparable<User> {
	
	String idUser;
	
	String nome;
	
	String cognome;
	
	String indirizzo;
	
	Set<String> mailList;
	
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public Set<String> getMailList() {
		return mailList;
	}
	
	public void setMailList(Set<String> mailList) {
		this.mailList = mailList;
	}
	
	public void addMailToList(String newMail) {
		if (!this.mailList.contains(newMail)) {
			this.mailList.add(newMail);
		}
	}
	
	public String getIdUser() {
		return idUser;
	}
	
	
	
	/* Questa funzione (creata in "Source > Generate hashCode() e equals()",
	serve per generare un codice hash che rispecchi lo stato dell'oggetto su
	cui è stato usato. */
	@Override
	public int hashCode() {
		return Objects.hash(cognome, idUser, indirizzo, mailList, nome);
	}
	
	/* Questa funzione, creata insieme a quella di sopra, serve per comparare
	 * l'oggetto passato come argomento con quello corrente. Torna true se
	 * l'oggetto ha gli stessi attributi, se è diverso da null e se appartiene
	 * alla stessa classe, altrimenti se anche solo 1 cosa non corrisponde torna false. */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(idUser, other.idUser)
				&& Objects.equals(indirizzo, other.indirizzo) && Objects.equals(mailList, other.mailList)
				&& Objects.equals(nome, other.nome);
	}
	

	/* Viene usato per ordinare gli oggetti della classe User in una struttura
	 * ordinata come TreeSet o Collections.sort().
	 * 
	 * Return:
	 * Un numero negativo se l'oggetto corrente è "minore" di o.
     * Zero se sono uguali.
     * Un numero positivo se è "maggiore" di o. */
	@Override
	public int compareTo(User o) {
	    int cmp = this.cognome.toLowerCase().compareTo(o.cognome.toLowerCase());
	    if (cmp == 0) {
	        cmp = this.nome.toLowerCase().compareTo(o.nome.toLowerCase());
	        if (cmp == 0) {
	            return this.idUser.toLowerCase().compareTo(o.idUser.toLowerCase());
	        }
	    }
	    return cmp;
	}
	
	
	
}

