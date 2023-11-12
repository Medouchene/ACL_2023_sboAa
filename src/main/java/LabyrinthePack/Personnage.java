package LabyrinthePack;
import java.util.*;
public class Personnage {
	Plateau p =new Plateau();
	
	
	double r=Math.random();
	  double r1=Math.random();
	 

	
	Random random = new Random();

	int vXMonstre = random.nextInt(11) - 10;
	int vYMonstre = random.nextInt(11) - 10;
   
	  int monstreX1 = (int) (r1*(p.largeur-20));
	  int monstreY1 = (int) (r*(p.longueur-20));

	public void moveMonstre1() {
		int neWmonstreX ;
		int neWmonstreY ;
		do {
			neWmonstreX = monstreX1 + (vXMonstre);
			
		}while ((neWmonstreX>p.maxX-30 || neWmonstreX<p.minX) );
		do {
			neWmonstreY = monstreY1 + (vXMonstre);
			
		}while ((neWmonstreY>p.maxX-20 || neWmonstreY<p.minY+10) );
		monstreX1 = neWmonstreX;
    	monstreY1 = neWmonstreY;
	}
	
}


