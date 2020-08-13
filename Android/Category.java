package org.techtown.songthemarket;

public class Category {
    String category;
    int c_id;
    String c_name;
    String c_sname;
    int c_pid;

    //get
    public String getCategory() {
        return category;
    }

    public int getC_id() {
        return c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public String getC_sname() {
        return c_sname;
    }

    public int getC_pid() {
        return c_pid;
    }

    //set
    public void setCategory(String category) {
        this.category = category;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public void setC_sname(String c_sname) {
        this.c_sname = c_sname;
    }

    public void setC_pid(int c_pid) {
        this.c_pid = c_pid;
    }
}
