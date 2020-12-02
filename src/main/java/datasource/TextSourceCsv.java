package datasource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextSourceCsv extends TextSource{
    private final String fileName;
    private final TextSourceFile fin;
    private String colName;
    private int col;

    public TextSourceCsv(String fileName) {
        this.fileName = fileName;
        fin = new TextSourceFile(fileName);
    }
    public TextSourceCsv(String fileName, int col) {
        this(fileName);
        this.col = col;
    }

    @Override
    public void onCreate() {
        fin.onCreate();
    }

    @Override
    public void onQuit() {
        fin.onQuit();
    }

    @Override
    public int getRow() {
        return fin.getRow();
    }

    @Override
    public void rewind() {
        this.onCreate();
        colName = fin.next();
    }

    @Override
    public boolean hasNext() {
        return fin.hasNext();
    }

    @Override
    public boolean hasData() {
        return fin.hasData();
    }

    @Override
    public String next() {
        return fin.next().split(",")[col];
    }

    @Override
    public ArrayList<String> toList() {
        this.rewind();
        ArrayList<String> out = new ArrayList<>();
        int i = 0;
        while(this.hasNext()){
            out.add(this.next());
//            if(i >= 2){
//                break;
//            }
//            i++;
        }
        return out;
    }

    @Override
    public void toTextFile() {
        String textFileName = fileName.substring(0, fileName.length() -3) + "txt";
        System.out.println(textFileName);
        this.rewind();
        try(
                BufferedWriter file = new BufferedWriter(new FileWriter(textFileName))
        ){
            while(this.hasNext()){
                file.write(this.next());
                file.newLine();
            }
            file.close();
        }
        catch(IOException e){
            System.out.println("GenFileUtil: exception = " + e);
            System.exit(-1);
        }
    }

    public void setCol(int col){
        this.col = col;
    }
    public String getColName(){
        return colName;
    }
}
