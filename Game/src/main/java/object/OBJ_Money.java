package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Money extends SuperObject{
	public OBJ_Money() {
		
		name = "Money";
		try {
			image= ImageIO.read(getClass().getResource("/objects/blood.png"));
		} catch(IOException e) {
			e.printStackTrace();
			
		}
	}
}
