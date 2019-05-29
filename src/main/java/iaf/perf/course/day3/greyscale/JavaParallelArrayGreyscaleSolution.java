package iaf.perf.course.day3.greyscale;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import iaf.perf.course.day3.greyscale.GreyscaleEx.GreyscaleConverter;

public class JavaParallelArrayGreyscaleSolution implements GreyscaleConverter {
	
	@Override
	public BufferedImage convert(BufferedImage img) 
	{
		BufferedImage greyscale = new BufferedImage(img.getWidth(), img.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		
		int[] rgbArray = new int[img.getWidth()* img.getHeight()];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, img.getWidth());
		
		Arrays.parallelSetAll(rgbArray, i -> GreyscaleSolution.rgbToGrey(rgbArray[i]));

		greyscale.setRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, img.getWidth());
		return greyscale;
	}

}
