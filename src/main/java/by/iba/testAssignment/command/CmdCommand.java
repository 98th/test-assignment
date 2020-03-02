package by.iba.testAssignment.command;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;


import static by.iba.testAssignment.ApplicationConstants.CMD_ERR;
import static by.iba.testAssignment.ApplicationConstants.CMD_OUT;


public class CmdCommand implements Command {

    @Override
    public void execute(String value) {
        try (BufferedWriter outWriter =  new BufferedWriter(new FileWriter(CMD_OUT));
            BufferedWriter errWriter = new BufferedWriter(new FileWriter(CMD_ERR))) {
            Process process = new ProcessBuilder("cmd.exe", "/c", value).start();

            String stdErr = IOUtils.toString(process.getErrorStream(), Charset.defaultCharset());
            String stdout = IOUtils.toString(process.getInputStream(), Charset.defaultCharset());
            outWriter.write(stdout);
            errWriter.write(stdErr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
