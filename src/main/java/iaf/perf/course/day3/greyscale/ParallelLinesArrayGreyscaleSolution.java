package iaf.perf.course.day3.greyscale;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import iaf.perf.course.day3.greyscale.GreyscaleEx.GreyscaleConverter;

public class ParallelLinesArrayGreyscaleSolution implements GreyscaleConverter {
	
	private static final int PARALLELISM = Runtime.getRuntime().availableProcessors();
	
	// according to 
	// https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/awt/image/BufferedImage.java
	// https://zgrepcode.com/java/oracle/jdk-8u181/java/awt/image/bufferedimage.java
	// setRGB is marked as synchronized
	// issue to remove this flag
	// https://bugs.openjdk.java.net/browse/JDK-8183980
	// but we don't need it to be sync
	// we doesn't have overlapped calls

	@Override
	public BufferedImage convert(BufferedImage img) 
	{
		final ExecutorService pool = Executors.newFixedThreadPool(PARALLELISM);
		BufferedImage greyscale = new BufferedImage(img.getWidth(), img.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		

		final int numOfLinesForEachJob = img.getHeight() / PARALLELISM;
		
		for (int i = 0; i < PARALLELISM; i++) {
			final int index = i;
			pool.submit(() -> {
				int[] rgbArray = new int[numOfLinesForEachJob * img.getWidth()];
				img.getRGB(0, index*numOfLinesForEachJob, img.getWidth(), numOfLinesForEachJob, rgbArray, 0, img.getWidth());
				Arrays.setAll(rgbArray, rgbIndex -> NaiveGreyscaleSolution.rgbToGrey(rgbArray[rgbIndex]));
				greyscale.setRGB(0, index*numOfLinesForEachJob, img.getWidth(), numOfLinesForEachJob, rgbArray, 0, img.getWidth());
			});
		}
		
		// wait for all jobs to finish
		pool.shutdown();
		try {
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			
		}
		
		
		return greyscale;
	}

}
