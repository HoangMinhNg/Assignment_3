package Data;

import MyTool.Tool;
import java.util.ArrayList;
import java.util.Collections;

public class CDList {

    private static final String dataFile = "CD.txt";
    private final String ID_format = "C\\d{3}|c\\d{3}";
    private boolean change = false;

    private static final int MAX = 2;
    private static int num = 0;
    private static CD[] listadd = new CD[MAX];

    ArrayList<CD> filelist = new ArrayList<>();
    ArrayList<CD> newlist = new ArrayList<>();

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public void loadFile() {
        Tool.loadCDfromFile(filelist, dataFile);
    }

    private int checkID(String ID, ArrayList<CD> checkList) {
        if (checkList.isEmpty() && num == 0) {
            return -1;
        } else {
            for (int i = 0; i < checkList.size(); i++) {
                if (checkList.get(i).getID().equalsIgnoreCase(ID)) {
                    return i;
                }
            }
        }

        return -1;
    }

    private int checkTitle(String title, ArrayList<CD> checkList) {
        if (checkList.isEmpty() && num == 0) {
            return -1;
        } else {
            for (int i = 0; i < checkList.size(); i++) {
                if (checkList.get(i).getTitle().equalsIgnoreCase(title)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void addCDtocatalog() {
        if (num >= MAX) {
            System.out.println("List is full. Cannot add new CD");
            System.out.println("Please save list to file");
        } else {
            String ID;
            int index1, index2;
            do {
                ID = Tool.readFormat("Enter ID: ", "Wrong format. Input again!!!", ID_format);
                index1 = checkID(ID, filelist);
                index2 = checkID(ID, newlist);
                if (index1 >= 0 || index2 >= 0) {
                    System.out.println("ID is duplicated. Try again");
                }
            } while (index1 >= 0 || index2 >= 0);

            String collection_name = Tool.getString("Enter collection name: ");
            String type = Tool.getString("Enter type: ");
            String title;
            do {
                title = Tool.getString("Enter title: ");
                index1 = checkTitle(title, filelist);
                index2 = checkID(title, newlist);
                if (index1 >= 0 || index2 >= 0) {
                    System.out.println("Title is duplicated. Try again");
                }
            } while (index1 >= 0 || index2 >= 0);

            Float unit_price = Tool.getFloat("Enter price: ");
            int year = Tool.getPossitiveInt("Enter year: ");
            listadd[num] = new CD(ID, collection_name, type, title, unit_price, year);
            num++;
            newlist.add(new CD(ID, collection_name, type, title, unit_price, year));
            System.out.println("New CD has been added successfully");
        }
    }

    public void searchCD() {
        int count = 0;
        if (filelist.isEmpty()) {
            System.out.println("There's not thing in file");
            System.out.println("Please add new CD");
        } else {
            String input = Tool.getString("Enter CD's title to search: ");
            for (CD check : filelist) {
                if (check.getTitle().contains(input)) {
                    count++;
                }
            }
            if (count > 0) {
                System.out.println("|----------------------------------------------------------------------|");
                System.out.println("|   ID   |COLLECTION NAME|   TYPE   |   TITLE   |   PRICE   |   YEAR   |");
                System.out.println("|----------------------------------------------------------------------|");
                for (CD cd : filelist) {
                    if (cd.getTitle().contains(input)) {
                        cd.showInfor();
                    }
                }
                System.out.println("|----------------------------------------------------------------------|");
            }else{
                System.out.print("\n");
                System.out.println("Not found CD's information");
                System.out.print("\n");
            }
        }

    }

    public void displayCatalog() {
        if (num == 0) {
            System.out.println("Empty List.");
        } else {
            System.out.println("|----------------------------------------------------------------------|");
            System.out.println("|   ID   |COLLECTION NAME|   TYPE   |   TITLE   |   PRICE   |   YEAR   |");
            System.out.println("|----------------------------------------------------------------------|");
            for (int i = 0; i < num; i++) {
                listadd[i].showInfor();
            }
            System.out.println("|----------------------------------------------------------------------|");
        }
    }

    private void updateArray(int index, CD[] upList) {
        String newCollection_name = Tool.getUpdateString("Enter new CD's collection name", upList[index].getCollection_name());
        upList[index].setCollection_name(newCollection_name);

        String newType = Tool.getUpdateString("Enter new CD's type", upList[index].getType());
        upList[index].setType(newType);

        String title = Tool.getUpdateString("Enter new CD's title", upList[index].getTitle());
        upList[index].setTitle(title);

        float newUnit_price = Tool.getUpdateFloat("Enter new CD's price", upList[index].getUnit_price());
        upList[index].setUnit_price(newUnit_price);

        int newYear = Tool.getUpdateInt("Enter new CD's year publishing", upList[index].getYear());
        upList[index].setYear(newYear);

        System.out.println("CD's information has been updated successfully");
    }

    private void update(int index, ArrayList<CD> upList) {
        String newCollection_name = Tool.getUpdateString("Enter new CD's collection name", upList.get(index).getCollection_name());
        upList.get(index).setCollection_name(newCollection_name);

        String newType = Tool.getUpdateString("Enter new CD's type", upList.get(index).getType());
        upList.get(index).setType(newType);

        String title = Tool.getUpdateString("Enter new CD's title", upList.get(index).getTitle());
        upList.get(index).setTitle(title);

        float newUnit_price = Tool.getUpdateFloat("Enter new CD's price", upList.get(index).getUnit_price());
        upList.get(index).setUnit_price(newUnit_price);

        int newYear = Tool.getUpdateInt("Enter new CD's year publishing", upList.get(index).getYear());
        upList.get(index).setYear(newYear);

        System.out.println("CD's information has been updated successfully");
    }

    public void updateCD() {
        String ID = Tool.readFormat("Enter CD's ID to update: ", "Wrong format. Try again!!!", ID_format);
        int index1 = checkID(ID, filelist);
        int index2 = checkID(ID, newlist);
        if (index1 >= 0 && index2 < 0) {
            update(index1, filelist);
        } else if (index2 >= 0 && index1 < 0) {
            updateArray(index2, listadd);
        } else if (index1 < 0 && index2 < 0) {
            System.out.println("Not found CD " + ID.toUpperCase());
        }
    }

    public void deleteCD() {
        String ID = Tool.readFormat("Enter CD's ID to deleting: ", "Wrong format. Try again!!!", ID_format);
        int index1 = checkID(ID, filelist);
        int index2 = checkID(ID, newlist);
        if (index1 >= 0) {
            boolean confirm = Tool.confirmYesNo("Are you sure want to delete CD " + ID.toUpperCase());
            if (confirm) {
                filelist.remove(index1);
                System.out.println("CD's information has been deleted successfully");
            }
        } else if (index2 >= 0 && index2 < num) {
            boolean confirm = Tool.confirmYesNo("Are you sure want to delete CD " + ID.toUpperCase());
            if (confirm) {
                for (int i = index2; i < (num - 1); i++) {
                    listadd[i] = listadd[i + 1];
                }
                num--;
                System.out.println("CD's information has been deleted successfully");
            }
        } else {
            System.out.println("Not found CD's information to delete");
        }
    }

    public void saveToFile() {
        ArrayList<CD> listNoDup = new ArrayList<>();
        if (change) {
            for (CD product : newlist) {
                if (!listNoDup.contains(product)) {
                    listNoDup.add(product);
                }
            }
            listNoDup.removeAll(filelist);
            Tool.writeFile(dataFile, filelist);
            Tool.writeFileAppend(dataFile, listNoDup);

            change = false;
            filelist.addAll(listNoDup);
            newlist.clear();
            listadd = new CD[MAX];
            num = 0;
            System.out.println("Save to file successfully");
        }
    }

    public void printFromFile() {
        if (filelist.isEmpty()) {
            System.out.println("Empty List!!!");
        } else {
            System.out.println("|----------------------------------------------------------------------|");
            System.out.println("|   ID   |COLLECTION NAME|   TYPE   |   TITLE   |   PRICE   |   YEAR   |");
            System.out.println("|----------------------------------------------------------------------|");
            for (int i = 0; i < filelist.size(); i++) {
                Collections.sort(filelist);
                filelist.get(i).showInfor();
            }
            System.out.println("|--------|---------------|----------|-----------|-----------|----------|");
        }
        System.out.println("Total product: " + filelist.size());
    }
}
