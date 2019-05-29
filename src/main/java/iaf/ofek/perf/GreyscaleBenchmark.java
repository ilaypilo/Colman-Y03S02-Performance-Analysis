package iaf.ofek.perf;

import iaf.perf.course.day3.greyscale.GreyscaleEx;
import iaf.perf.course.day3.greyscale.GreyscaleSolution;
import iaf.perf.course.day3.greyscale.ArrayGreyscaleSolution;
import iaf.perf.course.day3.greyscale.GraphicsGreyscaleSolution;
import iaf.perf.course.day3.greyscale.JavaParallelArrayGreyscaleSolution;
import iaf.perf.course.day3.greyscale.ThreadPoolParallelArrayGreyscaleSolution;
import iaf.perf.course.day3.greyscale.ParallelLinesArrayGreyscaleSolution;


import org.openjdk.jmh.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by giladrber on 3/29/2017.
 */
@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput, Mode.SampleTime})
@OutputTimeUnit(TimeUnit.SECONDS)
public class GreyscaleBenchmark
{

    private static BufferedImage rgb;

    @Setup(Level.Iteration)
    public void setup() {
        try {
            rgb = ImageIO.read(new File(GreyscaleEx.RGB_LOCATION));
        }  catch (IOException ioe) {
            System.out.println("Failed to load image from " + GreyscaleEx.RGB_LOCATION);
            throw new RuntimeException(ioe);
        }
    }

    @Benchmark
    public BufferedImage NavieGreyscaleBenchmark() {
        GreyscaleEx.GreyscaleConverter greyscaleConverter = new GreyscaleSolution();
        return greyscaleConverter.convert(rgb);
    }

    @Benchmark
    public BufferedImage ArrayGreyscaleBenchmark() {
        GreyscaleEx.GreyscaleConverter greyscaleConverter = new ArrayGreyscaleSolution();
        return greyscaleConverter.convert(rgb);
    }

    @Benchmark
    public BufferedImage ParallelArrayGreyscaleBenchmark() {
        GreyscaleEx.GreyscaleConverter greyscaleConverter = new JavaParallelArrayGreyscaleSolution();
        return greyscaleConverter.convert(rgb);
    }

    @Benchmark
    public BufferedImage ThreadPoolParallelArrayGreyscaleBenchmark() {
        GreyscaleEx.GreyscaleConverter greyscaleConverter = new ThreadPoolParallelArrayGreyscaleSolution();
        return greyscaleConverter.convert(rgb);
    }

    @Benchmark
    public BufferedImage ParallelLinesArrayGreyscaleBenchmark() {
        GreyscaleEx.GreyscaleConverter greyscaleConverter = new ParallelLinesArrayGreyscaleSolution();
        return greyscaleConverter.convert(rgb);
    }

    @Benchmark
    public BufferedImage GraphicsGreyscaleBenchmark() {
        GreyscaleEx.GreyscaleConverter greyscaleConverter = new GraphicsGreyscaleSolution();
        return greyscaleConverter.convert(rgb);
    }



}
