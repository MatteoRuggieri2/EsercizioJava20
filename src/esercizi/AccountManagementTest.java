package esercizi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountManagementTest {
	
	static final String pathFile = "src/text_files/accounts_list.txt";
	static AccountManagement am;
	static User testUser;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		am = new AccountManagement(pathFile);
		
		testUser = new User();
		testUser.setId("U111");
		testUser.setName("Matteo");
		testUser.setLastname("Ruggieri");
		testUser.setAddress("Via di test 15, TO");
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
		
		expectedUser.add(testUser);
		
		for (User user : am.users(EnumSortType.SORT_ASCENDING)) {
			expectedUser.add(user);
		}
		
		// Aggiungo l'utente di test nella mappa originale
		am.addUser(testUser.getId(), testUser.getName(), testUser.getLastname(), testUser.getAddress());
	
		assertArrayEquals(expectedUser.toArray(), am.users(EnumSortType.SORT_ASCENDING));
	}
	
	@Test
	void testAddEmail() {
		String testId = "U111";
		String testMail = "matteoruggieri@test.com";
		am.addMail(testId, testMail);
		assertTrue(Arrays.asList(am.allMails()).contains(testMail));
	}
	
	@Test
	void testExistsUser() {
		assertTrue(am.existsUser("U056"));
		assertFalse(am.existsUser("U111"));
	}
	
	@Test
	void testUserHasMail() {
		assertTrue(am.userHasMail("U056"));
		assertFalse(am.userHasMail("U111"));
	}
	
	@Test
	void testFindUser() {
		am.addUser(testUser.getId(), testUser.getName(), testUser.getLastname(), testUser.getAddress());
		Optional<User> result = am.user(testUser.getId());
		assertEquals(testUser, result.get());
		
		/* Normalmente "assertEquals" confronta i riferimenti in
		 * memoria degli oggetti utilizzano equals(), ma in questo caso,
		 * nella classe User è stato sovrascritto il metodo equals
		 * che ora confronta il valore degli attributi. */
	}
	
	@Test
	void testUserIds() {
		String[] expectedUserIdsASC = { "U001", "U020", "U022", "U033", "U056" };
		String[] expectedUserIdsDESC = { "U056", "U033", "U022", "U020", "U001" };
		assertArrayEquals(expectedUserIdsASC, am.userIds(EnumSortType.SORT_ASCENDING));
		assertArrayEquals(expectedUserIdsDESC, am.userIds(EnumSortType.SORT_DESCENDING));
	}
	
	@Test
	void testUserMails() {
		String[] albertoGabbaiEmails = { "alberto.gabbai@libero.it" };
		String[] carloNavoneEmails = { "carlo.navone@hotmail.com", "carlo.navone@libero.it" }; // Ordinati in base al codice HASH
		assertArrayEquals(albertoGabbaiEmails, am.userMails("U001"));
		assertArrayEquals(carloNavoneEmails, am.userMails("U056"));
	}
	
	@Test
	void testUsers() {
		// Creo un array di utenti ordinati in Alf ASC
		// Creo un array di utenti ordindati in Alf DESC
		
		// Confronto il primo array
		// Confronto il secondo array
	}


}
