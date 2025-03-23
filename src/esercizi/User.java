package esercizi;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Implementa il Comparator o il Comparable per avere la funzione compareTo per il sort
// In genere crea sempre le funzioni toString e hashCode, equals, Source > Generate...
public class User implements Comparable<User> {
	
	String id;
	
	String name;
	
	String lastname;
	
	String address;
	
	Set<String> mailList = new HashSet<String>();
	
	

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
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
	
	
	
	/* Questa funzione (creata in "Source > Generate hashCode() e equals()",
	serve per generare un codice hash che rispecchi lo stato dell'oggetto su
	cui è stato usato. */
	@Override
	public int hashCode() {
		return Objects.hash(lastname, id, address, mailList, name);
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
		return Objects.equals(lastname, other.lastname) && Objects.equals(id, other.id)
				&& Objects.equals(address, other.address) && Objects.equals(mailList, other.mailList)
				&& Objects.equals(name, other.name);
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
	    int cmp = this.lastname.toLowerCase().compareTo(o.lastname.toLowerCase());
	    if (cmp == 0) {
	        cmp = this.name.toLowerCase().compareTo(o.name.toLowerCase());
	        if (cmp == 0) {
	            return this.id.toLowerCase().compareTo(o.id.toLowerCase());
	        }
	    }
	    return cmp;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id 
				+ ", name=" + name 
				+ ", lastname=" + lastname 
				+ ", address=" + address 
				+ ", mailList=" + mailList 
				+ "]";
	}
	
	
	
}

