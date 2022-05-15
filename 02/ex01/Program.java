package ex01;

import java.io.*;
import java.util.*;
public class Program {
    public static double getSimilarity(String file1, String file2) throws IOException {
        BufferedReader reader = null;
        String line1 = null;
        String line2 = null;
        try {
            reader = new BufferedReader(new FileReader(System.getenv("PWD") + "/" + file1), 10000000);
            line1 = reader.readLine();
            reader = new BufferedReader(new FileReader(System.getenv("PWD") + "/" + file2), 10000000);
            line2 = reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } finally {
            reader.close();
        }
        Set<String> dict = new HashSet<>();
        List<String> fileArr1 = null;
        List<String> fileArr2 = null;
        try {
            fileArr1 = Arrays.asList(line1.split("\\s+"));
            fileArr2 = Arrays.asList(line2.split("\\s+"));
        } catch (NullPointerException e ) {
            System.out.println("Empty file");
            System.exit(-1);
        }
        dict.addAll(fileArr1);
        dict.addAll(fileArr2);
        List<Integer> fileFrequency1 = new ArrayList<>(dict.size());
        List<Integer> fileFrequency2 = new ArrayList<>(dict.size());
        for (String word : dict) {
            fileFrequency1.add(Collections.frequency(fileArr1, word));
            fileFrequency2.add(Collections.frequency(fileArr2, word));
        }
        int numerator = 0;
        for (int i = 0; i < dict.size(); i++)
            numerator += (fileFrequency1.get(i) * fileFrequency2.get(i));
        double denominator;
        int denominator1 = 0;
        int denominator2 = 0;
        for (Integer n : fileFrequency1)
            denominator1 += n * n;
        for (Integer n : fileFrequency2)
            denominator2 += n * n;
        denominator = Math.sqrt(denominator1) * Math.sqrt(denominator2);
        return numerator / denominator;
    }
    public static void main(String[] args) throws IOException {
        if (args.length < 2)
            System.exit(-1);
        System.out.println(getSimilarity(args[0], args[1]));
    }
}