package iaf.perf.course.day3.greyscale;

import java.awt.image.BufferedImage;

import iaf.perf.course.day3.greyscale.GreyscaleEx.GreyscaleConverter;

public class FirstGreyscaleSolution implements GreyscaleConverter {

	private static final int ALPHA = 255 << 24;
	
	@Override
	public BufferedImage convert(BufferedImage img) 
	{
		BufferedImage greyscale = new BufferedImage(img.getWidth(), img.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		
		/*
		 * This is actually very inefficient, memory-wise.
		 * BufferedImage.getRGB accesses the BufferedImage's:
		 *   1. ColorModel, which accesses:
		 *   	1.1 DataBuffer
		 *   2. Raster, which accesses:
		 *   	2.1. SampleModel, which accesses:
		 *   		2.1.1 DataBuffer
		 *   
		 * Resulting in a lot of cache noise.
		 * What if instead we just stored the pixels in a byte[] or int[]?  
		 */
		int[] rgbArray = new int[img.getWidth()* img.getHeight()];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, img.getWidth());
		
		for (int i = 0; i < rgbArray.length; i++) {
			
//			 int greyColor = (int) (
//                     (rgbArray[i] & 0xFF) * 0.21 +
//                     (rgbArray[i] & 0xFF00 >> 8) * 0.72 +
//                     (rgbArray[i] & 0xFF0000 >> 16) * 0.07
//             );
//			 rgbArray[i] = (greyColor << 16) + (greyColor << 8) + greyColor;
			rgbArray[i] = rgbToGrey(rgbArray[i]);
		}
		
		greyscale.setRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, img.getWidth());
		return greyscale;
	}

	public static int rgbToGrey(int baseRgb) {
		int value =  0xff000000 | baseRgb;
		int rgbGreyValue = rgbToGreyscalePixel(value);
		int greyValue = greyRgbToValue(rgbGreyValue);
		return greyValue;
	}

	private static int greyRgbToValue(int rgbGreyValue) 
	{
		return rgbToValue(rgbGreyValue, rgbGreyValue, rgbGreyValue);
	}

	private static int rgbToGreyscalePixel(int value) 
	{
		int red =  (value >> 16) & 0xFF;
		int green = (value >> 8) & 0xFF;
		int blue = (value >> 0) & 0xFF;
		int newRed = (int) (red * 0.21);
		int newGreen = (int) (green * 0.72);
		int newBlue = (int) (blue * 0.07);
		int sum = newRed + newGreen + newBlue;
		return sum;
	}
	
	
	private static int rgbToValue(int r, int g, int b) {
		return (ALPHA |
                ( (r & 0xFF) << 16) |
                ( (g & 0xFF) << 8)  |
                  (b & 0xFF) );
	}

}
