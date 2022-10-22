package UI;

import Data.CDList;
import MyTool.Tool;

/**
 *
 * @author Admin
 */
public class CDHouse {

    public static void main(String[] args) {
        boolean confirm = false;
        boolean tryAgain = false;
        boolean respone = false;
        CDList cList = new CDList();
        cList.loadFile();
        int choice = 0;
        do {
            Menu menu = new Menu("Welcome to CD House Management");
            menu.addNewOption("     1. Add CD to the catalog");
            menu.addNewOption("     2. Search CD by CD title");
            menu.addNewOption("     3. Display the catalog");
            menu.addNewOption("     4. Update CD");
            menu.addNewOption("     5. Save CD to file");
            menu.addNewOption("     6. Print list CDs from file");
            menu.addNewOption("     7. Quit");
            do {
                menu.printMenu();
                choice = menu.getChoice();
                switch (choice) {
                    case 1:
                        do {
                            cList.addCDtocatalog();
                            cList.setChange(true);
                            tryAgain = Tool.confirmYesNo("Do you want to try again(Y/N)? ");
                        } while (tryAgain);
                        break;
                    case 2:
                        do {
                            cList.searchCD();
                            cList.setChange(true);
                            tryAgain = Tool.confirmYesNo("Do you want to try again(Y/N)? ");
                        } while (tryAgain);
                        break;
                    case 3:
                        cList.displayCatalog();
                        break;
                    case 4:
                        do {
                            int choice2 = 0;
                            Menu menu2 = new Menu("Update CD function");
                            menu2.addNewOption("        1. Update CD information");
                            menu2.addNewOption("        2. Delete CD information");
                            menu2.addNewOption("        3. Quit");
                            do {
                                menu2.printMenu();
                                choice2 = menu2.getChoice();
                                switch (choice2) {
                                    case 1:
                                        cList.updateCD();
                                        cList.setChange(true);
                                        tryAgain = true;
                                        break;
                                    case 2:
                                        cList.deleteCD();
                                        cList.setChange(true);
                                        tryAgain = true;
                                        break;
                                    default:
                                        tryAgain = false;
                                }
                            } while (respone);
                        } while (tryAgain);
                        break;
                    case 5:
                        cList.saveToFile();
                        break;
                    case 6:
                        cList.printFromFile();
                        break;
                    case 7:
                        if (cList.isChange() == true) {
                            respone = Tool.confirmYesNo("Data is changed. Write to file? (Y/N)");
                            if (respone == true) {
                                cList.saveToFile();
                            }
                            System.out.println("Bye,bye.See you next time.");
                            confirm = false;
                        }
                        break;
                }
            } while (choice > 0 && choice < 7);
        } while (confirm);
    }
}
