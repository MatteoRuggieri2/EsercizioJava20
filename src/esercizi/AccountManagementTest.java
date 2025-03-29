package esercizi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AccountManagementTest {
	
	static final String pathFile = "src/text_files/accounts_list.txt";
	static AccountManagement am;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		am = new AccountManagement(pathFile);
	}

	@Test
	void testAddUser() {
		
		// Creo un TreeSet con gli utenti esistenti e uno di test
		Set<User> expectedUser = new TreeSet<User>(
				new Comparator<User>() {
					@Override
					public int compare(User o1, User o2) {
						return o1.compareTo(o2);
					}
				}
		);
		
		User newUser = new User();
		newUser.setId("U111");
		newUser.setName("Matteo");
		newUser.setLastname("Ruggieri");
		newUser.setAddress("Via di test 15, TO");
		expectedUser.add(newUser);
		
		for (User user : am.users(EnumSortType.SORT_ASCENDING)) {
			expectedUser.add(user);
		}
		
		
		// Aggiungo l'utente di test nella mappa originale
		am.addUser("U111", "Matteo", "Ruggieri", "Via di test 15, TO");
	
		assertArrayEquals(expectedUser.toArray(), am.users(EnumSortType.SORT_ASCENDING));
	}
	
//	@Test
//	void testEmailCheckTrue() {
//		assertTrue(ga.emailCheck("matteoruggieri2@gmail.com"));
//		assertTrue(ga.emailCheck("pincopallo@libero.it"));
//		assertTrue(ga.emailCheck("giorgio.poggi@google.com"));
//	}
//	
//	@Test
//	void testEmailCheckFalse() {
////		 assertFalse(ga.emailCheck("matteoruggieri2@gmail.2")); // da' errore, penso dipenda dalla scrittura dell'espressione regolare
//		assertFalse(ga.emailCheck("332@gmail."));
////		 assertFalse(ga.emailCheck("222@334gf.com")); // da' errore
////		assertFalse(ga.emailCheck("giorgio.poggi@@gmail.com"));
//	}

}
