package UI;

import java.util.ArrayList;
import dao.I_menu;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Menu implements I_menu {

    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList<>();

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    @Override
    public void addNewOption(String newOption) {
        optionList.add(newOption);
    }

    @Override
    public void printMenu() {
        System.out.println("\n");
        System.out.println("Welcome to " + menuTitle);
        for (String x : optionList) {
            System.out.println(x);
        }

    }

    @Override
    public int getChoice() {
        int maxOption = optionList.size();
        String inputMsg = "Choose [1.." + maxOption + "]: ";
        return getInt(inputMsg, 1, maxOption);
    }

    public static int getInt(String message, int min, int max) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        int number = 0;
        do {
            try {
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Input number in range [" + min + "," + max + "]");
            }
        } while (check || number > max || number < min);
        return number;
    }
}
