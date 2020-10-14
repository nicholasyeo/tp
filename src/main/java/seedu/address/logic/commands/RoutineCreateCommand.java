package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Routine;

/**
 * Adds an Routine to fitNUS.
 */
public class RoutineCreateCommand extends Command {

    public static final String COMMAND_WORD = "routine_create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a routine to fitNUS. "
            + "Parameters: "
            + PREFIX_ROUTINE + "ROUTINE_NAME "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROUTINE + "Leg Day Session ";

    public static final String MESSAGE_SUCCESS = "New routine added: %1$s";
    public static final String MESSAGE_DUPLICATE_ROUTINE = "This routine already exists in fitNUS";

    private final Routine toAdd;

    /**
     * Creates an RoutineAddCommand to add the specified {@code Routine}
     */
    public RoutineCreateCommand(Routine routine) {
        requireNonNull(routine);
        toAdd = routine;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRoutine(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROUTINE);
        }

        model.addRoutine(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineCreateCommand // instanceof handles nulls
                && toAdd.equals(((RoutineCreateCommand) other).toAdd));
    }
}
