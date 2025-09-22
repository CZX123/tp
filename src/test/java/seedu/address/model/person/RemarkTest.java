package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    void toStringMethod() {
        String remarkString = "This is a remark";
        Remark remark = new Remark(remarkString);
        assertEquals(remarkString, remark.toString());
    }

    @Test
    void equals() {
        Remark remark = new Remark(VALID_REMARK);

        // same values -> returns true
        assertTrue(remark.equals(new Remark(VALID_REMARK)));

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // null -> returns false
        assertFalse(remark.equals(null));

        // different types -> returns false
        assertFalse(remark.equals(5.0f));

        // different values -> returns false
        assertFalse(remark.equals(new Remark("Other Valid Remark")));
    }

    @Test
    void hashCodeMethod() {
        String remarkString = "This is a remark";
        Remark remark = new Remark(remarkString);
        assertEquals(remarkString.hashCode(), remark.hashCode());
    }
}
