package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    /*
    Sample code to read from file passed as command line argument
    try {
        // the file to be opened for reading
        FileInputStream fis = new FileInputStream(args[0]);
        Scanner sc = new Scanner(fis); // file to be scanned
        // returns true if there is another line to read
        while (sc.hasNextLine()) {
           //Add your code here to process input commands
        }
        sc.close(); // closes the scanner
    } catch (IOException e) {
    }
    */
    System.out.println("Hello World!");
    // Sample code to read from file passed as command line argument
    try {
      // the file to be opened for reading
      FileInputStream fis = new FileInputStream(args[0]);
      Scanner sc = new Scanner(fis); // file to be scanned
      // returns true if there is another line to read
      while (sc.hasNextLine()) {
        //Add your code here to process input commands
        String line = sc.nextLine();
        List<String> tokens = Arrays.asList(line.split(" "));
        System.out.println(tokens);
      }
      sc.close(); // closes the scanner
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
