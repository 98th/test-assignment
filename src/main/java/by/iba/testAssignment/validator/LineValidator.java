package by.iba.testAssignment.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LineValidator {
    private static final String LINE_REGEX = "[A-Za-z]+=[A-Za-z]+";


    public ValidationResult validateLine(String str) {
        ValidationResult vr = new ValidationResult();
        Pattern pattern = Pattern.compile(LINE_REGEX);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return vr;
        } else {
            vr.addMessage("Invalid data", "Wrong key or value");
            return vr;
        }
    }
}
