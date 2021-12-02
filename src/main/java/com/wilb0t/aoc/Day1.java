package com.wilb0t.aoc;

class Day1 {

  public static int getIncCount(int[] depths) {
    var count = 0;
    for (var i = 1; i < depths.length; i++) {
      count += (depths[i] > depths[i - 1]) ? 1 : 0;
    }
    return count;
  }

  public static int getIncWindowCount(int[] depths) {
    if (depths.length < 4) {
      return 0;
    }

    var count = 0;
    var window = depths[0] + depths[1] + depths[2];
    for (var i = 1; i < depths.length - 2; i++) {
      var last = window;
      window += depths[i + 2] - depths[i - 1];
      count += (window > last) ? 1 : 0;
    }
    return count;
  }
}
