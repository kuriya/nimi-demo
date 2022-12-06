package org.nimi.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * This method is responsible to transform dates in a file to a given format.
 * A new file will be created with the new format.
 */
public class TransformDates {

    private static final String OUTPUT_FILE_NAME_PREFIX="\\output";
    public static void transformDates(final File file, final String sourcePattern, final String destinationPattern) {
        /**
         * Input and output format
         */
        final SimpleDateFormat inputFormat = new SimpleDateFormat(sourcePattern);
        final SimpleDateFormat outputFormat = new SimpleDateFormat(destinationPattern);

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final StringBuilder fileContentBuilder = new StringBuilder();
        final StringBuilder lineBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            try {
                lineBuilder.setLength(0);
                lineBuilder.append(scanner.nextLine());
                String requiredDate = outputFormat.format(inputFormat.parse(lineBuilder.toString()));
                fileContentBuilder.append(requiredDate);
            } catch (ParseException e) {
                fileContentBuilder.append(lineBuilder.toString());
            } finally {
                fileContentBuilder.append("\n");
            }
        }
        FileWriter writer = null;
        try {
            final StringBuilder outputFilePathBuilder = new StringBuilder();
            outputFilePathBuilder.append(file.getParent());
            outputFilePathBuilder.append(OUTPUT_FILE_NAME_PREFIX);
            outputFilePathBuilder.append(file.getName());

            final String outputFilepath = outputFilePathBuilder.toString().replace("\\", "/");
            writer = new FileWriter(outputFilepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write(fileContentBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
