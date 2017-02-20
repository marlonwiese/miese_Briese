

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import objekte.GameObject;

	public class Main extends BasicGame{
		
		//Variablen 
		
		//Konstanten		
		public static double gamma = 6.67408*Math.pow(10, -11);
		public static double masseErde =5.972*Math.pow(10, 24);
		public static double r1X=0;
		public static double r1Y=6371*Math.pow(10, 3);
		
		public static double beschleunigungY=gamma*masseErde*Math.pow(r1Y, -2)*r1Y*Math.pow(r1Y, -1);
		public static double beschleunigungX = 0;
		
		public static double komponenteX;
		public static double komponenteY;
		public static float komponenteXF ;		
		public static float komponenteYF;
		public static float komponenteXD ;		
		public static float komponenteYD ;		

		
		public static int start_x = 300;
		public static int start_y;
		public static long start=0 ;
		public static Input in;
		
		//Variablen Bilder
		public static Image hintergrund_img;
		public static Image ball_img;
		public static Image foehn_img;
		public static Image block1_img;
		
		public static GameObject ball;
		public static GameObject foehn;
		public static GameObject block1;
		
		
		public static Music bg_music;
		
		public static long end;
		
		
		
		
		public void init(GameContainer gc) throws SlickException {
			
			in = gc.getInput();
			
			
			start = System.currentTimeMillis();
			
			
			hintergrund_img = new Image("img/background0.png");
			ball_img = new Image("img/Ball.png");
			foehn_img = new Image("img/foehn.png");
			block1_img = new Image("img/Block1.png");
			
			ball = new GameObject(ball_img, 200, 30, 0, true, ball_img.getHeight());
			foehn = new GameObject(foehn_img, 60, 400, 0, false, foehn_img.getHeight() );
			block1 = new GameObject(block1_img, 200, 500, 0, false, block1_img.getHeight());
			
			bg_music = new Music("sounds/Track1.wav");
			//bg_music.loop();
		}
		
		
		public void update(GameContainer gc, int arg1) throws SlickException
		{		
			
			if((ball.getHitbox().intersects(block1.getHitbox())))
			{
				ball.setSpeed_y(0);
				start = System.currentTimeMillis();
			}
			else
			{
				ball.setSpeed_y(komponenteYF);
				 end = System.currentTimeMillis();
			}
			
			
			
			//vergange Zeit
			double t = (end - start)*Math.pow(10, -3);
			
			
			if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)&& isBetween(in.getMouseY(),foehn.getPos_y()-50,foehn.getPos_y()+ 50)&& isBetween(in.getMouseX(),foehn.getPos_x()-50,foehn.getPos_x()+50))
			{
				foehn.setPos_y(in.getMouseY());
				foehn.setPos_x(in.getMouseX());

			}
			
			if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)&& isBetween(in.getMouseY(),block1.getPos_y()-50,block1.getPos_y()+ 50)&& isBetween(in.getMouseX(),block1.getPos_x()-50,block1.getPos_x()+50))
			{
				block1.setPos_y(in.getMouseY());
				block1.setPos_x(in.getMouseX());

			}
			
			
			
			
			
			komponenteX = (double)1 / 2 *beschleunigungX *Math.pow(t, 2)+ start_x;
			komponenteY= (double)1 / 2 *beschleunigungY *Math.pow(t, 2)+  start_y;
			
			komponenteXF =(float)komponenteX;
			komponenteYF =(float)komponenteY;
			
			

			
			
			
			
			
			
			
			if(komponenteYF >= 698)
			{
				komponenteYF=698;
			}
			
			
			//System.out.println(in.getMouseX());			
			
			block1.update();
			ball.update();
			foehn.update();
		}
		
		
		


		public void render(GameContainer gc, Graphics g) throws SlickException {

			hintergrund_img.draw(0, 0);
			ball.getImg().drawCentered(ball.getPos_x(), ball.getPos_y());
			foehn.getImg().drawCentered(foehn.getPos_x(), foehn.getPos_y());
			block1.getImg().drawCentered(block1.getPos_x(), block1.getPos_y());
			g.setColor(Color.blue);
			g.draw(block1.getHitbox());
			g.draw(ball.getHitbox());
			g.draw(foehn.getHitbox());
		}

		public Main(String title) {
			super(title);
		}	
		
		public static boolean isBetween(float value,float min, float max)
		{
			return ((value > min) && (value < max));
		}
		
		
		
		public static void main(String[] args) {
			try {
				AppGameContainer app = new AppGameContainer(new Main("Crazy Machines"));
				app.setDisplayMode(1240, 698, false);
				app.setTargetFrameRate(60);
				app.setShowFPS(false);
				app.setVSync(true);
				app.start();
				}
			catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			



			
		}

		
		
		
}
