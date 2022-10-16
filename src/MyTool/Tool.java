package MyTool;

import Data.CD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tool {

    private static final Scanner sc = new Scanner(System.in);

    public static void loadCDfromFile(ArrayList file, String CDfile) {
        try {
            FileReader fr = new FileReader(CDfile);
            BufferedReader br = new BufferedReader(fr);
            String details;
            while ((details = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, CD.SEPERATOR);
                String ID = stk.nextToken();
                String Collection_name = stk.nextToken();
                String type = stk.nextToken();
                String title = stk.nextToken();
                float price = Float.parseFloat(stk.nextToken());
                int year = Integer.parseInt(stk.nextToken());
                file.add(new CD(ID, Collection_name, type, title, price, year));
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    public static String readFormat(String inputMessage, String errorMessage, String format) {
        String input;
        boolean valid;
        while (true) {
            System.out.print(inputMessage);
            input = sc.nextLine();
            valid = input.matches(format);
            if (input.isEmpty()) {
                System.out.println("Not blank or empty");
            } else if (valid == false) {
                System.out.println(errorMessage);
            } else {
                return input.toUpperCase();
            }
        }
    }

    public static String getString(String inputMessage) {
        String input = "";
        while (true) {
            System.out.print(inputMessage);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Not blank or empty. Please input again");
            } else {
                return input.toUpperCase();
            }
        }
    }

    public static float getFloat(String inputMessage) {
        float input = 0;
        boolean check = true;
        do {
            try {
                System.out.print(inputMessage);
                input = Float.parseFloat(sc.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Input number greater than 0");
            }
        } while (check || input < 0);
        return input;
    }

    public static int getPossitiveInt(String inputMessage) {
        int input = 0;
        boolean check = true;
        do {
            try {
                System.out.print(inputMessage);
                input = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Input number greater than 0");
            }
        } while (check || input <= 0);
        return input;
    }

    public static String getUpdateString(String inputMessage, String oldValue) {
        String input = "";
        boolean check = true;
        do {
            System.out.print(inputMessage + " (Enter for omitting):");
            String tmp = sc.nextLine();
            if (tmp.isEmpty()) {
                input = oldValue;
                check = false;
            } else {
                input = tmp;
                check = false;
            }
        } while (check);
        return input.toUpperCase();
    }

    public static float getUpdateFloat(String inputMessage, float oldValue) {
        float input = 0;
        boolean check = true;
        do {
            try {
                System.out.print(inputMessage + " (Enter for omitting):");
                String tmp = sc.nextLine();
                if (tmp.isEmpty() || tmp.length() == 0) {
                    input = oldValue;
                    check = false;
                }else{
                    input = Float.parseFloat(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number greater than 0");
            }
        } while (check || input < 0);
        return input;
    }

    public static int getUpdateInt(String inputMessage, int oldValue) {
        int input = 0;
        boolean check = true;
        do {
            try {
                System.out.print(inputMessage + " (Enter for omitting):");
                String tmp = sc.nextLine();
                if (tmp.isEmpty() || tmp.length() == 0) {
                    input = oldValue;
                    check = false;
                }else{
                    input = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number greater than 0");
            }
        } while (check || input < 0);
        return input;
    }

    public static boolean confirmYesNo(String message) {
        boolean result = false;
        System.out.print(message);
        String confirm = sc.nextLine();
        if (confirm.toLowerCase().startsWith("y")) {
            result = true;
        }
        return result;
    }

    public static void writeFileAppend(String filename, ArrayList list) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f, true);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i).toString());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void writeFile(String filename, ArrayList list) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            if (f.exists()) {
//                f.delete();
            }
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i).toString());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
