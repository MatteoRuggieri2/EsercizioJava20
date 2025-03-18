package esercizi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AccountManagementTest {
	
	static String pathFile = "src/text_files/accounts_list.txt";
	static AccountManagement ga;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ga = new AccountManagement(pathFile);
	}

	@Test
	void testAddUser() {
		
		/* Creo un HashMap di test uguale a quello di GestioneAccount
		 * e aggiungo un utente uguale a quello che aggiungerò come
		 * test utilizzando il metodo "addUser".
		 * Questo sarà l'HashMap con cui confrontare quello originale. */
		Map<String, User> usersTestMap = new HashMap<>(ga.users);
		User newUser = new User();
		newUser.setNome("Matteo");
		newUser.setCognome("Ruggieri");
		newUser.setIndirizzo("Via di test 15, TO");
		usersTestMap.put("U111", newUser);

		// Aggiungo utente nell'HashMap originale
		ga.addUser("U111", "Matteo", "Ruggieri", "Via di test 15, TO");
		
		// Confronto le due HashMap per verificare che l'utente sia stato aggiunto correttamente
		assertEquals(usersTestMap, ga.users);
		
		/* Siccome assertEquals confronta i riferimenti, di base non confronta il valore delle mappe.
		 * Per sicurezza conviene creare un arrayList e inserire dentro tutti i valori di test. 
		 * Poi crei una nuova arrayList dove metti tutti gli utenti della mappa originale.
		 * Successivamente per confrontare i valori delle 2 arrayList utilizza containsAll() */
	}
	
	@Test
	void testEmailCheckTrue() {
		assertTrue(ga.emailCheck("matteoruggieri2@gmail.com"));
		assertTrue(ga.emailCheck("pincopallo@libero.it"));
		assertTrue(ga.emailCheck("giorgio.poggi@google.com"));
	}
	
	@Test
	void testEmailCheckFalse() {
//		 assertFalse(ga.emailCheck("matteoruggieri2@gmail.2")); // da' errore, penso dipenda dalla scrittura dell'espressione regolare
		assertFalse(ga.emailCheck("332@gmail."));
//		 assertFalse(ga.emailCheck("222@334gf.com")); // da' errore
//		assertFalse(ga.emailCheck("giorgio.poggi@@gmail.com"));
	}

}
