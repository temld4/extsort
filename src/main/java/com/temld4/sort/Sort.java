package com.temld4.sort;

import java.io.*;
import java.util.*;

public class Sort {

    private final String sourceFile;
    private final String destFile;
    private final List<String> subFiles = new ArrayList<>();

    private final int subFileSize;

    public Sort(String sourceFile, int subFileSize) {
        this.sourceFile = sourceFile;
        this.destFile = sourceFile + ".sorted.txt";
        this.subFileSize = subFileSize;
    }

    public void sort() {
        createSortedSubfiles();
        mergeSubfiles();
    }

    private void mergeSubfiles() {
        try {
            BufferedWriter destWriter = new BufferedWriter(new FileWriter(destFile));

            TreeSet<SubContainer> subContainers = new TreeSet<>();
            for (String subFile : subFiles) {
                subContainers.add(new SubContainer(subFile));
            }

            while (subContainers.size() > 0) {
                SubContainer first = subContainers.pollFirst();
                destWriter.write(first.popFirst());
                destWriter.newLine();

                if (first.hasMore()) {
                    subContainers.add(first);
                }
            }

            destWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSortedSubfiles() {
        try {
            String subFilePrefix = "subfile-";

            BufferedReader sourceReader = new BufferedReader(new FileReader(sourceFile));

            String nextLine = null;
            List<String> buffer = new ArrayList<>(subFileSize);
            int bufferCnt = 0;
            int currentSubNum = 0;
            for(;;) {
                nextLine = sourceReader.readLine();

                if (bufferCnt >= subFileSize || nextLine == null) {
                    Collections.sort(buffer);

                    String subFileName = subFilePrefix + currentSubNum;
                    currentSubNum++;
                    subFiles.add(subFileName);

                    BufferedWriter subWriter = new BufferedWriter(new FileWriter(subFileName));
                    for (String outputLine : buffer) {
                        subWriter.write(outputLine);
                        subWriter.newLine();
                    }
                    subWriter.close();

                    buffer = new ArrayList<>(subFileSize);
                    bufferCnt = 0;
                }

                if (nextLine != null) {
                    buffer.add(nextLine);
                    bufferCnt++;
                } else {
                    break;
                }
            }

            sourceReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
