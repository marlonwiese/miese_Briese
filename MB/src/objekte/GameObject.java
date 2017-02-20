package objekte;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class GameObject {
	
	
	protected Image img;
	protected int pos_x;
	protected int pos_y;
	protected Shape hitbox;
	protected int hitbox_size;
	protected float speed_y;
	
	public static double gamma = 6.67408*Math.pow(10, -11);
	public static double masseErde =5.972*Math.pow(10, 24);
	public static double r1Y=6371*Math.pow(10, 3)*1/4;
	public static double beschleunigungY=gamma*masseErde*Math.pow(r1Y, -2)*r1Y*Math.pow(r1Y, -1);
	
	
	
	public GameObject(Image img,int x, int y, float speed_y, boolean circle, int hitbox_size)
	{
		this.img = img;
		this.pos_x = x;
		this.pos_y = y;
		this.speed_y = speed_y;
		this.hitbox_size = hitbox_size;
		
		if(circle)
		{
			this.hitbox = new Circle(x , y ,hitbox_size/2);
		}
		else
		{
			this.hitbox = new Rectangle(x , y ,hitbox_size, hitbox_size);
		}	
	
	}
	
		public void update()
		{
			this.pos_y += speed_y;
			
			this.hitbox.setLocation(this.pos_x - this.hitbox_size / 2, this.pos_y - this.hitbox_size / 2);

			
		
		
		
		}
	
		public Image getImg()
		{
			return this.img;
		
		}
	
		public Shape getHitbox()
		{
			return this.hitbox;
		}
	

		public int getPos_x() 
		{
			return pos_x;
		}

		
		public int getPos_y() 
		{
			return pos_y;
		}

		public void setPos_y(int pos_y) 
		{
			this.pos_y = pos_y;
		}
		
		public float getSpeed_y() {
			return speed_y;
		}

		public void setSpeed_y(float speed_y) {
			this.speed_y = speed_y;
		}

		public void setPos_x(int pos_x)
		{
			this.pos_x = pos_x;
		}

}
