package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day9 {
  
  public record Point(int r, int c) {
    public List<Point> nbors(int maxr, int maxc) {
      return 
          Stream.of(
              new Point(r - 1, c), 
              new Point(r + 1, c), 
              new Point(r, c - 1), 
              new Point(r, c + 1))
          .filter(p -> 
              p.r >= 0 && p.r < maxr
              && p.c >= 0 && p.c < maxc)
          .collect(Collectors.toList());
      }
  }
  
  public static int calcRisk(int[][] heights) {
    var maxr = heights.length;
    var maxc = heights[0].length;

    var risk = 0;
    
    for (var r = 0; r < maxr; r++) {
      for (var c = 0; c < maxc; c++) {
        var point = new Point(r, c);
        var height = heights[r][c];
        var islow = true;
        for (var nbor : point.nbors(maxr, maxc)) {
          islow &= height < heights[nbor.r][nbor.c];
        }
        if (islow) {
          risk += height + 1;
        }
      }
    }
    
    return risk;
  }
  
  public static int calcBasins(int[][] heights) {
    var maxr = heights.length;
    var maxc = heights[0].length;
    
    var queue = new ArrayDeque<Point>();
    var basinSizes = new ArrayList<Integer>();

    for (var r = 0; r < maxr; r++) {
      for (var c = 0; c < maxc; c++) {
        queue.add(new Point(r, c));
        var basinSize = 0;
        while (!queue.isEmpty()) {
          var p = queue.pop();
          if (heights[p.r][p.c] < 9) {
            heights[p.r][p.c] = 99;
            basinSize += 1;
            queue.addAll(p.nbors(maxr, maxc));
          }
        }
        if (basinSize > 0) {
          basinSizes.add(basinSize);
        }
      }
    }
    
    return basinSizes.stream()
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .mapToInt(i -> i)
        .reduce(1, (l, r) -> l * r);
  }
}
