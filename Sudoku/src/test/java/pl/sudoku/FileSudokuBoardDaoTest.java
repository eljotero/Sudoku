package pl.sudoku;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import org.junit.jupiter.api.Test;

public class FileSudokuBoardDaoTest {

    private final SudokuBoardDaoFactory newFactory_1 = new SudokuBoardDaoFactory();
    private final SudokuSolver exampleSolver_1 = new BacktrackingSudokuSolver();
    private final SudokuBoard exampleSudokuBoard_1 = new SudokuBoard(exampleSolver_1);
    private Dao<SudokuBoard> fileSudokuBoardDao;

    @Test
    public void IntroTest() {
        assertNotNull(newFactory_1);
        assertNotNull(exampleSolver_1);
        assertNotNull(exampleSudokuBoard_1);
    }

    @Test
    public void writeReadTest () {
        fileSudokuBoardDao = newFactory_1.getFileDao("file");
        fileSudokuBoardDao.write(exampleSudokuBoard_1);
        assertNotNull(fileSudokuBoardDao);
        assertTrue(exampleSudokuBoard_1.equals(fileSudokuBoardDao.read()));
    }

    @Test
    public void readException () {
        fileSudokuBoardDao = newFactory_1.getFileDao("yyy");
        assertThrows(RuntimeException.class, () -> {fileSudokuBoardDao.read();});
    }

    @Test
    public void writeException () {
        fileSudokuBoardDao = newFactory_1.getFileDao("???");
        assertThrows(RuntimeException.class, () -> {fileSudokuBoardDao.write(exampleSudokuBoard_1);});
    }

}