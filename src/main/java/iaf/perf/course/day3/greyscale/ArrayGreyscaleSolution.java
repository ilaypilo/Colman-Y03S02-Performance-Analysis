package iaf.perf.course.day3.greyscale;

import java.awt.image.BufferedImage;

import iaf.perf.course.day3.greyscale.GreyscaleEx.GreyscaleConverter;

public class ArrayGreyscaleSolution implements GreyscaleConverter {
	
	
	@Override
	public BufferedImage convert(BufferedImage img) 
	{
		BufferedImage greyscale = new BufferedImage(img.getWidth(), img.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		
		int[] rgbArray = new int[img.getWidth()* img.getHeight()];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, img.getWidth());
		
		for (int i = 0; i < rgbArray.length; i++) {
			rgbArray[i] = GreyscaleSolution.rgbToGrey(rgbArray[i]);
		}
		
		greyscale.setRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, img.getWidth());
		return greyscale;
	}

}
