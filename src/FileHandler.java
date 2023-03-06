import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    // Creating a file
    public static void createFile(String fileName) throws IOException {
        File file = new File(fileName);

        if (file.createNewFile()) {
            System.out.println("File " + fileName + " is created successfully.");
        } else {
            System.out.println("File " + fileName + " already exists.");
        }
    }

    // Deleting a file
    public static void deleteFile(String fileName) {
        File file = new File(fileName);

        if (file.delete()) {
            System.out.println("File " + fileName + " is deleted successfully.");
        } else {
            System.out.println("Failed to delete the file " + fileName);
        }
    }

    // Reading a file
    public static void readFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    // Updating a file
    public static void updateFile(String fileName, String newText) throws IOException {
        File file = new File(fileName);

        FileWriter writer = new FileWriter(file, true);
        writer.write(newText);
        writer.close();

        System.out.println("File " + fileName + " is updated successfully.");
    }

    // Updating a line in a file
    public static void updateLineInFile(String fileName, int lineIndex, String newText) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContent.add(line);
            }

            scanner.close();

            if (lineIndex >= fileContent.size()) {
                System.out.println("Invalid line index.");
            } else {
                fileContent.set(lineIndex, newText);
                FileWriter writer = new FileWriter(file);

                for (String line : fileContent) {
                    writer.write(line + "\n");
                }

                writer.close();
                System.out.println("Line " + lineIndex + " in " + fileName + " is updated successfully.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    public static void main(String[] args) {
        try {
            String fileName = "sample.txt";
            createFile(fileName);
            readFile(fileName);
            updateFile(fileName, "This is a new line.\n");
            updateLineInFile(fileName, 2, "This is a modified line.");
            deleteFile(fileName);
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
