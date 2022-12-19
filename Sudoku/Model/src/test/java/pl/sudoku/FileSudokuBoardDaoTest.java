package pl.sudoku;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static pl.sudoku.SudokuBoardDaoFactory.getFileDao;

import org.junit.jupiter.api.Test;
import pl.sudoku.exceptions.FileSudokuBoardDaoInputException;
import pl.sudoku.exceptions.FileSudokuBoardDaoOutputException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import pl.sudoku.exceptions.FileException;
import pl.sudoku.exceptions.GeneralDaoException;
import pl.sudoku.exceptions.InputOutputOperationException;

public class FileSudokuBoardDaoTest {

    private final SudokuSolver exampleSolver_1 = new BacktrackingSudokuSolver();
    private final SudokuBoard exampleSudokuBoard_1 = new SudokuBoard(exampleSolver_1);
    private final SudokuBoard exampleSudokuBoard_2 = new SudokuBoard(exampleSolver_1);

    @Test
    public void IntroTest() {
        assertNotNull(exampleSolver_1);
        assertNotNull(exampleSudokuBoard_1);
        assertNotNull(exampleSudokuBoard_2);
    }

    @Test
    public void writeReadTest() throws Exception {
        SudokuBoard sudokuFromFile;
        try (Dao<SudokuBoard> fileSudokuBoardDao = getFileDao("someFileName")) {
            fileSudokuBoardDao.write(exampleSudokuBoard_1);
            sudokuFromFile = fileSudokuBoardDao.read();
        } catch (InputOutputOperationException e) {
            throw new GeneralDaoException(e.getMessage(), e.getCause());
        }
        assertTrue(exampleSudokuBoard_1.equals(sudokuFromFile));
    }

    @Test
    public void writeReadTestMultiple() throws Exception {
        SudokuBoard sudoku1;
        SudokuBoard sudoku2;
        try (Dao<SudokuBoard> fileSudokuBoardDao = getFileDao("someFileName")) {
            fileSudokuBoardDao.write(exampleSudokuBoard_1);
            fileSudokuBoardDao.write(exampleSudokuBoard_2);
            sudoku1 = fileSudokuBoardDao.read();
            sudoku2 = fileSudokuBoardDao.read();
        } catch (InputOutputOperationException e) {
            throw new GeneralDaoException(e.getMessage(), e.getCause());
        }
        assertTrue(exampleSudokuBoard_1.equals(sudoku1));
        assertTrue(exampleSudokuBoard_2.equals(sudoku2));
    }

    @Test
    public void readException() throws Exception {
        try (Dao<SudokuBoard> fileSudokuBoardDao = getFileDao("yyy")) {
            assertThrows(FileSudokuBoardDaoInputException.class, () -> fileSudokuBoardDao.read());
        } catch (InputOutputOperationException e) {
            throw new GeneralDaoException(e.getMessage(), e.getCause());
        }
    }

    @Test
    public void fileExceptionTest() {
        assertThrows(FileException.class, () -> getFileDao("???"));
    }

    @Test
    public void writeExceptionTest() throws Exception {
        Dao<SudokuBoard> fileSudokuBoardDao = getFileDao("correctName");
        fileSudokuBoardDao.close();
        assertThrows(FileSudokuBoardDaoOutputException.class, () -> fileSudokuBoardDao.write(exampleSudokuBoard_1));
        assertThrows(InputOutputOperationException.class, () -> fileSudokuBoardDao.close());
    }
}
