package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Box extends SuperObject{
	public OBJ_Box() {
	      this.name = "Box";

	      try {
	         this.image = ImageIO.read(this.getClass().getResourceAsStream("/objects/box.png"));
	      } catch (IOException e) {
	         e.printStackTrace();
	      }

	      this.collision = false;
	   }
}
