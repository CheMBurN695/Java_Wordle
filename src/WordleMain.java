import java.util.Scanner;
import java.util.Random;

public class WordleMain
{
    private Scanner scanner;
    private Random random;

    // Constructor
    public WordleMain(Scanner _scanner, Random _random)
    {
        scanner = _scanner;
        random = _random;
    }

    // Main attributes
    public final String[] wordleWords = {"Touch", "Stone", "Brain", "Smart", "Print", "Steam", "Grade"};
    private final int totalGuesses = 6;
    private int guessesRemaning;
    private String currentWord = "";

    // Initialize game loop
    public void InitializeGame()
    {
        // Print welcome messages
        System.out.println("Welcome to Wordle!");
        System.out.println("You have 6 tries to guess the 5-letter word.");
        System.out.println("If you guess the word correctly, you win!");
        System.out.println("If you guess the word incorrectly, you lose!");

        // Initialize game variables
        guessesRemaning = totalGuesses;
        // Get random word
        currentWord = wordleWords[random.nextInt(wordleWords.length)];
        boolean playerWon = false;

        // Main game loop
        while (guessesRemaning > 0)
        {
            System.out.println("Enter your guess: ");
            String guess = scanner.nextLine();

            // Debugging feature for super quick testing
            if (guess.equals("DebugGuess"))
            {
                System.out.println(currentWord);
                continue;
            }

            // Validate guess length
            if (guess.length() != 5)
            {
                System.out.println("Your guess can only be a 5 letter word");
                continue;
            }

            // Validate guess contains only English letters
            if (!IsAlphabetCharacter(guess))
            {
                System.out.println("Your guess can only have English letters");
                continue;
            }

            // Parse and compare the guess with the currentWord
            ParseGuess(currentWord, guess);
            if (guess.equals(currentWord))
            {
                playerWon = true;
                break;
            }
            else
            {
                guessesRemaning--;
                System.out.println("Incorrect guess. You have " + guessesRemaning + " guesses remaining.");
            }
        }

        // Game outcome message
        if (playerWon == false)
            System.out.println("Sorry you lost! Better luck next time! " + "\n ___________________________");
        else
            System.out.println("Congratulations! You guessed the word correctly!");

        // Recursive - restart game
        InitializeGame();
    }

    // Method to compare the guess with the currentWord and print the result
    private void ParseGuess(String hiddenWord, String guess)
    {
        String finalResult = "";

        // Validate lengths match one more time
        if (hiddenWord.length() != guess.length())
        {
            System.out.println("Error - word and guess do not match");
            return;
        }

        // Compare each character in hiddenWord and guess
        for (int i = 0; i < hiddenWord.length(); i++)
        {
            char hiddenChar = hiddenWord.charAt(i);
            char guessChar = guess.charAt(i);
            if (hiddenChar == guessChar)
            {
                finalResult += "√";  // Matching character
            }
            else
            {
                boolean hasChar = false;
                for (int k = 0; k < hiddenWord.length(); k++)
                {
                    if (hiddenWord.charAt(k) == guessChar)
                    {
                        hasChar = true;
                        break;
                    }
                }

                if (hasChar)
                    finalResult += "+";  // Correct letter, wrong position
                else
                    finalResult += "_";  // Incorrect letter
            }
        }

        // Print result of the guess
        System.out.println(finalResult);
    }

    // Method to check if a string contains only alphabetic characters (from the internet)
    private boolean IsAlphabetCharacter(String guess)
    {
        String regex = "^[a-zA-Z]+$";  // Regular expression for alphabetic characters
        return guess.matches(regex);
    }
}
