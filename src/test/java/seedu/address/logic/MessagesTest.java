package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class MessagesTest {

    @Test
    void getErrorMessageForDuplicatePrefixes() {
        Prefix prefix = new Prefix("z/");
        String expectedMessage = Messages.MESSAGE_DUPLICATE_FIELDS + prefix;
        String actualMessage = Messages.getErrorMessageForDuplicatePrefixes(prefix);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void format() {
        Person person = new PersonBuilder().build();
        String expected = person.getName().toString()
                + "; Phone: " + person.getPhone().toString()
                + "; Email: " + person.getEmail().toString()
                + "; Address: " + person.getAddress().toString()
                + "; Remark: " + person.getRemark().toString()
                + "; Tags: ";
        String actual = Messages.format(person);
        assertEquals(expected, actual);

        // check that the number of parameters in the format string matches the number of parameters in toString
        int numOfParamsInFormat = countOccurrences(actual, "; [^;: ]+: ") + 1; // +1 for the first field (name)
        int numOfParamsInToString = countOccurrences(person.toString(), "=");
        assertEquals(numOfParamsInToString, numOfParamsInFormat);
    }

    /** Returns the number of occurrences of the given regex in the text. */
    private static int countOccurrences(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
