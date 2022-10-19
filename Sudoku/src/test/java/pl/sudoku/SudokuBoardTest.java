package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    int[][] exampleBoard_1 = {
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

    /*  Plansza bardzo podobna do exmpleBoard_1, ale w pierwszym wierszu, kolumnie i macierzy 3x3
        zmieniono niektóre wartości, aby plansza, czy też układ liczb był niepoprawny
     */
    int[][] exampleBoard_2 = {
            {5,3,4,6,7,8,9,5,2},
            {6,5,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,1,6,7},
            {8,4,9,7,6,1,4,2,3},
            {5,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
    };

    SudokuBoard exampleSudokuBoard_1 = new SudokuBoard(exampleBoard_1);
    SudokuBoard exampleSudokuBoard_2 = new SudokuBoard();
    SudokuBoard exampleSudokuBoard_3 = new SudokuBoard();
    SudokuBoard exampleSudokuBoard_4 = new SudokuBoard(exampleBoard_2);

    @Test
    public void valueGetterTest() {
        assertEquals(0, exampleSudokuBoard_1.get(-9,-9));
        assertEquals(0, exampleSudokuBoard_1.get(0,-9));
        assertEquals(0, exampleSudokuBoard_1.get(-9,0));
        assertEquals(0, exampleSudokuBoard_1.get(0,9));
        assertEquals(0, exampleSudokuBoard_1.get(9,0));
        assertEquals(0, exampleSudokuBoard_1.get(9,9));
        assertEquals(5, exampleSudokuBoard_1.get(4,4));
        assertEquals(5, exampleSudokuBoard_1.get(0,0));
        assertEquals(2, exampleSudokuBoard_1.get(0,8));
        assertEquals(3, exampleSudokuBoard_1.get(8,0));
        assertEquals(9, exampleSudokuBoard_1.get(8,8));
    }

    @Test
    public void valuesCheckerInALineTest() {
        assertTrue(exampleSudokuBoard_1.checkValuesInALine(1));
    }

    @Test
    public void valuesCheckerInAColumnTest() {
        assertTrue(exampleSudokuBoard_1.checkValuesInAColumn(1));
    }

    @Test
    public void valuesCheckerInAMatrixTest() {
        assertTrue(exampleSudokuBoard_1.checkValuesInAMatrix(4,4));
    }

    @Test
    public void valuesCheckerInALineFailTest() {
        assertFalse(exampleSudokuBoard_4.checkValuesInALine(0));
    }

    @Test
    public void valuesCheckerInAColumnFailTest() {
        assertFalse(exampleSudokuBoard_4.checkValuesInAColumn(0));
    }

    @Test
    public void valuesCheckerInAMatrixFailTest() {
        assertFalse(exampleSudokuBoard_4.checkValuesInAMatrix(0,0));
    }

    @Test
    public void sudokuBoardUniquenessTest() {
        exampleSudokuBoard_2.solveGame();
        exampleSudokuBoard_3.solveGame();

        boolean areSudokusIdentical = true;

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                if (exampleSudokuBoard_1.get(i, j) != exampleSudokuBoard_2.get(i, j)) {
                    areSudokusIdentical = false;
                    break;
                }
            }
            break;
        }
        assertFalse(areSudokusIdentical);
    }

    @Test
    public void checkIfSudokuBoardCorrectTest() {
        exampleSudokuBoard_2.solveGame();
        for (int i = 0; i < 9; i++) {
            assertTrue(exampleSudokuBoard_2.checkValuesInALine(i));
        }

        for (int i = 0; i < 9; i++) {
            assertTrue(exampleSudokuBoard_2.checkValuesInAColumn(i));
        }

        for (int i = 0; i < 3; i++) {
            for (int z = 0; z < 3; z++) {
                assertTrue(exampleSudokuBoard_2.checkValuesInAMatrix(i, z));
            }
        }
    }

    @Test
    public void checkIfSudokuIsDisplayedCorrectlyTest() {
        String sudokuOutput_1 = "";
        String sudokuOutput_2 = exampleSudokuBoard_1.toString();

        sudokuOutput_1 += "|-----------------------|\n";
        for (int i = 0; i < 3; i++) {
            for (int l = 0; l < 3; l++) {
                sudokuOutput_1 += "| ";
                for (int j = 0; j < 3; j++) {
                    for (int z = 0; z < 3; z++) {
                        sudokuOutput_1 += exampleSudokuBoard_1.get(i * 3 + l, j * 3 + z) + " ";
                    }
                    sudokuOutput_1 += "| ";
                }
                sudokuOutput_1 += '\n';
            }
            sudokuOutput_1 += "|-----------------------|\n";
        }

        assertTrue(sudokuOutput_1.equals(sudokuOutput_2));
    }
}
