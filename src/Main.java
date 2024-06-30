//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        // Initialize scanner to read user input and random to generate random numbers
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Create instance of WordleMain class, passing scanner and random as parameters
        WordleMain wordleMain = new WordleMain(scanner, random);

        // Call InitializeGame method on wordleMain instance
        wordleMain.InitializeGame();
    }
}