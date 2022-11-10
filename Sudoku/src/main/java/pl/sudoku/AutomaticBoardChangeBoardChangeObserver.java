package pl.sudoku;

public class AutomaticBoardChangeBoardChangeObserver extends BoardChangeObserver {

    public AutomaticBoardChangeBoardChangeObserver(final SudokuBoard board) {
        super(board);
    }

    @Override
    public void update(final SudokuBoard boardAfterChange) {
        if (!super.checkBoard(boardAfterChange)) {
            System.out.print("Nieprawidłowe uzupełnienie planszy.");
        } else {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    this.board.set(i, j, boardAfterChange.convertToIntList().get(i * 9 + j));
                }
            }
        }
    }
}
