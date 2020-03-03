package by.iba.testAssignment.command;

import com.sun.deploy.association.utility.WinRegistryWrapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.iba.testAssignment.ApplicationConstants.RK_ERR;
import static by.iba.testAssignment.ApplicationConstants.RK_OUT;

public class RegistryKeyCommand implements Command {
    private static final String PATH_REGEX = "([A-Za-z_]+)(\\\\)([\\w\\p{Punct}\\\\]+)+(\\\\)([\\d\\w\\p{Punct}]+)";

    @Override
    public void execute(String path) {
        Pattern pattern = Pattern.compile(PATH_REGEX);
        Matcher matcher = pattern.matcher(path);
        try (BufferedWriter outWriter =  new BufferedWriter(new FileWriter(RK_OUT));
        BufferedWriter errWriter = new BufferedWriter(new FileWriter(RK_ERR))){
            if (matcher.matches()) {
                System.out.println(matcher.groupCount());
                String key = matcher.group(5);
                String regPath = matcher.group(3);
                String hkey = matcher.group(1);
                int hkeyVal = 0;
                try {
                    hkeyVal = (int) WinRegistryWrapper.class.getField(hkey).get(null);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    errWriter.write(e.getMessage());
                }
                if (hkeyVal == 0) {
                    errWriter.write("Invalid path " + path);
                    return;
                }
                String value = WinRegistryWrapper.WinRegQueryValueEx(hkeyVal, regPath, key);
                outWriter.write(key + "=" + value);
            } else {
                errWriter.write("Invalid path " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
