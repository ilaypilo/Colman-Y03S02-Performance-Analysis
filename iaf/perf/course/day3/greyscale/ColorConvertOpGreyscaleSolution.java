package iaf.perf.course.day3.greyscale;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import iaf.perf.course.day3.greyscale.GreyscaleEx.GreyscaleConverter;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ColorConvertOpGreyscaleSolution implements GreyscaleConverter {

	@Override
	public BufferedImage convert(BufferedImage img) 
	{

		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
		ColorConvertOp op = new ColorConvertOp(cs, null);  
		BufferedImage greyscale = op.filter(img, null);
        
		return greyscale;
	}

}
