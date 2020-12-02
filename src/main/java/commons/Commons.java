package commons;

import java.util.ArrayList;

public class Commons {
    public static String[] deleteEmpties(String[] tok){
        int size = 0;
        for(String t : tok){
            if(t != null && !t.isEmpty()){
                size ++;
            }
        }
        String[] out = new String[size];
        int i = 0;
        for(String t : tok){
            if(t != null && !t.isEmpty()){
                out[i++] = t;
            }
        }
        return out;
    }

    public static <T> void disp( ArrayList<T> arr ){
        disp(arr, "generic array list");
    }
    public static <T> void disp( ArrayList<T> arr, String label ){
        if( arr==null || arr.isEmpty() ){
            System.out.println("\nDisplay: " + label + ": NULL or EMPTY array:");
            return;
        }
        System.out.println("\nDisplay: " + label + ": " + arr.size() );
        for( T elem : arr ){
            System.out.println( "\t" + elem );
        }
        System.out.println("End Display: " + label+"\n");
    }
    // For Strings or Objects
    public static <T> void disp( T[] arr ){
        disp(arr, "generic array");
    }
    public static <T> void disp( T[] arr, String label ){
        if( arr==null || arr.length==0 ){
            System.out.println("\nDisplay: " + label + ": NULL or EMPTY array:");
            return;
        }
        System.out.println("\nDisplay: " + label + ": " + arr.length );
        for( T elem : arr ){
            System.out.println( "\t" + elem );
        }
        System.out.println("End Display: " + label+"\n");
    }
    // For primitives
    public static void disp( char[] arr ){
        disp(arr, "char array");
    }
    public static void disp( char[] arr, String label ){
        if( arr==null || arr.length==0 ){
            System.out.println("\nDisplay: " + label + ": NULL or EMPTY array:");
            return;
        }
        System.out.println("\nDisplay: " + label + ": " + arr.length );
        for( char elem : arr ){
            System.out.println( "\t" + elem );
        }
        System.out.println("End Display: " + label+"\n");
    }
    public static void disp( int[] arr ){
        disp(arr, "int array");
    }
    public static void disp( int[] arr, String label ){
        if( arr==null || arr.length==0 ){
            System.out.println("\nDisplay: " + label + ": NULL or EMPTY array:");
            return;
        }
        System.out.println("\nDisplay: " + label + ": " + arr.length );
        for( int elem : arr ){
            System.out.println( "\t" + elem );
        }
        System.out.println("End Display: " + label+"\n");
    }
}
