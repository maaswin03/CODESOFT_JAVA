import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner n = new Scanner(System.in);
		Random rand = new Random();
		
		int target = rand.nextInt(100);
		
		int count = 0;
		
		for(int i = 0 ; i < 20 ; i++ ) {
			System.out.print("ENTER THE VALUE : ");
			int value = n.nextInt();
			
			if(value == target) {
				System.out.println("\n");
				System.out.println("GUESS IS CORRECT");
				break;
			}
			else if(value > target) {
				System.out.println("GUESS IS TOO HIGH");
				count++;
			}
			else if(value < target) {
				System.out.println("GUESS IS TOO LOW");
				count++;
			}
		}
		
		System.out.println("THE TOTAL SCORE IS : "+((20 - count)*5));
	}
}