import java.util.ListIterator;
import java.util.Scanner;
import java.util.Stack;

/**
 * Serves as the main driver of the Stack generator.
 * Has separate functions for collection of input, validating/formatting input into a Stack.
 *
 * Main serves as the driver as this is the only class currently being utilized.
 *
 *
 * UPDATES FOR MAINTENANCE ASSIGNMENT: Updated description as output is being utilized for a stack.
 */
public class StackGenerator {
    /**
     * collectInput collects a list from the user, and formats it for the createStack function.
     * This is done by collecting a String from the User, removing the spaces, and then creating an array
     * of Strings each representing an individual integer.
     *
     * Areas of Reuse of External Libraries: Use of Java.util.Scanner allows code to be
     * read from the terminal.
     *
     * @return numbers (String[]): A list of strings to represent integers to be sorted.
     *
     * UPDATES FOR MAINTENANCE ASSIGNMENT: Updated description as output is being utilized for a Stack.
     */
    public static String[] collectInput() {
        // Reuse of Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a list of numbers separated by commas: ");
        // Reuse of Scanner
        String numberList = scanner.nextLine();
        numberList = numberList.replace(" ", "");
        String numbers[] = numberList.split(",");
        return numbers;
    }

    /**
     * createStack utilizes the Java Collections Framework to process
     * an array of Strings of integers, and reformat them into a sorted Stack.
     * This is accomplished by iterating through each String, and translating them
     * to an integer. This integer is then added to a growing Stack,
     * which sorts the numbers as they are added through comparison of each element
     * via the ListIterator class also within the Collections Framework.
     *
     * Areas of reuse of code: Stack and ListIterator Packages
     * Stack: Stack Class from Collections Framework used to store datapoints leading from one
     * integer to the next.
     * ListIterator: Used to iterate through the Stack Class, add new elements, and compare an adjacent
     * data point with a given integer.
     * @param numbers (String[]): An array of strings to be translated to integers, prior to sorting
     *                and placement in a Stack.
     * @return stackedNumbers / nullNumbers (Stack(int)): If a list is correctly sorted (indicating valid user
     * input) stackedNumbers will be returned as a sorted Stack. If user input is not valid, nullNumbers will be
     * returned instead.
     *
     * UPDATES FOR MAINTENANCE ASSIGNMENT: Updated the class utilized to store the integers to be a Stack as requested.
     * All nomenclature of variables has been updated to reflect the change to a Stack being the main storage
     * of the integers. ListIterator usage remains unchanged due to its compatability with Stacks.
     * Initialization of the Stack variable reflects the greatest change from the original program.
     * Additionally,.push() is utilized rather than .addFirst(), and the nullNumbers is also returned as an
     * empty Stack rather than a LinkedList.
     */
    public static Stack<Integer> createStack (String[] numbers) {
        boolean allNums = true;
        // Reuse of Stack
        Stack<Integer> stackedNumbers = new Stack<>();
        for (int i = 0; i < numbers.length; i++){
            try {
                int nextNum = Integer.parseInt(numbers[i]);
                // Reuse of Stack
                if ((stackedNumbers.size()) == 0)  {
                    stackedNumbers.push(nextNum);
                }
                else {
                    // Reuse of ListIterator
                    ListIterator<Integer> it = stackedNumbers.listIterator();
                    int headNum = it.next();
                    if (nextNum < headNum) {
                        it.previous();
                        it.add(nextNum);
                    }
                    else {
                        boolean added = false;
                        while (it.hasNext()) {
                            if (nextNum < it.next()) {
                                it.previous();
                                it.add(nextNum);
                                added = true;
                                break;
                            }
                        }
                        if (added == false) {
                            it.add(nextNum);
                        }
                    }

                }
            } catch (Exception failure) {
                allNums = false;

                break;
            }
        }
        if(allNums){
            return stackedNumbers;
        }
        else {
            // Reuse of Stack
            Stack<Integer> nullNumbers = new Stack<>();
            return nullNumbers;

        }

    }

    /**
     * Main serves as the driver to this program. Calls for an input via collectInput,
     * then processes the input via createStack. Depending on returns, either reports
     * Stack elements, or reports failure to process Stack if user input is invalid.
     *
     * Stack Class reused in this aspect of the code to both hold the Stack
     * returned from the createStack function, as well as print the Stack to the terminal.
     * @param args (Default Parameter, not used)
     *
     *
     */
    public static void main(String[] args) {
        String[] numbers = collectInput();
        // Reuse of Stack
        Stack<Integer> stackedNumbers = createStack(numbers);
        if (stackedNumbers.size() == 0){
            System.out.println("Invalid characters entered. Only integers accepted.");
        }
        else{
            System.out.println(stackedNumbers);
        }
    }
}
