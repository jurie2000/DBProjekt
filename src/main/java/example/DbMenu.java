package example;

import example.database.DbConnection;
import example.database.DbOperation;

import java.sql.SQLException;
import java.util.*;

public class DbMenu {

    /**
     * Author: Niklas Wiemer
     * Gibt das Hauptmenu aus
     */
    public void mainMenu() {
        String[] menuItems = {"Auslesen", "Einfügen", "Bearbeiten", "Löschen"};
        List<String> menuList = new LinkedList<>(Arrays.asList(menuItems));

        ScannerBuilder scannerBuilder = new ScannerBuilder(menuList);
        int value = scannerBuilder.start();

        switch (value) {
            case 0 -> auslesen();
            case 1 -> einfuegen();
            case 2 -> bearbeiten();
            case 3 -> loeschen();
        }
    }

    /**
     * Author: Justin Riedel
     * Verwaltet das auslesen von Daten
     */
    private void auslesen() {
        // Connection
        DbConnection dbConnection = new DbConnection("jdbc:oracle:thin:@172.22.112.100:1521:fbpool", "C##FBPOOL164", "oracle");
        DbOperation dbOperation = new DbOperation(dbConnection);

        // Tabelle auswählen
        List<String> tabellen = dbOperation.getAllAvailableTables();
        ScannerBuilder scannerBuilder = new ScannerBuilder(tabellen);
        int eingabe = scannerBuilder.start();
        String tabelle = tabellen.get(eingabe);

        // Tabellendaten holen
        List<Map<String, Object>> values;
        try {
            values = dbOperation.getTuple(tabelle);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Tabellendaten ausgeben
        System.out.println();
        System.out.println("** Ausgabe **");
        System.out.println();
        for (Map<String, Object> data : values) {
            for (String key : data.keySet()) {
                System.out.println(key + ": " + data.get(key));
            }
            System.out.println();
        }

        // Datenbank verbindung schließen
        dbConnection.disconnectFromDb();

        // Zurück zum Hauptmenu
        mainMenu();
    }

    /**
     * Author: Niklas Wiemer
     * Verwaltet das einfügen von Daten
     */
    private void einfuegen() {
        // Connection
        DbConnection dbConnection = new DbConnection("jdbc:oracle:thin:@172.22.112.100:1521:fbpool", "C##FBPOOL164", "oracle");
        DbOperation dbOperation = new DbOperation(dbConnection);

        // Tabelle auswählen
        List<String> tabellen = dbOperation.getAllAvailableTables();
        ScannerBuilder scannerBuilder = new ScannerBuilder(tabellen);
        int eingabe = scannerBuilder.start();
        String tabelle = tabellen.get(eingabe);

        // Alle Spalten der Tabelle holen
        List<String> columns;
        try {
            columns = dbOperation.getAllAvailableColumns(tabelle);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Werte einlesen
        List<Object> values = new LinkedList<>();

        System.out.println();
        System.out.println("** Werte eintragen **");
        System.out.println();
        for (String column : columns) {
            System.out.print(column + ": ");
            values.add(scanner.next());
        }

        // Eingelesene Werte in die Datenbank eintragen

        try {
            dbOperation.insertToDB(tabelle, values);
            System.out.println();
            System.out.println("Datensatz wurde erfolgreich eingetragen");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Wie es aussieht wurden ungültige Daten eingegeben.");
        }

        dbConnection.disconnectFromDb();

        // Zurück zum Hauptmenu
        mainMenu();
    }

    public void bearbeiten() {
        // Tabelle auswählen
        List<String> tabellen = new LinkedList<>(Arrays.asList("FAHRGAST", "MITARBEITER")); // ToDo Tabellen list
        ScannerBuilder scannerBuilder = new ScannerBuilder(tabellen);
        int eingabe = scannerBuilder.start();
        String tabelle = tabellen.get(eingabe);

        // ToDo Datensatz auswählen

        // ToDo Datenwert auswählen

        // Zurück zum Hauptmenu
        mainMenu();
    }


    /**
     * Author: Niklas Wiemer
     * Verwaltet das löschen von Daten
     */
    private void loeschen() {
        // Connection
        DbConnection dbConnection = new DbConnection("jdbc:oracle:thin:@172.22.112.100:1521:fbpool", "C##FBPOOL164", "oracle");
        DbOperation dbOperation = new DbOperation(dbConnection);

        // Tabelle auswählen
        List<String> tabellen = dbOperation.getAllAvailableTables();
        ScannerBuilder scannerBuilder = new ScannerBuilder(tabellen);
        int eingabe = scannerBuilder.start();
        String tabelle = tabellen.get(eingabe);

        // Tabellendaten holen
        List<Map<String, Object>> values;
        try {
            values = dbOperation.getTuple(tabelle);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Tabellendaten ausgeben
        System.out.println();
        System.out.println("** Ausgabe **");
        System.out.println();
        for (int i = 0; i < values.size(); i++) {
            System.out.println("** Datensatz ID [" + i + "] **");
            for (String key : values.get(i).keySet()) {
                System.out.println(key + ": " + values.get(i).get(key));
            }
            System.out.println();
        }

        Scanner scanner = new Scanner(System.in);
        int datensatzID;

        do {
            try {
                System.out.print("Eingabe: ");
                datensatzID = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Ungültiger Wert");
                scanner.next();
                datensatzID = -1;
            }
        } while (datensatzID < 0 || datensatzID >= values.size());

        try {
            dbOperation.deleteFromDB(tabelle, values.get(datensatzID));
            System.out.println();
            System.out.println("Datensatz wurde gelöscht.");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Datensatz konnte nicht gelöscht werden.");
        }

        // Datenbank verbindung schließen
        dbConnection.disconnectFromDb();

        // Zurück zum Hauptmenu
        mainMenu();
    }

}