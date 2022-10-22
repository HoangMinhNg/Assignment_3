package Data;

import java.io.Serializable;

public class CD implements Serializable, Comparable<CD> {

    private String ID;
    private String collection_name;
    private String type;
    private String title;
    private float unit_price;
    private int year;

    public static final String SEPERATOR = ",";

    public CD(String ID, String collection_name, String type, String title, float unit_price, int year) {
        this.ID = ID;
        this.collection_name = collection_name;
        this.type = type;
        this.title = title;
        this.unit_price = unit_price;
        this.year = year;
    }

    public String getCollection_name() {
        return collection_name;
    }

    public void setCollection_name(String collection_name) {
        this.collection_name = collection_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void showInfor() {
        System.out.printf("|%-8s|%-15s|%-10s|%-11s|%-11.2f|%-10d|\n", ID, collection_name, type, title, unit_price, year);
    }

    @Override
    public int compareTo(CD o) {
        return this.title.compareToIgnoreCase(o.title);
    }
}
