package iaf.perf.course.day3.greyscale;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import iaf.perf.course.day3.greyscale.GreyscaleEx.GreyscaleConverter;

public class ThreadPoolParallelArrayGreyscaleSolution implements GreyscaleConverter {
	
	private static final int PARALLELISM = Runtime.getRuntime().availableProcessors();
	
	@Override
	public BufferedImage convert(BufferedImage img) 
	{
		final ExecutorService pool = Executors.newFixedThreadPool(PARALLELISM);
		BufferedImage greyscale = new BufferedImage(img.getWidth(), img.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		
		int[] rgbArray = new int[img.getWidth()* img.getHeight()];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, img.getWidth());
		
		final int sizeOfEachJob = rgbArray.length / PARALLELISM;
		
		for (int i = 0; i < PARALLELISM; i++) {
			final int offset = sizeOfEachJob * i;
			pool.submit(() -> {
				for(int j = 0; j < sizeOfEachJob; j++)
				rgbArray[offset+j] = NaiveGreyscaleSolution.rgbToGrey(rgbArray[offset+j]);
			});
		}
		
		// wait for all jobs to finish
		pool.shutdown();
		try {
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			
		}
		
		greyscale.setRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, img.getWidth());
		return greyscale;
	}

}
