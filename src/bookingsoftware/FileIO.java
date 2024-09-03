/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author jmone
 * @param <E>
 */
public class FileIO<E> {

    private String fileName;

    public FileIO(String fileName) {
        this.fileName = fileName;
    }

    /////////////////////////////
    // WRITE

    // append one thing to file
    public void writeToFile(E object) throws IOException {
        PrintWriter pw = null;
        try {
            FileWriter fr;
            fr = new FileWriter(this.fileName, true);
            BufferedWriter br = new BufferedWriter(fr);
            pw = new PrintWriter(br);
            pw.println(object);

            pw.close();
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // write all to file
    public void writeAllToFile(ArrayList<String> objects) throws IOException {
        PrintWriter pw = null;
        try {
            FileWriter fr;
            // append set to false to overwrite file
            fr = new FileWriter(this.fileName, false);
            BufferedWriter br = new BufferedWriter(fr);
            pw = new PrintWriter(br);

            for (String object : objects) {
                // write line per line from arraylist
                pw.println(object);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /////////////////////////////

    // READ

    // store file in arraylist
    public ArrayList<String> readFileToList() {

        ArrayList<String> lines = new ArrayList<>();
        try {
            // Read from file
            FileReader fr = new FileReader(this.fileName);
            BufferedReader inputStream = new BufferedReader(fr);
            String line;

            // add each line to arraylist
            while ((line = inputStream.readLine()) != null) {
                lines.add(line);
            }
            fr.close();
            inputStream.close();

            return lines;

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
            return null;
        } catch (IOException I) {
            System.out.println("Error reading file: " + I);
            return null;
        }
    }

    /////////////////////////////

    // PRINT

    // print everything on file
    public void displayFile() {
        try {
            // Read and print all elements in file.txt
            FileReader fr = new FileReader(this.fileName);
            BufferedReader inputStream = new BufferedReader(fr);

            String line;
            // print out every element line by line
            while ((line = inputStream.readLine()) != null) {
                System.out.println(line);
            }

            fr.close();
            inputStream.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException I) {
            System.out.println("Error reading file: " + I);
        }
    }

    /////////////////////////////
}
