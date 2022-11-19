package example;

import java.util.List;
import java.util.Scanner;

public class ScannerBuilder {

    private final List<String> menuItems;

    public ScannerBuilder(List<String> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * Author: Niklas Wiemer
     * Gibt das Menu aus
     */

    private void printMenu() {
        System.out.println("** Menu **");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println("[" + i + "] - " + menuItems.get(i));
        }
    }

    /**
     * Author: Niklas Wiemer
     * Verwaltet die Menu eingaben und kontrolliert ob die eingegebenen Daten korrekt sind
     * @return
     */

    public int start() {
        Scanner scanner = new Scanner(System.in);
        int eingabe;
        printMenu();

        do {
            System.out.print("Eingabe: ");
            try {
                eingabe = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Ungültige Eingabe.");
                scanner.next();
                eingabe = -1;
            }
            if (eingabe >= 0 && eingabe < menuItems.size()) {
                return eingabe;
            }
        } while (true);
    }

}
