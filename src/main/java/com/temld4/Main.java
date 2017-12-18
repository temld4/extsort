package com.temld4;

import com.temld4.generate.Generator;
import com.temld4.sort.Sort;

import java.io.IOException;

public class Main {

    private static final Generator generator = new Generator();


    public static void main(String[] args) {
        String action = "";
        if (args.length > 0) {
            action = args[0];
        }

        switch (action) {
            case "generate":
                generateFile(args);
                break;
            case "sort":
                sortFile(args);
                break;
            default:
                printUsage();
        }
    }

    private static void generateFile(String[] args) {
        try {
            String fileName = args[1];
            int strNum = Integer.parseInt(args[2]);
            int strLen = Integer.parseInt(args[3]);

            generator.generate(fileName, strNum, strLen);
        } catch (Exception e) {
            printUsage();
        }
    }

    private static void sortFile(String[] args) {
        try {
            String fileName = args[1];
            int subFileSize = Integer.parseInt(args[2]);

            Sort sort = new Sort(fileName, subFileSize);
            sort.sort();
        } catch (Exception e) {
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("generate [file name] [lines in file] [line length]");
        System.out.println("sort [filename] [sub file size]");
    }
}
