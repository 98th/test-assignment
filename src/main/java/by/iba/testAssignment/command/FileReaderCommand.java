package by.iba.testAssignment.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.iba.testAssignment.FileNameContainer.FILE_ERR;
import static by.iba.testAssignment.FileNameContainer.FILE_OUT;

public class FileReaderCommand implements Command {
    private static final Logger log = LogManager.getLogger(FileReaderCommand.class);
    private static final String LINE_REGEX = "[A-Za-z0-9]+=[A-Za-z0-9]+";

    @Override
    public void execute(String path) {
        PrintWriter fileErrWriter = null;
        try {
            fileErrWriter = new PrintWriter(new FileWriter(FILE_ERR));
        } catch (IOException  e) {
            log.error("Failed to create error file " + e.getMessage());
        }
        String absolutePath = getAbsolutePath(path);
        List<String> lines;
        System.out.println(new File(absolutePath).exists());
        try {
            lines = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8);
            boolean isValid = true;
            for (String i : lines) {
                if(!validateLine(i)) {
                    isValid = false;
                }
            }
            if (isValid) {
                writeValidData(absolutePath);
            } else {
                writeInvalidData(absolutePath);
            }
        } catch (IOException e) {
            e.printStackTrace(fileErrWriter);
        } finally {
            if(fileErrWriter != null) {
                fileErrWriter.close();
            }
        }
    }

    private boolean validateLine(String str){
        Pattern pattern = Pattern.compile(LINE_REGEX);
        Matcher matcher = pattern.matcher(str);
        return  matcher.matches();
    }

    private String getAbsolutePath(String pathName) {
        Path path = Paths.get(pathName);
        if (path.isAbsolute()){
            return pathName;
        } else {
            return FileSystems.getDefault().getPath(pathName).normalize().toAbsolutePath().toString();
        }
    }

    private void writeInvalidData(String path) throws IOException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_OUT));
        while (scanner.hasNext()) {
            String content = scanner.nextLine();
            writer.write(content);
        }
        writer.close();
    }

    private void writeValidData(String path) throws IOException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_OUT));
        while (scanner.hasNext()) {
            String content = scanner.nextLine();
            String[] subContent = content.split("=");
            writer.write(subContent[0] + "\r\n" + subContent[1] + "\r\n");
        }
        writer.close();
    }
}
