/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package iaf.ofek.perf;

import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class GreyscaleBenchmarkRunner {

    public static void main(String... args) throws RunnerException {
        Options opts = new OptionsBuilder()
//                .addProfiler(GCProfiler.class)
                .measurementIterations(15)
                .warmupIterations(10)
                .measurementTime(TimeValue.seconds(3))
                .forks(3)
                .jvmArgs("-Xms1g", "-Xmx1g", "-Xmn800m", "-server")
                .include(GreyscaleBenchmark.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }

}
/*
# Run complete. Total time: 00:08:09

Benchmark                                                      Mode  Cnt    Score    Error  Units
GreyscaleBenchmark.ArrayGreyscaleBenchmark                    thrpt   45   15.161 ±  0.748  ops/s
GreyscaleBenchmark.GraphicsGreyscaleBenchmark                 thrpt   45  406.998 ± 21.182  ops/s
GreyscaleBenchmark.NavieGreyscaleBenchmark                    thrpt   45   11.333 ±  0.789  ops/s
GreyscaleBenchmark.JavaParallelArrayGreyscaleBenchmark            thrpt   45   14.657 ±  0.462  ops/s
GreyscaleBenchmark.ParallelLinesArrayGreyscaleBenchmark       thrpt   45   71.034 ±  4.196  ops/s
GreyscaleBenchmark.ThreadPoolParallelArrayGreyscaleBenchmark  thrpt   45   14.963 ±  0.404  ops/s
*/