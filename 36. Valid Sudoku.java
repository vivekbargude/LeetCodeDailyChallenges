// Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

// Each row must contain the digits 1-9 without repetition.
// Each column must contain the digits 1-9 without repetition.
// Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
// Note:

// A Sudoku board (partially filled) could be valid but is not necessarily solvable.
// Only the filled cells need to be validated according to the mentioned rules.
 

// Example 1:


// Input: board = 
// [["5","3",".",".","7",".",".",".","."]
// ,["6",".",".","1","9","5",".",".","."]
// ,[".","9","8",".",".",".",".","6","."]
// ,["8",".",".",".","6",".",".",".","3"]
// ,["4",".",".","8",".","3",".",".","1"]
// ,["7",".",".",".","2",".",".",".","6"]
// ,[".","6",".",".",".",".","2","8","."]
// ,[".",".",".","4","1","9",".",".","5"]
// ,[".",".",".",".","8",".",".","7","9"]]
// Output: true
// Example 2:

// Input: board = 
// [["8","3",".",".","7",".",".",".","."]
// ,["6",".",".","1","9","5",".",".","."]
// ,[".","9","8",".",".",".",".","6","."]
// ,["8",".",".",".","6",".",".",".","3"]
// ,["4",".",".","8",".","3",".",".","1"]
// ,["7",".",".",".","2",".",".",".","6"]
// ,[".","6",".",".",".",".","2","8","."]
// ,[".",".",".","4","1","9",".",".","5"]
// ,[".",".",".",".","8",".",".","7","9"]]
// Output: false
// Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 

// Constraints:

// board.length == 9
// board[i].length == 9
// board[i][j] is a digit 1-9 or '.'.

import java.util.*;

class Solution {

    //Approach 1: Brute Force

    // public boolean isValidSudoku(char[][] board) {
    //     int m = 9;
    //     int n = 9;

    //     //for checking all rows
    //     for(int row=0; row<m; row++){
    //         Set<Character> st = new HashSet<>();

    //         for(int col=0; col<n; col++){

    //             if(board[row][col]=='.')
    //             continue;

    //             if(st.contains(board[row][col]))
    //             return false;

    //             st.add(board[row][col]);
    //         }
    //     }

    //     //for checking all columns
    //     for(int col = 0; col<n ; col++) {
    //         Set<Character> st = new HashSet<>();

    //         for(int row=0; row<m; row++){
    //             if(board[row][col]=='.')
    //             continue;

    //             if(st.contains(board[row][col]))
    //             return false;

    //             st.add(board[row][col]);
    //         }
    //     }


    //     //for submatrix traversal
    //     for(int sr=0 ; sr<9 ; sr+=3) {
    //         int er = sr+2;

    //         for(int sc=0; sc<9; sc+=3){
    //             int ec = sc+2;

    //             if(!traverseSubMatrix(board,sr,er,sc,ec))
    //             return false;
    //         }
    //     }

    //     return true;
    // }

    // public boolean traverseSubMatrix(char[][] board, int sr, int er, int sc, int ec) {
        
    //     Set<Character> st = new HashSet<>();

    //     for(int i = sr; i <= er; i++) {
    //         for(int j = sc; j <= ec; j++) {
    //             if(board[i][j]=='.')
    //             continue;

    //             if(st.contains(board[i][j]))
    //             return false;

    //             st.add(board[i][j]);
    //         }
    //     }
        
    //     return true;
    // }

    

    //Approach 2: Using Set

    public boolean isValidSudoku(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        Set<String> st = new HashSet<>();

        for(int row = 0 ; row < m ; row++) {
            for(int col = 0 ; col < n ; col++) {

                if(board[row][col]=='.')
                continue;

                char currNumber = board[row][col];

                String rowKey = Character.toString(currNumber) + "_ROW_" + Integer.toString(row);
                String colKey = Character.toString(currNumber) + "_COL_" + Integer.toString(col);
                String boxKey = Character.toString(currNumber) + "_BOX_" + Integer.toString(row/3) + Integer.toString(col/3);


                if(st.contains(rowKey) || st.contains(colKey) || st.contains(boxKey))
                return false;

                st.add(rowKey);
                st.add(colKey);
                st.add(boxKey);

            }
        }

        return true;
    }

}