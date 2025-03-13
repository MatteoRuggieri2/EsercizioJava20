# Generics, Optionals

## AccountManagement

L'obiettivo di questo esercizio è l'utilizzo di **Generics**, **Collections**, **Scanner**, **classi anonime**, **Comparator** e **Optional**.

Creare la classe `AccountManagement` che implementa l'interfaccia `Account`, con relativo JUnit di test `AccountManagementTest`.

All'interno della classe `AccountManagement` è presente il seguente costruttore:

```java
class AccountManagement implements Account {
    AccountManagement(String fileName)
}
```

Creare la classe `User`, utile per modellare un utente univoco, che gestisce informazioni di tipo String come **userId**, **name**, **surname**, **mail**.
La classe `User`, per gestire l’ordinamento, deve implementare `hashCode()`, `equals()` e le interfacce `Comparator` e `Comparable`.

Utilizzare l’enumerazione `EnumSortType` per le operazioni di ordinamento.

### Output

Dopo aver letto il file di testo, eseguire i metodi all'interno dell'interface `Account`.

### Struttura file

Il **file di testo** contiene informazioni per ogni _userId_ (che deve essere univoco).
Il file, per ogni riga, può contenere informazioni diverse:

- Id, Nome, Cognome, Indirizzo
- Id, Mail

**Esempio struttura file**
utente1 nome cognome indirizzo
utente2 nome cognome indirizzo
utenteN nomeN cognomeN indirizzoN
...
utenteN mail
utenteN mail

**Requisiti file**

Le righe del file, per ogni utente, devono contenere una sola riga con nome, cognome e indirizzo. **Non sono ammesse righe duplicate**.

Righe in generale **errate** o **duplicate**, vanno scartate e memorizzate per essere successivamente visualizzate.

Per lo stesso utente possono essere presenti una o più righe con email diverse.

### Consigli

La classe `AccountManagement` e l’interface relativa utilizzano i **Generics** per dichiarare parametricamente la classe `User`.

Gli _utenti_ e le _mail_ vengono caricati nelle strutture dati all’istanziazione di `AccountManagement`.
I metodi `addUser()` e `addMail()` permetteranno di inserire ulteriori utenti e mail.

## Files

**EnumSortType.java**

```java
enum EnumSortType {
    SORT_ASCENDING,
    SORT_DESCENDING,
}
```

**Account.java**

```java
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
```

**accounts_list.txt**

```text
U001 Alberto Gabbai Via Servais 16/A Torino
U056 carlo.navone@libero.it
U056 Carlo Navone Via exilles 12
U001 alberto.gabbai@libero.it
U056 carlo.navone@hotmail.com
U025
U026 Giusi
U027 Giusi Ferrero
U033 Giampietro Zedda Via Po 20 Torino
U033 giampietro.zedda@libero.it.it
U020 Giorgio Poggi Via San Massimo 3 Torino
U020 giorgio.poggi@libero.it
U020 giorgio.poggi@google.com
U020 giorgio.poggi@spformazione.com

U022 Giorgio Palandri Via Livorno 25/a Torino
```
