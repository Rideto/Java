/**
 * Date: 13.02.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        HashMap <String, Integer> hMap = new HashMap<>();
        ValueComparator comp =  new ValueComparator(hMap);
        TreeMap <String, Integer> sortedMap = new TreeMap<String, Integer>(comp);
        int k = 0;

        try(PrintWriter out = new PrintWriter("output.txt");Reader in = new InputStreamReader(new FileInputStream(args[0]), "windows-1251")) {

            StringBuilder sb = new StringBuilder();
            String s;
            int data;

            while ((data = in.read()) != -1) {
                char c = (char) data;
                if (Character.isLetterOrDigit(c)){
                    sb.append(c);
                }
                else {
                    if (sb.length() != 0)  {
                        s = sb.toString();
                        if (hMap.containsKey(s)) {
                            hMap.put(s, hMap.get(s)+1);
                        } else {
                            hMap.put(s, 1);
                        }
                        k++;
                    }
                    sb.setLength(0);
                }
            }
            if (sb.length() != 0) {
                s = sb.toString();
                if (hMap.containsKey(s)) {
                    hMap.put(s, hMap.get(s)+1);
                } else {
                    hMap.put(s, 1);
                }
                k++;
            }

            sortedMap.putAll(hMap);

            for (Map.Entry<String, Integer> e : sortedMap.entrySet()) {
                if (e.getKey().length() > 7) {
                    out.printf("%s\t%d\t%.5f%n", e.getKey(), e.getValue(), (float)e.getValue()/k);
                }
                else {
                    out.printf("%s\t\t%d\t%.5f%n", e.getKey(), e.getValue(), (float)e.getValue()/k);
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }


    }
}