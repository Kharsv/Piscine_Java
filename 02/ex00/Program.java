package ex00;
import java.io.*;
import java.util.*;

public class Program {
    private static final String SIGNATURES = "signatures.txt";
    private static final String RESULT = "result.txt";
    public static void main(String[] args) {
        Map<String, String> signaturesMap = new HashMap<>();
        StringBuilder myStringBuilder = new StringBuilder(30);

        try {
            FileInputStream myFileInputStream = new FileInputStream(new File("signature.txt"));

            for (int symbol = myFileInputStream.read(); symbol != -1; symbol = myFileInputStream.read()) {
                if ((char) symbol == '\n' || myFileInputStream.available() == 0) {
                    String[] newLine = myStringBuilder.toString().split(", ");
                    signaturesMap.put(newLine[1].trim(), newLine[0]);
                    myStringBuilder.setLength(0);

                } else {
                    myStringBuilder.append((char) symbol);
                }
            }

            myFileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner myScanner = new Scanner(System.in);
        while ( true ) {

            String inputString = myScanner.nextLine();
            if ( inputString.equals("42") ) {
                break;
            }

            FileInputStream inputFile = null;
            FileOutputStream outputFile = null;
            try {

                outputFile = new FileOutputStream("result.txt", true);
                inputFile = new FileInputStream(inputString);
                for (int i = 0; inputFile.available() > 0 && i < 10; i++) {
                    myStringBuilder.append(String.format("%02X ", inputFile.read()));
                }

                String inputFileSignature = myStringBuilder.toString();
                String value = "";
                for (String key : signaturesMap.keySet()) {

                    if (inputFileSignature.startsWith(key)) {

                        value = signaturesMap.get(key);
                        outputFile.write(value.getBytes());
                        outputFile.write('\n');
                        System.out.println("PROCESSED");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {

                    if (inputFile != null)
                        inputFile.close();
                    if (outputFile != null)
                        outputFile.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
