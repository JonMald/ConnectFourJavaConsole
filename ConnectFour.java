package ConnectFour;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ConnectFour {
  private int turnCount;
  private int playerTurn = 1;
  private String[][] board;

  public ConnectFour() {
    board = new String[6][7];
    for(int y = 0; y < board.length; y++) {
      for(int x = 0; x < board[0].length; x++) {
        board[y][x] = "|   ";
      }
    }
    run();
  }
  public static void main(String[] args) {
   new ConnectFour();
}
  private void run() {
    while(!checkWin() && !checkTie()) {
      if(turnCount != 0) swapTurn();
      printBoard();
      getTurn();
      turnCount++;
    }
    printBoard();
  }
  private void printBoard() {
    for(int i = 0; i < 7; i++) {
      System.out.print(" ");
    }
    if(!checkWin() && !checkTie())
    System.out.print("Player " + playerTurn + "'s Turn\n");
    else if(checkWin()) 
    System.out.print("Player " + playerTurn + " Wins!\n");
    else System.out.print("Game Over! Tie!\n");

    for(int i = 0; i < 29; i++) {
      System.out.print("=");
    }
    System.out.print("\n");
    for(int y = 0; y < board.length; y++) {
      for(int x = 0; x < board[0].length; x++) {
        System.out.print(board[y][x]);
      }
      System.out.print("|\n");
    }
    for(int i = 0; i < 29; i++) {
      System.out.print("=");
    }
    System.out.print("\n");
    for(int i = 0; i < board[0].length; i++) {
      System.out.print("| " + (i + 1) + " ");
    }
    System.out.print("|\n");
  }
  private void getTurn() {
    Scanner scanner = new Scanner(System.in);
    int input;
    while (true) {
      try {
        input = scanner.nextInt();
        if(board[0][input - 1].equals("| R ") || board[0][input - 1].equals("| Y ")) {
          System.out.println("That column is already filled. Please enter another column number.");
        } else {
          for(int y = 5; y >= 0; y--) {
            if(!board[y][input - 1].equals("| R ") && !board[y][input - 1].equals("| Y ")) {
              board[y][input - 1] = (playerTurn == 1) ? "| R " : "| Y ";
              break;
            }
          }
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Pleaser enter an integer!");
        scanner.next();
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Please enter a valid column number.");
      }
    }
    scanner.close();
  }
  private boolean checkTie() {
    return (turnCount >= 42);
  }
  private boolean checkWin() {
    return (checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin());
  }
  private boolean checkHorizontalWin() {
    for(int y = 5; y >= 0; y--) {
      for(int x = 0; x <= 3; x++) {
        if(board[y][x].equals("| R ") && board[y][x + 1].equals("| R ") && board[y][x + 2].equals("| R ") && board[y][x + 3].equals("| R ")) return true;
        if(board[y][x].equals("| Y ") && board[y][x + 1].equals("| Y ") && board[y][x + 2].equals("| Y ") && board[y][x + 3].equals("| Y ")) return true;
      }
    }
    return false;
  }
  private boolean checkVerticalWin() {
    for(int y = 5; y >= 3; y--) {
      for(int x = 0; x < board[0].length; x++) {
        if(board[y][x].equals("| R ") && board[y - 1][x].equals("| R ") && board[y - 2][x].equals("| R ") && board[y - 3][x].equals("| R ")) return true;
        if(board[y][x].equals("| Y ") && board[y - 1][x].equals("| Y ") && board[y - 2][x].equals("| Y ") && board[y - 3][x].equals("| Y ")) return true;
      }
    }
    return false;
  }
  private boolean checkDiagonalWin() {
    for(int y = 5; y >= 3; y--) {
      for(int x = 0; x <= 3; x++) {
        if(board[y][x].equals("| R ") && board[y - 1][x + 1].equals("| R ") && board[y - 2][x + 2].equals("| R ") && board[y - 3][x + 3].equals("| R ")) return true;
        if(board[y][x].equals("| Y ") && board[y - 1][x + 1].equals("| Y ") && board[y - 2][x + 2].equals("| Y ") && board[y - 3][x + 3].equals("| Y ")) return true;
      }
    }
    for(int y = 5; y >= 3; y--) {
      for(int x = 6; x >= 3; x--) {
        if(board[y][x].equals("| R ") && board[y - 1][x - 1].equals("| R ") && board[y - 2][x - 2].equals("| R ") && board[y - 3][x - 3].equals("| R ")) return true;
        if(board[y][x].equals("| Y ") && board[y - 1][x - 1].equals("| Y ") && board[y - 2][x - 2].equals("| Y ") && board[y - 3][x - 3].equals("| Y ")) return true;
      }
    }
    return false;
  }
  private void swapTurn() {
    playerTurn = (playerTurn == 1) ? 2 : 1;
  }
}
