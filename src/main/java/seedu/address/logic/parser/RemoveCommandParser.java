package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new RemoveCommand object
 */
public class RemoveCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns a RemoveCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {

        String[] identifiers = argsToArray(args);

        if (isIndexArg(identifiers)) {
            return new RemoveCommand(ParserUtil.parseIndex(identifiers[0]));
        } else {
            return new RemoveCommand(new NameContainsKeywordsPredicate(Arrays.asList(identifiers)));
        }
    }

    // helper, to follow single layer abstraction
    public String[] argsToArray(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        }
        return trimmedArgs.split("\\s+");
    }

    public boolean isIndexArg(String[] args) {
        return args.length == 1 && isInteger(args[0]);
    }

    /**
     * Returns true if the string is an integer.
     */
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
