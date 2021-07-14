import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 */

/**
 * @author haruka
 *
 */
public class DrawPanel extends JPanel {
	int currentWidth=10;
	Color currentColor=Color.black;
	Color pen_after_eraser_Color=Color.black;
	BufferedImage bufferImage=null;
	Graphics2D bufferGraphics=null;
	Image img;
	int font_num=0;

	//引数で指定された太さに設定
	public void setPenWidth(int newWidth) {
		currentWidth = newWidth;
	}

	//引数で指定された色に設定
	public void setPenColor(Color newColor) {
		currentColor = newColor;
	}

	public void spoitColor(int x, int y) {
		if(null==bufferGraphics) {
		 	this.createBuffer(this.getWidth(),this.getHeight());  //バッファをまだ作ってなければ作る
		}
		currentColor=new Color(bufferImage.getRGB(x, y-50));
	}

	public void createBuffer(int width, int height) {
	       //バッファ用のImageとGraphicsを用意する
			bufferImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
			bufferGraphics=bufferImage.createGraphics(); //getGraphicsと似ているが、戻り値がGraphics2D。
			//bufferGraphics.translate(100, 100);
			bufferGraphics.setBackground(Color.white);
			bufferGraphics.clearRect(0, 0, width, height); //バッファクリア
		}
	public void drawLine(int x1, int y1, int x2, int y2){
		if(null==bufferGraphics) {
		 	this.createBuffer(this.getWidth(),this.getHeight());  //バッファをまだ作ってなければ作る
			//this.createBuffer(500,300);
		}

		repaint();//再描画するためpaintComponentを呼び出す。


		bufferGraphics.setColor(currentColor);
		//太さがcurrentWidth の線を描く．線の両端は丸くする．
		bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
		bufferGraphics.drawLine(x1, y1-50, x2, y2-50);
	}

	public void drawCircle(int x, int y) {
		if(null==bufferGraphics) {
		 	this.createBuffer(this.getWidth(),this.getHeight());  //バッファをまだ作ってなければ作る
		}
		//bufferGraphics.drawLine(x1, y1-30, x2, y2-30); // バッファに描画する

		bufferGraphics.setColor(currentColor);
		bufferGraphics.setStroke(new BasicStroke(5 ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
		bufferGraphics.drawOval( x-40, y-90, 100+currentWidth, 100+currentWidth);
		repaint();//再描画するためpaintComponentを呼び出す。
	}
	public void drawSquare(int x, int y) {
		if(null==bufferGraphics) {
		 	this.createBuffer(this.getWidth(),this.getHeight());  //バッファをまだ作ってなければ作る
		}
		//bufferGraphics.drawLine(x1, y1-30, x2, y2-30); // バッファに描画する

		bufferGraphics.setColor(currentColor);
		bufferGraphics.setStroke(new BasicStroke(5 ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
		bufferGraphics.drawRect( x-40, y-90, 100+currentWidth, 100+currentWidth);
		repaint();//再描画するためpaintComponentを呼び出す。
	}

	public void drawBeima(int x, int y) {
		if(null==bufferGraphics) {
		 	this.createBuffer(this.getWidth(),this.getHeight());  //バッファをまだ作ってなければ作る
		}

		bufferGraphics.setColor(Color.black );
		bufferGraphics.setStroke(new BasicStroke(5 ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
		int xb=x-40;
		int yb=y-85;

		bufferGraphics.drawOval(xb,yb, 140, 90 );
		bufferGraphics.fillOval(23+xb, 33+yb, 25, 25);
		bufferGraphics.fillOval(93+xb, 33+yb, 25, 25);
		bufferGraphics.drawLine(28+xb, 44+yb, 100+xb, 44+yb);
		repaint();
	}


	public void drawText(int x, int y, String s) {
		if(null==bufferGraphics) {
		 	this.createBuffer(this.getWidth(),this.getHeight());  //バッファをまだ作ってなければ作る
		}
		/*Font fn = new Font("Serif" , Font.PLAIN , 60);//標準
	    Font fi = new Font("Serif" , Font.ITALIC , currentWidth);//斜体
	    Font fb = new Font("Serif" , Font.BOLD , currentWidth);//強調
	    Font fh = new Font("HG行書体" , Font.PLAIN , currentWidth);//和風*/
		if(font_num==0) {
			bufferGraphics.setFont(new Font("Serif" , Font.PLAIN , currentWidth));
		}
		else if(font_num==1) {
			bufferGraphics.setFont(new Font("Serif" , Font.ITALIC , currentWidth));
		}
		else if(font_num==2) {
			bufferGraphics.setFont(new Font("Serif" , Font.BOLD , currentWidth));
		}

		bufferGraphics.setColor(currentColor);
		bufferGraphics.drawString(s, x, y-40);
		repaint();
	}

	public void drawPictureStamp(int x, int y, File file) {
		if(null==bufferGraphics) {
		 	this.createBuffer(this.getWidth(),this.getHeight());  //バッファをまだ作ってなければ作る
		}

		try {
			img = ImageIO.read(file);
		} catch(Exception e){
			System.out.println("Error: reading file="+file.getName());
			return;
		}

		int width = img.getWidth(this);
		int height = img.getHeight(this);
		int ratio_height = (100*height)/width;

		//System.out.println(width);
		//System.out.println(height);
		//System.out.println(ratio);

		bufferGraphics.drawImage(img, x-50, y-40-(ratio_height/2), 100, ratio_height, this);

		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);//他に描画するものがあるかもしれないので親を呼んでおく
		if(null!=bufferImage) g.drawImage(bufferImage, 0,0,this);//バッファを表示する
	}

	public void saveFile(File file2save) {
		try {
			ImageIO.write(bufferImage, "jpg", file2save);
		} catch (Exception e) {
			System.out.println("Error: writing file="+file2save.getName());
			return;
		}
	}

	public void openFile(File file2open){
		BufferedImage pictureImage;
		try {
			pictureImage = ImageIO.read(file2open);
		} catch(Exception e){
			System.out.println("Error: reading file="+file2open.getName());
			return;
		}
		//画像に合わせたサイズでbufferImageとbufferGraphicsを作りなおして画像を読み込む
			//ImageIO.readの戻り値をbufferImageに代入するのでは駄目みたいです。
		this.createBuffer(pictureImage.getWidth(),pictureImage.getHeight());
		bufferGraphics.drawImage(pictureImage,0,0,this);
		repaint(); //画像を表示するためにpaintComponentを呼ぶ
	}

}
