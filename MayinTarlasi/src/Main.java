import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
        System.out.print("Sat�r say�s�n� giriniz: ");
        int rows = scanner.nextInt();
        System.out.print("S�tun say�s�n� giriniz: ");
        int cols = scanner.nextInt();

        MineSweeper game = new MineSweeper(rows, cols);
        game.play();
	}
}
