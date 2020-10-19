package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ExerciseNameContainsKeywordsPredicate;

/**
 * Finds and lists all exercises in fitNUS whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindExercisesCommand extends Command {

    public static final String COMMAND_WORD = "find_exercises";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all exercises whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " bench press";

    private final ExerciseNameContainsKeywordsPredicate predicate;

    public FindExercisesCommand(ExerciseNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW, model.getFilteredExerciseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindExercisesCommand // instanceof handles nulls
                && predicate.equals(((FindExercisesCommand) other).predicate)); // state check
    }
}
