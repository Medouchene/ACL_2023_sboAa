package LabyrinthePack;

public class Position {
	
	Plateau p = new Plateau();
	
	int x; int y;
	
	
	Position(int a, int b){
		this.x=a;
		this.y=b;
	}
	
	
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	void Coordonnee() {
		System.out.print("x : ");
		System.out.print(getX());
		System.out.print(" et y : ");
		System.out.print(getY());
		System.out.println();

	}
	public boolean MovValide( int a, int b) {
		int xf=this.x+a;
		int yf=this.y+b;
		
		if (xf>0 && xf<p.longueur && yf>0 && yf<p.largeur) {
			return true;
		}
		else {
			return false;
		}
			
	}
	
	public void Bouger(String commande) {
		try {
			switch(commande) {
				case "L":
					if (MovValide(-1,0)) {
						this.x+=-1;
						this.y+=0;		
						p.setX(this.x);
						p.setY(this.y);
					}
					break;
					
				case "R":
					if (MovValide(1,0)) {
						this.x+=1;
						this.y+=0;
						p.setX(this.x);
						p.setY(this.y);
					}
					break;

				case "U":
					if (MovValide(0,-1)) {
						this.x+=0;
						this.y+=-1;
						p.setX(this.x);
						p.setY(this.y);
					}
					break;

				case "D":
					if (MovValide(0,1)) {
						this.x+=0;
						this.y+=1;
						p.setX(this.x);
						p.setY(this.y);
					}
					break;

				case "S":
					break;

			}
		} catch (Exception e) {
			System.out.println("You can't ! ");
		}
	}
}
