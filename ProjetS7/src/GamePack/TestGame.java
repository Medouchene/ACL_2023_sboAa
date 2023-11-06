package GamePack;
import java.util.*;
public class TestGame {
	
	public static void main(String[] args) {
		Plateau p = new Plateau();
		int x = p.getX();
		int y = p.getY();
		
		Position pos = new Position(x,y);
		boolean GameOver=false;
		int i=0;
		do {
			i++;
			pos.Coordonnee();
			//System.out.print(p.surface);
			Scanner sc= new Scanner(System.in);
			System.out.println("ecrire Command (L/R/U/D/S) : ");
			String command=sc.next();
			pos.Bouger(command);
			if (i==4) GameOver=true;
			
		}while(!GameOver);
		
		/*pos.Coordonnee();
		//System.out.print(p.surface);
		Scanner sc= new Scanner(System.in);
		System.out.println("ecrire Command (L/R/U/D/S) : ");
		String command=sc.next();
		pos.Bouger(command);
		pos.Coordonnee();*/

	}
}
