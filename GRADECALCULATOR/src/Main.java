import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner n = new Scanner(System.in);
		int total = 0;
		
		
		
		String Subject[] = {"TAMIL","ENGLISH","MATHS","SCIENCE","SS"};
		int Mark[] = new int[Subject.length];
		String Grades[] = new String[Subject.length];
		
		
		
		for(int i = 0 ; i < Subject.length ; i++) {
			System.out.print("ENTER MARK OF "+Subject[i]+" : ");
			int sub = n.nextInt();
			Mark[i] = sub;
			total += sub;
		}
		
		
		double Average = total/Subject.length;
		
		
		for(int i = 0 ; i < Subject.length ; i++) {
			if(Mark[i] >= 95) {
				Grades[i] = "O";
			}
			else if(Mark[i] <= 94 & Mark[i] >= 90) {
				Grades[i] = "A+";
			}
			else if(Mark[i] <= 89 & Mark[i] >= 85) {
				Grades[i] = "A";
			}
			else if(Mark[i] <= 84 & Mark[i] >= 80) {
				Grades[i] = "B+";
			}
			else if(Mark[i] <= 79 & Mark[i] >= 75) {
				Grades[i] = "B";
			}
			else if(Mark[i] <= 74 & Mark[i] >= 70) {
				Grades[i] = "C+";
			}
			else if(Mark[i] <= 69 & Mark[i] >= 65) {
				Grades[i] = "C";
			}
			else if(Mark[i] <= 64 & Mark[i] >= 60) {
				Grades[i] = "D+";
			}
			else if(Mark[i] <= 59 & Mark[i] >= 55) {
				Grades[i] = "D";
			}
			else if(Mark[i] <= 54 & Mark[i] >= 50) {
				Grades[i] = "E";
			}
			else {
				Grades[i] = "F";
			}
		}
		
		System.out.println("\n");
		
		System.out.println("STUDENTS GRADE CALCULATOR");
		System.out.println();
		
		System.out.println("TOTAL MARKS :"+total);
		System.out.println();
		
		System.out.println("AVERAGE MARKS :"+Average);
		System.out.println();
		
		
		for(int i = 0 ; i < Subject.length ; i++) {
			System.out.println(Subject[i] + "\t" + Mark[i] + "\t" + Grades[i]);
		}
		
	}

}