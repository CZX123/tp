package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.RemarkCommand;

class RemarkCommandParserTest {
    private RemarkCommandParser parser = new RemarkCommandParser();

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index specified
        assertParseFailure(parser, REMARK_DESC_NON_EMPTY, MESSAGE_INVALID_FORMAT);

        // negative index
        assertParseFailure(parser, "-5" + REMARK_DESC_NON_EMPTY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + REMARK_DESC_NON_EMPTY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);

        // duplicate remark prefix
        assertParseFailure(parser, "1" + REMARK_DESC_NON_EMPTY + REMARK_DESC_EMPTY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_REMARK));
    }

    @Test
    public void parse_validArgs_returnsRemarkCommand() {
        // no remark specified
        assertParseSuccess(parser, "1", new RemarkCommand(INDEX_FIRST_PERSON, ""));

        // empty remark
        assertParseSuccess(parser, "1" + REMARK_DESC_EMPTY, new RemarkCommand(INDEX_FIRST_PERSON, ""));

        // valid remark
        assertParseSuccess(parser, "1" + REMARK_DESC_NON_EMPTY, new RemarkCommand(INDEX_FIRST_PERSON, VALID_REMARK));
    }

}