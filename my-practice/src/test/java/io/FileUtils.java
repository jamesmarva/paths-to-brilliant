package io;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
    static final Pattern P = Pattern.compile("https.*jpg");


    public static void main(String[] args) {
        String writeFilePrefix = "D:/MyWork/2021-05/";
        String writeFileSuffix = ".txt";
        try (FileReader fr = new FileReader("D:/MyWork/2021-05/2021-05-17.txt");
             BufferedReader br = new BufferedReader(fr);
             FileWriter fw = new FileWriter("D:/MyWork/2021-05/imageUrls.md", true);
            BufferedWriter bw = new BufferedWriter(fw)) {
            AtomicInteger i = new AtomicInteger(0);
            br.lines().forEach( o -> {
                int local = i.getAndAdd(1);
                Matcher m = P.matcher(o);
                if (m.find()) {
                    System.out.println(m.group());
                    try {
                        bw.write("[" + local + "](" + m.group() + ")" + "\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printLines(String filename) {
        try (FileReader fr = new FileReader("D:/MyWork/2021-05/2021-05-17.txt");
             BufferedReader br = new BufferedReader(fr)) {
             br.lines().forEach(System.out::println);
        } catch (Exception e) {

        }
    }





}