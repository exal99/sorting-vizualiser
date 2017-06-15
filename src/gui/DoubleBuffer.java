package gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public abstract class DoubleBuffer extends Component {
	private static final long serialVersionUID = 1L;
	
	private int bufferWidth;
	private int bufferHeight;
	private Image bufferImage;
	private Graphics bufferGraphics;
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	/**
	 * Clears the old buffer, creates a new one of screen and then pastes it
	 * onto the visible screen again.
	 */
	public void paint(Graphics g) {
		if (bufferWidth != getSize().width || bufferHeight != getSize().height ||
			bufferImage == null || bufferGraphics == null) {
			resetBuffer();
		}
		if(bufferGraphics!=null){
	        //this clears the offscreen image, not the onscreen one
	        bufferGraphics.clearRect(0,0,bufferWidth,bufferHeight);

	        //calls the paintbuffer method with 
	        //the offscreen graphics as a parameter
	        paintBuffer(bufferGraphics);

	        //we finally paint the offscreen image onto the onscreen image
	        g.drawImage(bufferImage,0,0,this);
	    }
	}
	
	/**
	 * This metod is called by the paint method to paint the buffer.
	 * This method will be used as the paint method in all subclasses
	 * @param g the Graphics object.
	 */
	public abstract void paintBuffer(Graphics g);
	
	/**
	 * Clears the buffer and creates a new one
	 */
	private void resetBuffer(){
	    // always keep track of the image size
	    bufferWidth=getSize().width;
	    bufferHeight=getSize().height;

	    //    clean up the previous image
	    if(bufferGraphics!=null){
	        bufferGraphics.dispose();
	        bufferGraphics=null;
	    }
	    if(bufferImage!=null){
	        bufferImage.flush();
	        bufferImage=null;
	    }
	    System.gc();

	    //    create the new image with the size of the panel
	    bufferImage=createImage(bufferWidth,bufferHeight);
	    bufferGraphics=bufferImage.getGraphics();
	}

}
