package ex02;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    public static final String GREEN = "\u001B[32m";
    public static final String WHITE = "\u001B[37m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static String currentPath = null;
    public static String formatFileSize(long size) {
        Double truncatedDouble = BigDecimal.valueOf(size / 1024.0).setScale(1, RoundingMode.HALF_UP).doubleValue();
        return truncatedDouble + " KB";
    }
    private static long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        int count = files.length;
        for (File file : files) {
            if (file.isFile())
                length += file.length();
            else
                length += getFolderSize(file);
        }
        return length;
    }
    public static void getDirList() {
        Set<File> currentDirectory = Stream.of(new File(currentPath).listFiles())
                .collect(Collectors.toSet());
        for (File f : currentDirectory)
            if (f.isDirectory())
                System.out.println(GREEN + f.getName() + RESET + " " + WHITE + formatFileSize(getFolderSize(f)) + RESET);
        for (File f : currentDirectory)
            if (f.isFile())
                System.out.println(f.getName() + " " + WHITE + formatFileSize(f.length()) + RESET);
    }
    public static void changeDir(String newFolder) throws IOException {
        File file = new File(currentPath + "/" + newFolder);
        if (file.isDirectory()) {
            currentPath = file.getCanonicalPath();
            System.out.println(currentPath);
        } else
            System.out.println(RED + "Error while opening"+ RESET);
    }
    public static void renameFile(String fileName1, String fileName2) throws IOException {
        File file1 = new File(currentPath + "/" + fileName1);
        File file2 = new File(currentPath + "/" + fileName2);
        if (file2.isDirectory()) {
            if (file2.exists())
                file1.renameTo(new File(file2.getCanonicalPath() + "/" + file1.getName()));
            else
                System.out.println(RED + "No such directory"+ RESET);
        } else {
            boolean success = file1.renameTo(file2);
            if (!success)
                System.out.println("File was not successfully renamed");
        }
    }
    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].startsWith("--current-folder=")) {
            currentPath = args[0].substring(args[0].indexOf("=") + 1);
        } else
            System.exit(-1);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] cmd = input.split("\\s+");
            if (cmd.length == 0) continue;
            if (cmd[0].equals("exit")) break;
            else if (cmd[0].equals("ls"))
                getDirList();
            else if (cmd[0].equals("cd") && cmd.length > 1)
                changeDir(cmd[1]);
            else if (cmd[0].equals("mv") && cmd.length > 2)
                renameFile(cmd[1], cmd[2]);
            else
                System.out.println(RED + "Unknown command"+ RESET);
        }
    }
}