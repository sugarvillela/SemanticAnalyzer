package datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TextSourceFile extends TextSource{
    private final String fileName;
    private Scanner input;

    public TextSourceFile(String fileName ){
        this.fileName = fileName;
        //System.out.println("TextSourceFile construct: " + fileName);
    }

    @Override
    public final void onCreate(){
        this.row = -1;
        this.text = "";
        this.prev = "";
        try{
            this.input = new Scanner( new File(fileName) );
            this.good = true;
            this.done = false;
            this.next();             // internal state one row ahead of output
        }
        catch ( FileNotFoundException e ){
            System.out.println(e);
            this.good = false;
            this.done = true;
        }
    }

    @Override
    public final void onQuit(){ // call when program/task is finished
        this.input.close();
    }

    @Override
    public String next(){
        this.prev = this.text;
        row++;
        try{
            this.text = input.nextLine();
        }
        catch ( NoSuchElementException | IllegalStateException e ){
            done = true;
        }
        return this.prev;
    }

    @Override
    public ArrayList<String> toList() {
        this.rewind();
        ArrayList<String> out = new ArrayList<>();
        while(this.hasNext()){
            out.add(this.next());
        }
        return out;
    }

    @Override
    public void toTextFile() {

    }

    @Override
    public String toString(){
        return String.join("\n", this.toList());
    }

}
