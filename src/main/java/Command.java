import academic.GradeBook;
import academic.PersonBook;
import exceptions.InvalidCommandException;
import bookmark.BookmarkCategory;
import bookmark.commands.BookmarkCommand;
import bookmark.InvalidBookmarkCommandException;
import java.util.ArrayList;
import bookmark.BookmarkUi;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;

public class Command {

    public static void executeCommand(String command, CommandType commandType,
                                      ArrayList<BookmarkCategory> bookmarkCategories, BookmarkUi bookmarkUi,
                                      BookmarkParser bookmarkParser) {
        if (commandType == CommandType.EXIT_PROGRAM) {
            Ui.printExit();
        } else if (commandType == CommandType.EXIT_MODE) {
            Ui.exitMode();
        } else if (commandType == CommandType.LOCATION) {
            Ui.printLocation();
        } else if (commandType == CommandType.CHANGE_MODE) {
            Ui.changeModeCommand(command);
        } else if (commandType == CommandType.HELP) {
            HelpMessage.printHelpMessage();
        } else if (StudyIt.getCurrentMode() != Mode.MENU) {
            handleNonGeneralCommand(command,commandType,bookmarkCategories,bookmarkUi,bookmarkParser);
        } else {
            ErrorMessage.printUnidentifiableCommand();
        }
    }

    public static void handleNonGeneralCommand(String command, CommandType commandType,
                                               ArrayList<BookmarkCategory> bookmarkCategories,
                                               BookmarkUi bookmarkUi,BookmarkParser bookmarkParser) {
        Mode currentMode = StudyIt.getCurrentMode();
        if (currentMode == Mode.BOOKMARK) {
            executeBookmarkModeCommand(command,bookmarkCategories,bookmarkUi,bookmarkParser);
        } else if (currentMode == Mode.TIMETABLE) {
            executeTimetableModeCommand();
        } else if (currentMode == Mode.ACADEMIC) {
            executeAcademicModeCommand(command);
        } else if (currentMode == Mode.FLASHCARD) {
            executeFlashcardCommand();
        }
    }

    public static void executeBookmarkModeCommand(String command, ArrayList<BookmarkCategory> categories,
                                                  BookmarkUi ui, BookmarkParser parser) {
        try {
            BookmarkCommand c = parser.evaluateInput(command,ui, categories);
            c.executeCommand(ui,categories);
        } catch (InvalidBookmarkCommandException e) {
            ui.showInvalidBookmarkCommand();
        }
    }

    public static void executeTimetableModeCommand() {

    }

    public static void executeAcademicModeCommand(String command) {
        try {
            AcademicCommandType commandType = AcademicCommandParser.getAcademicCommandType(command);

            if (commandType == AcademicCommandType.ADD_CONTACT) {
                Ui.printLine("Adding Contact"); //TODO: Remove placeholder line.
                PersonBook.addPerson(AcademicCommandParser.getContact(command));

            } else if (commandType == AcademicCommandType.CHECK_CONTACT) {
                Ui.printLine("Checking Contact"); //TODO: Remove placeholder line.
                Ui.printLine(PersonBook.printPersonBook());

            } else if (commandType == AcademicCommandType.ADD_GRADE) {
                Ui.printLine("Adding Grade"); //TODO: Remove placeholder line.
                GradeBook.addGrade(AcademicCommandParser.getGrade(command));

            } else if (commandType == AcademicCommandType.CHECK_GRADE) {
                Ui.printLine("Checking Grade"); //TODO: Remove placeholder line.
                Ui.printLine(GradeBook.printCap());

            } else if (commandType == AcademicCommandType.LIST_GRADE) {
                Ui.printLine("Listing Grade"); //TODO: Remove placeholder line.
                Ui.printLine(GradeBook.printListOfGrades());

            }
        } catch (InvalidCommandException e) {
            ErrorMessage.printUnidentifiableCommand();
        } catch (StringIndexOutOfBoundsException e) {
            ErrorMessage.printUnidentifiableInput();
        } catch (NumberFormatException e) {
            ErrorMessage.printInvalidNumber();
        } catch (InvalidGradeException e) {
            ErrorMessage.printInvalidGrade();
        } catch (InvalidMcException e) {
            ErrorMessage.printInvalidMc();
        }
    }

    public static void executeFlashcardCommand() {

    }
}