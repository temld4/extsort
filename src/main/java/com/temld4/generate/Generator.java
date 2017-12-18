package com.temld4.generate;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {

    private final Random RND = new Random(42);

    public void generate(String outputFileName, int strNum, int strLen) {
        FileWriter fw = null;
        BufferedWriter writer = null;

        try {
            fw = new FileWriter(outputFileName);
            writer =  new BufferedWriter(fw);

            for (int i = 0; i < strNum; i++) {
                if (i % 10000 == 0) {
                    System.out.println(i);
                }
                writer.write(RandomStringUtils.randomAlphabetic(strLen));
                writer.newLine();
            }

            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
