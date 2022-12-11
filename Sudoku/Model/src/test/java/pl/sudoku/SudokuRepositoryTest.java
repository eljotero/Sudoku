package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class SudokuRepositoryTest {
    private final int[][] correctBoard = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
    };
    @Test
    public void overallTest() {
        SudokuBoard sourceBoard = new SudokuBoard(correctBoard);
        SudokuBoardRepository sudoku_repo = new SudokuBoardRepository(sourceBoard);
        assertNotNull(sudoku_repo);
        SudokuBoard testBoard = sudoku_repo.createInstance();
        assertNotNull(testBoard);
        assertEquals(sourceBoard, testBoard);
    }
}