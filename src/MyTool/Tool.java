package MyTool;

import Data.CD;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tool {

    private static final Scanner sc = new Scanner(System.in);

    public static void loadCDfromFile(ArrayList<CD> file, String CDfile){
        try {
            InputStream fis = new FileInputStream(CDfile);
            DataInputStream dis = new DataInputStream(fis);
            while (dis.available() > 0) {
                String ID = dis.readUTF();
                String Collection_name = dis.readUTF();
                String type = dis.readUTF();
                String title = dis.readUTF();
                float price = dis.readFloat();
                int year = dis.readInt();
                file.add(new CD(ID, Collection_name, type, title, price, year));
            }
            dis.close();
            fis.close();
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

    public static String getCollection(String inputMessage) {
        String input = "";
        boolean check = true;
        do {
            System.out.print(inputMessage);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Not blank or empty. Please input again");
                check = true;
            } else if (input.equalsIgnoreCase("game")) {
                input = "game";
                check = false;
            } else if (input.equalsIgnoreCase("movie")) {
                input = "movie";
                check = false;
            } else if (input.equalsIgnoreCase("music")) {
                input = "music";
                check = false;
            } else {
                System.out.println("Input must be in 3 collection (game/music/movie)");
            }
        } while (check);
        return input.toUpperCase();
    }

    public static String getType(String inputMessage) {
        String input = "";
        boolean check = true;
        do {
            System.out.print(inputMessage);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Not blank or empty. Please input again");
                check = true;
            } else if (input.equalsIgnoreCase("audio")) {
                input = "audio";
                check = false;
            } else if (input.equalsIgnoreCase("video")) {
                input = "video";
                check = false;
            } else {
                System.out.println("Input must be in 2 Type (audio/video)");
            }
        } while (check);
        return input.toUpperCase();
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
                } else {
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
                } else {
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

    public static void writeFileAppend(String filename, ArrayList<CD> list) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f, true);
            DataOutputStream dos = new DataOutputStream(fos);
            for (int i = 0; i < list.size(); i++) {
                dos.writeUTF(list.get(i).getID());
                dos.writeUTF(list.get(i).getCollection_name());
                dos.writeUTF(list.get(i).getType());
                dos.writeUTF(list.get(i).getTitle());
                dos.writeFloat(list.get(i).getUnit_price());
                dos.writeInt(list.get(i).getYear());
            }
            dos.flush();
            dos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void writeFile(String filename, ArrayList<CD> list) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            if (f.exists()) {
//                f.delete();
            }
            FileOutputStream fos = new FileOutputStream(f);
            DataOutputStream dos = new DataOutputStream(fos);
            for (int i = 0; i < list.size(); i++) {
                dos.writeUTF(list.get(i).getID());
                dos.writeUTF(list.get(i).getCollection_name());
                dos.writeUTF(list.get(i).getType());
                dos.writeUTF(list.get(i).getTitle());
                dos.writeFloat(list.get(i).getUnit_price());
                dos.writeInt(list.get(i).getYear());
            }
            dos.flush();
            dos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
