/**
 * Author: Sanjay Suthakaran
 * Date: May 20, 2023
 * Description:
 * - This simple program performs the algorithm for transitive closure of a relation, known as Warshall's Algorithm.
 * - The user must input a .txt file of correct format: an integer representing the cardinality of the set (ex. 5x5 matrix would be 5), followed by the matrix consisting of 1s and 0s on the subsequent lines.
 * - A sample file of the format is provided.
 * - The program will then compute the algorithm, showing the matrix for each pass.
 * - This program makes use of Brock University's BasicIO from the Brock.jar library for the ASCIIDataFile class.
 */

//import
import BasicIO.*;

public class Main{

    //instance variable for printing a divider
    static String divider = "----------------------------------------";

    public Main(){

        //loading up .txt file, and calling the create method to instantiate it into a 2D array.
        ASCIIDataFile file = new ASCIIDataFile();
        char[][] newWarshall0 = createArray(file);

        //prints W0 and calls recursive algorithm.
        System.out.println("Warshall's algorithm\n" + divider );
        System.out.println("here is your starting array.");
        printArray(newWarshall0);
        algorithm(newWarshall0, 0);
    }

    /**
     * This method creates W0, the starting array to be passed into the algorithm method.
     * @param file - the user provided .txt file containing the starting array, and dimension integer.
     * @return - returns a 2D array containing W0 to be used for the algorithm.
     */
    private static char[][] createArray(ASCIIDataFile file){

        //the first line in the .txt file should be an integer representing the dimensions of the array, which is a square shape (hence the need for only one variable).
        int dimension = file.readInt();

        //creates new array using the provided dimensions, then reads the matrix in the .txt file into the array.
        char[][] newWarshall0 = new char[dimension][dimension];
        for (int i = 0; i < dimension; i++){
            String temp = file.readString();
            newWarshall0[i] = temp.toCharArray();
        }

        return newWarshall0;
    }


    /**
     * This method is used to print out the provided 2D array to the console.
     * @param printThisWarshall - 2D array to be printed
     */
    private static void printArray(char[][] printThisWarshall){
        for (int i = 0; i < printThisWarshall.length; i++){
            for (int j = 0; j < printThisWarshall[i].length; j++){
                System.out.print(printThisWarshall[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * This is a recursive method that computes Warshall's algorithm just as how it's done by hand.
     * @param currentWarshall - 2D array to be used to compute the next Warshall array
     * @param warshallIteration - an integer used to determine which iteration of the algorithm we are currently on
     */
    private static void algorithm (char[][] currentWarshall, int warshallIteration){

        //if the warshall iteration is equal to the length (cardinality) of the set, we are finished the algorithm.
        if (warshallIteration == currentWarshall.length){
            return;
        }

        //creates a new array using the same dimensions as the current array.
        char[][] newWarshall = new char[currentWarshall.length][currentWarshall.length];

        //vertical traversal
        for (int i = 0; i < currentWarshall.length; i++){
            newWarshall[i][warshallIteration] = currentWarshall[i][warshallIteration];
        }

        //horizontal traversal
        for (int i = 0; i < currentWarshall.length; i++){
            newWarshall[warshallIteration][i] = currentWarshall[warshallIteration][i];
        }

        //algorithm; checks if the current row/columns both contain a 1, or if the current space in the array contains a 1, if so, a 1 is added to the new array. if not, then it becomes a 0.
        for (int i = 0; i < currentWarshall.length; i++){
            for (int j = 0; j < currentWarshall[i].length; j++){
                if ((currentWarshall[i][j] == '1') || (currentWarshall[i][warshallIteration] == '1' && currentWarshall[warshallIteration][j] == '1')){
                    newWarshall[i][j] = '1';
                }
                else{
                    newWarshall[i][j] = '0';
                }
            }
        }

        //prints the result and recalls the method with the iteration increased by 1.
        System.out.println("\n" + divider + "\n\nHere is W" + (warshallIteration + 1));
        printArray(newWarshall);
        algorithm(newWarshall,warshallIteration + 1);
    }

    //main method
    public static void main(String[] args) {
        new Main();
    }

}












