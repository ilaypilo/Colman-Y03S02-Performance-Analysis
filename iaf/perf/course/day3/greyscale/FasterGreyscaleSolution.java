package iaf.perf.course.day3.greyscale;

import java.awt.image.BufferedImage;

import iaf.perf.course.day3.greyscale.GreyscaleEx.GreyscaleConverter;

public class FasterGreyscaleSolution implements GreyscaleConverter {
	
	@Override
	public BufferedImage convert(BufferedImage img) 
	{
		BufferedImage greyscale = new BufferedImage(img.getWidth(), img.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		
		for (int width = 0; width < img.getWidth(); width++) {
			for (int height = 0; height < img.getHeight(); height++) {
				int baseRgb = img.getRGB(width, height);
				int greyValue = rgbToGrey(baseRgb);
				greyscale.setRGB(width, height, greyValue);
			}
		}
		
		return greyscale;
	}

	public static int rgbToGrey(int baseRgb) {
		 int greyColor = (int) (
              (baseRgb & 0xFF) * 0.21 +
              (baseRgb & 0xFF00 >> 8) * 0.72 +
              (baseRgb & 0xFF0000 >> 16) * 0.07
      );
		 return (greyColor << 16) + (greyColor << 8) + greyColor;
	}

}
