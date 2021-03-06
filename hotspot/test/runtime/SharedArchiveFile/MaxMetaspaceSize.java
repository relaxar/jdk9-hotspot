/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 8067187
 * @summary Testing CDS dumping with the -XX:MaxMetaspaceSize=<size> option
 * @library /testlibrary
 * @modules java.base/sun.misc
 *          java.management
 */

import com.oracle.java.testlibrary.*;

public class MaxMetaspaceSize {
  public static void main(String[] args) throws Exception {
    ProcessBuilder pb = ProcessTools.createJavaProcessBuilder(
        "-XX:MaxMetaspaceSize=20m", "-Xshare:dump");
    OutputAnalyzer output = new OutputAnalyzer(pb.start());
      output.shouldContain("is not large enough.\nEither don't specify the -XX:MaxMetaspaceSize=<size>\nor increase the size to at least");
      output.shouldHaveExitValue(2);
  }
}
