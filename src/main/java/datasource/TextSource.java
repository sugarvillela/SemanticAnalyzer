package datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class TextSource {
    protected String text, prev;
    protected boolean good;
    protected boolean done;
    protected int row;

    public void onCreate(){}

    public void onQuit(){}

    public int getRow(){
        return this.row;
    }

    public void rewind(){
        this.onCreate();
    }

    public boolean hasNext(){
        return !this.done;
    }

    public boolean hasData(){
        return this.good;
    }

    public abstract String next();

    public abstract ArrayList<String> toList();

    public abstract void toTextFile();

    public String[] toArray(){
        ArrayList<String> list = this.toList();
        return list.toArray(new String[list.size()]);
    }
}
