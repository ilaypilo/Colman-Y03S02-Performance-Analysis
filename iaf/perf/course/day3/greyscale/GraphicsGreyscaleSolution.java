package iaf.perf.course.day3.greyscale;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import iaf.perf.course.day3.greyscale.GreyscaleEx.GreyscaleConverter;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class GraphicsGreyscaleSolution implements GreyscaleConverter {

	@Override
	public BufferedImage convert(BufferedImage img) 
	{
		BufferedImage greyscale = new BufferedImage(
										img.getWidth(), 
										img.getHeight(), 
										BufferedImage.TYPE_BYTE_GRAY);
		
		Graphics g = greyscale.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
        
		return greyscale;
	}

}
