package com.wilb0t.aoc;

import java.util.List;
import java.util.stream.Collectors;

public class Day3 {

  @FunctionalInterface
  public interface BitCrit {
    boolean test(int ones, int zeros, char bit);
  }

  public static int getPowerCons(String[] report) {
    var bits = report[0].length();
    int[] oneCount = new int[bits];

    for (var line : report) {
      for (var idx = 0; idx < bits; idx++) {
        oneCount[idx] += (line.charAt(idx) == '1') ? 1 : 0;
      }
    }

    int gamma = 0;
    int epsilon = 0;
    for (var idx = 0; idx < bits; idx++) {
      var zeroCount = report.length - oneCount[idx];
      if (oneCount[idx] > zeroCount) {
        gamma |= 1 << (bits - 1 - idx);
      } else {
        epsilon |= 1 << (bits - 1 - idx);
      }
    }

    return gamma * epsilon;
  }

  public static int getLifeSupportRating(String[] report) {
    var o2Rating =
        filterReport(
            report,
            (ones, zeros, bit) -> (ones >= zeros && bit == '1') || (ones < zeros && bit == '0'));

    var co2Rating =
        filterReport(
            report,
            (ones, zeros, bit) -> (ones >= zeros && bit == '0') || (ones < zeros && bit == '1'));

    return o2Rating * co2Rating;
  }

  static int filterReport(String[] report, BitCrit bitCrit) {
    var lines = List.of(report);

    var bit = 0;
    while (lines.size() > 1) {
      var oneCount = 0;
      var zeroCount = 0;
      for (var line : lines) {
        oneCount += (line.charAt(bit) == '1') ? 1 : 0;
        zeroCount += (line.charAt(bit) == '0') ? 1 : 0;
      }
      var oc = oneCount;
      var zc = zeroCount;
      var b = bit;
      lines =
          lines.stream()
              .filter(line -> bitCrit.test(oc, zc, line.charAt(b)))
              .collect(Collectors.toList());
      bit += 1;
    }

    return Integer.parseInt(lines.get(0), 2);
  }
}
