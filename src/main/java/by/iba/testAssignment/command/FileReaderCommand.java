package by.iba.testAssignment.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.iba.testAssignment.ApplicationConstants.FILE_ERR;

public class FileReaderCommand implements Command {
    private static final Logger log = LogManager.getLogger(FileReaderCommand.class);
    private static final String LINE_REGEX = "[A-Za-z]+=[A-Za-z]+";

    @Override
    public void execute(String path) {
        PrintWriter fileErr = null;
        try {
            fileErr = new PrintWriter(FILE_ERR, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            log.error("Failed to create error file for " + this.getClass().getName() + " " + e.getMessage());
        }
        String absolutePath = getAbsolutePath(path);
        List<String> lines;
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
            fileErr.println(e.getMessage());
        }
    }

    private boolean validateLine(String str){
        Pattern pattern = Pattern.compile(LINE_REGEX);
        Matcher matcher = pattern.matcher(str);
        return  matcher.matches();
    }

    private String getAbsolutePath(String path) {
        boolean isAbsolute = path.startsWith("\\:", 1);
        return isAbsolute ? path : FileSystems.getDefault().getPath(path).normalize().toAbsolutePath().toString();
    }

    private void writeInvalidData(String path) {

    }

    private void writeValidData(String path){
        File f = new File(path);
        if(f.exists()){
            try {
                FileReader fr = new FileReader(f);
                //TODO
            } catch (FileNotFoundException e) {
                e.getMessage();
            }
        }
    }
}
