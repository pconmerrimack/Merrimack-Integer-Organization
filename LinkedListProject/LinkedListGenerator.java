import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Serves as the main driver of the LinkedList generator.
 * Has separate functions for collection of input, validating/formatting input into LinkedList.
 *
 * Main serves as the driver as this is the only class currently being utilized.
 */
public class LinkedListGenerator {
    /**
     * collectInput collects a list from the user, and formats it for the createLinkedList function.
     * This is done by collecting a String from the User, removing the spaces, and then creating an array
     * of Strings each representing an individual integer.
     *
     * Areas of Reuse of External Libraries: Use of Java.util.Scanner allows code to be
     * read from the terminal.
     *
     * @return numbers (String[]): A list of strings to represent integers to be sorted.
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
     * createLinkedList utilizes the Java Collections Frame work to process
     * an array of Strings of integers, and reformat them into a sorted Linked List.
     * This is accomplished by iterating through each String, and translating them
     * to an integer. This integer is then added to a growing LinkedList,
     * which sorts the numbers as they are added through comparison of each element
     * via the ListIterator class also within the Collections Framework.
     *
     * Areas of reuse of code: LinkedList and ListIterator Packages
     * LinkedList: LinkedList Class from Collections Frame work used to store datapoints leading from one
     * integer to the next.
     * ListIterator: Used to iterate through the LinkedList Class, add new elements, and compare an adjacent
     * data point with a given integer.
     * @param numbers (String[]): An array of strings to be translated to integers, prior to sorting
     *                and placement in a LinkedList.
     * @return linkedNumbers / nullNumbers (LinkedList(int)): If a list is correctly sorted (indicating valid user
     * input) linkednumbers will be returned as a sorted linkedList. If user input is not valid, nullNumbers will be
     * returned instead.
     */
    public static LinkedList<Integer> createLinkedList (String[] numbers) {
        boolean allNums = true;
        // Reuse of LinkedList
        LinkedList<Integer> linkedNumbers = new LinkedList<>();
        for (int i = 0; i < numbers.length; i++){
            try {
                int nextNum = Integer.parseInt(numbers[i]);
                // Reuse of LinkedList
                if ((linkedNumbers.size()) == 0)  {
                    linkedNumbers.addFirst(nextNum);
                }
                else {
                    // Reuse of ListIterator
                    ListIterator<Integer> it = linkedNumbers.listIterator();
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
            return linkedNumbers;
        }
        else {
            // Reuse of LinkedList
            LinkedList<Integer> nullNumbers = new LinkedList<>();
            return nullNumbers;

        }

    }

    /**
     * Main serves as the driver to this program. Calls for an input via collectInput,
     * then processes the input via createLinkedList. Depending on returns, either reports
     * LinkedList elements, or reports failure to process list if user input is invalid.
     *
     * LinkedList Class reused in this aspect of the code to both hold the Linkedlist
     * returned from the createLinkedList function, as well as print the LinkedList to the terminal.
     * @param args (Default Parameter, not used)
     */
    public static void main(String[] args) {
        String[] numbers = collectInput();
        // Reuse of LinkedList
        LinkedList<Integer> linkedNumbers = createLinkedList(numbers);
        if (linkedNumbers.size() == 0){
            System.out.println("Invalid characters entered. Only integers accepted.");
        }
        else{
            System.out.println(linkedNumbers);
        }
    }
}