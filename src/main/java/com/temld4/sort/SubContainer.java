package com.temld4.sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SubContainer implements Comparable<SubContainer> {

    private String fileName;
    private BufferedReader reader;
    private String nextLine;

    public SubContainer(String fileName) {
        try {
            this.fileName = fileName;
            this.reader = new BufferedReader(new FileReader(fileName));
            nextLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasMore() {
        return nextLine != null;
    }

    public String popFirst() {
        String out = nextLine;
        try {
            nextLine = reader.readLine();
            if (nextLine == null) {
                File file = new File(fileName);
                file.delete();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            nextLine = null;
        }
        return out;
    }

    String getFirst() {
        return nextLine;
    }

    String getFileName() {
        return fileName;
    }

    @Override
    public int compareTo(SubContainer right) {
        int firstCmp = this.getFirst().compareTo(right.getFirst());
        return firstCmp != 0 ? firstCmp : this.getFileName().compareTo(right.getFileName());
    }
}
