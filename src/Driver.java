import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Driver 
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{	
		List<Integer>list = new ArrayList<Integer>();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter numbers:");
		
		int a = keyboard.nextInt();
		
		while(a != -1) {
			list.add(a);
			a = keyboard.nextInt();
			}
		
		int sum = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		
		list.sort(null);
		int e = 0;
		for (int i = list.size() - 1; i >= 0 ; i--) {
			
			if (list.get(i) + list.get(e) == sum) {
				System.out.print(list.get(i) + ", " + list.get(e));
			}
			else if (list.get(i) + list.get(e) < sum) {
				e++;
			}
		}
		
		
		
			
		}
		
		
		
		
		
		
        
	}

