import java.util.Random;
import java.util.Scanner;

public class CrorepathiGame {

    // Game Variables
    static String[] questions = {
        "What is the capital of France?",
        "What is 2 + 2?",
        "What is the largest ocean on Earth?",
        "What is the chemical symbol for gold?",
        "Who wrote 'Romeo and Juliet'?",
        "What is the hardest natural substance on Earth?",
        "What is the currency of Japan?",
        "Which planet is known as the Red Planet?",
        "What is the largest mammal in the world?",
        "What is the main ingredient in guacamole?"
    };

    static String[][] options = {
        {"Berlin", "Madrid", "Paris", "Rome"},
        {"3", "4", "5", "6"},
        {"Atlantic", "Indian", "Arctic", "Pacific"},
        {"Au", "Ag", "Pb", "Fe"},
        {"Mark Twain", "Charles Dickens", "William Shakespeare", "Ernest Hemingway"},
        {"Gold", "Iron", "Diamond", "Quartz"},
        {"Yuan", "Won", "Yen", "Dollar"},
        {"Earth", "Mars", "Jupiter", "Saturn"},
        {"Elephant", "Blue Whale", "Giraffe", "Shark"},
        {"Tomato", "Avocado", "Pepper", "Onion"}
    };

    static int[] correctAnswers = {2, 1, 3, 0, 2, 2, 2, 1, 1, 1};
    static int[] rewards = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};

    static boolean audienceLifelineUsed = false;
    static boolean phoneLifelineUsed = false;
    static boolean fiftyFiftyLifelineUsed = false;

    static int totalReward = 0;
    static int currentQuestionIndex = 0;
    static boolean gameOver = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (currentQuestionIndex < questions.length && !gameOver) {
            displayQuestion(currentQuestionIndex);
            System.out.print("Choose an option (1-4) or lifeline (a-c): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "a":
                    if (!audienceLifelineUsed) {
                        useAudienceLifeline(currentQuestionIndex);
                    } else {
                        System.out.println("Audience Poll lifeline already used.");
                    }
                    break;

                case "b":
                    if (!phoneLifelineUsed) {
                        usePhoneLifeline(currentQuestionIndex);
                    } else {
                        System.out.println("Phone a Friend lifeline already used.");
                    }
                    break;

                case "c":
                    if (!fiftyFiftyLifelineUsed) {
                        useFiftyFiftyLifeline(currentQuestionIndex);
                    } else {
                        System.out.println("50-50 lifeline already used.");
                    }
                    break;

                default:
                    try {
                        int answerIndex = Integer.parseInt(input) - 1;
                        if (answerIndex >= 0 && answerIndex < 4) {
                            if (answerIndex == correctAnswers[currentQuestionIndex]) {
                                totalReward += rewards[currentQuestionIndex];
                                System.out.println("✅ Correct! Your total reward is: ₹" + totalReward);
                                currentQuestionIndex++;
                            } else {
                                System.out.println("❌ Wrong answer! Game over. Your total reward is: ₹" + totalReward);
                                gameOver = true;  // End the game after a wrong answer
                            }
                        } else {
                            System.out.println("Invalid input. Please select a valid option.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number or lifeline option.");
                    }
                    break;
            }
        }

        if (!gameOver && currentQuestionIndex == questions.length) {
            System.out.println("🎉 Congratulations! You've completed the game with a total reward of: ₹" + totalReward);
        }
        scanner.close();
    }

    // Display question and options
    static void displayQuestion(int index) {
        System.out.println("\nQuestion " + (index + 1) + ": " + questions[index]);
        for (int i = 0; i < options[index].length; i++) {
            System.out.println((i + 1) + ": " + options[index][i]);
        }
        displayLifelines();
    }

    // Display available lifelines
    static void displayLifelines() {
        System.out.println("Lifelines available:");
        if (!audienceLifelineUsed) System.out.println("a: Audience Poll");
        if (!phoneLifelineUsed) System.out.println("b: Phone a Friend");
        if (!fiftyFiftyLifelineUsed) System.out.println("c: 50-50");
    }

    // Audience Lifeline
    static void useAudienceLifeline(int index) {
        audienceLifelineUsed = true;
        System.out.println("📊 Audience Poll suggests: " + options[index][correctAnswers[index]]);
    }

    // Phone a Friend Lifeline
    static void usePhoneLifeline(int index) {
        phoneLifelineUsed = true;
        System.out.println("📞 Your friend suggests: " + options[index][correctAnswers[index]]);
    }

    // 50-50 Lifeline
    static void useFiftyFiftyLifeline(int index) {
        fiftyFiftyLifelineUsed = true;
        System.out.println("Remaining options: ");
        System.out.println("1: " + options[index][correctAnswers[index]]);
        
        Random random = new Random();
        int wrongOptionIndex;
        do {
            wrongOptionIndex = random.nextInt(4);
        } while (wrongOptionIndex == correctAnswers[index]);

        System.out.println("2: " + options[index][wrongOptionIndex]);
    }
}
