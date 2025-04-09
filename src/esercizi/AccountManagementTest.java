package esercizi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountManagementTest {
	
	static final String pathFile = "src/text_files/accounts_list.txt";
	static AccountManagement am;
	static User testUser;
	static User user1;
	static User user2;
	static User user3;
	static User user4;
	static User user5;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		am = new AccountManagement(pathFile);
		
		testUser = new User("U111", "Matteo", "Ruggieri", "Via di test 15, TO");
		
		user1 = new User("U001", "Alberto", "Gabbai", "Via Servais 16/A Torino");
		user2 = new User("U020", "Giorgio", "Poggi", "Via San Massimo 3 Torino");
		user3 = new User("U022", "Giorgio", "Palandri", "Via Livorno 25/a Torino");
		user4 = new User("U033", "Giampietro", "Zedda", "Via Po 20 Torino");
		user5 = new User("U056", "Carlo", "Navone", "Via exilles 12");
		
		user1.addMailToList("alberto.gabbai@libero.it");
		user2.addMailToList("giorgio.poggi@libero.it");
		user2.addMailToList("giorgio.poggi@google.com");
		user2.addMailToList("giorgio.poggi@spformazione.com");
		user5.addMailToList("carlo.navone@libero.it");
		user5.addMailToList("carlo.navone@hotmail.com");
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
		
		List<User> usersList = new ArrayList<User>();
		usersList.add(user1);
		usersList.add(user2);
		usersList.add(user3);
		usersList.add(user4);
		usersList.add(user5);
		
		Set<User> expectedUserASC = new TreeSet<User>(
				new Comparator<User>() {
					@Override
					public int compare(User o1, User o2) {
						return o1.compareTo(o2);
					}
				}
		);
		
		Set<User> expectedUserDESC = new TreeSet<User>(
				new Comparator<User>() {
					@Override
					public int compare(User o1, User o2) {
						return o2.compareTo(o1);
					}
				}
		);
		
		expectedUserASC.addAll(usersList);
		expectedUserDESC.addAll(usersList);
		
		assertArrayEquals(expectedUserASC.toArray(new User[0]), am.users(EnumSortType.SORT_ASCENDING));
		assertArrayEquals(expectedUserDESC.toArray(new User[0]), am.users(EnumSortType.SORT_DESCENDING));
		
	}
	
	@Test
	void testFirstUser() {
		User expectedUser = user1;
		assertEquals(expectedUser, am.firstUser());
	}
	
	@Test
	void testAllMails() {
		String[] expectedEmails = {
		    "alberto.gabbai@libero.it",
		    "carlo.navone@hotmail.com",
		    "carlo.navone@libero.it",
		    "giorgio.poggi@google.com",
		    "giorgio.poggi@libero.it",
		    "giorgio.poggi@spformazione.com",
		};
		
		assertArrayEquals(expectedEmails, am.allMails());
	}


}
