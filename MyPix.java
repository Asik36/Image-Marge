import java.awt.Point;

public class MyPix {
	public MyPix(int pixel, Point location) {
		this.pixel = pixel;
		this.red = (pixel & 0xff0000) >> 16;
		this.green = (pixel & 0xff00) >> 8;
		this.blue = pixel & 0xff;;
		this.location = location;
	}

	public int red;
	public int green;
	public int blue;
	public Point location;
	public int pixel;
	public String toString()
	{
		return "pix = "+this.pixel+" r = "+red+" g = "+this.green+" blue = "+this.blue+" at "+this.location.x+","+this.location.y;
	}

}


