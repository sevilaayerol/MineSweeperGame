import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
        System.out.print("Satýr sayýsýný giriniz: ");
        int rows = scanner.nextInt();
        System.out.print("Sütun sayýsýný giriniz: ");
        int cols = scanner.nextInt();

        MineSweeper game = new MineSweeper(rows, cols);
        game.play();
	}
}
