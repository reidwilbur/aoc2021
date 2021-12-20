package com.wilb0t.aoc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20 {
  public static record Point(int r, int c) { 
    public List<Point> algoPoints() {
      return Stream.of(
          new Point(r - 1, c - 1),
          new Point(r - 1, c),
          new Point(r - 1, c + 1),
          new Point(r, c - 1),
          new Point(r, c),
          new Point(r, c + 1),
          new Point(r + 1, c - 1),
          new Point(r + 1, c),
          new Point(r + 1, c + 1)
      ).collect(Collectors.toList());
    }
  }
  
  public static record Puzzle(boolean[] algo, boolean[][] image) { 
    public static Puzzle parse(String[] lines) {
      var algo = new boolean[lines[0].length()];
      for (var i = 0; i < algo.length;  i++) {
        algo[i] = lines[0].charAt(i) == '#';
      }
     
      boolean[][] image = new boolean[lines.length + 2][lines[2].length() + 4];
      for (var i = 2; i < lines.length; i++) {
        for (var c = 0; c < lines[i].length(); c++) {
          if (lines[i].charAt(c) == '#') {
            var r = i - 2;
            image[r + 2][c + 2] = true;
          }
        }
      }
      return new Puzzle(algo, image);
    }
  }
  
  public static int getLitPixels(Puzzle input, int steps) {
    var image = input.image;
    for (var step = 0; step < steps; step++) {
      image = runAlgo(input.algo, image);
    }
    var count = 0;
    for (var row : image) {
      for (var cell : row) {
        count += (cell) ? 1 : 0;
      }
    }
    return count;
  }
  
  static boolean[][] runAlgo(boolean[] algo, boolean[][] image) {
    var outsideval = image[0][0];
    var nextimg = new boolean[image.length + 2][image[0].length + 2];
 
    var maxr = image.length;
    var maxc = image[0].length;
    for (var row = -1; row <= maxr; row++) {
      for (var col = -1; col <= maxc; col++) {
        var point = new Point(row, col);
        var algoidx = 0;
        var algopts = point.algoPoints();
        for (int idx = 0; idx < 9; idx++) {
          var p = algopts.get(idx);
          var isoutside = (p.r < 0) || (p.c < 0) || (p.r >= maxr) || (p.c >= maxc);
          var lit = ((isoutside) ? outsideval : image[p.r][p.c]) ? 1 : 0;
          algoidx |= lit << (8 - idx);
        }
        if (algo[algoidx]) {
          nextimg[row + 1][col + 1] = true;
        }
      }
    }
    return nextimg;
  }
}
