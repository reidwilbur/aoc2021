package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 {
  
  public record Point(int r, int c) {
    public List<Point> nbors(int maxr, int maxc) {
      return
          Stream.of(
                  new Point(r - 1, c - 1),
                  new Point(r - 1, c),
                  new Point(r - 1, c + 1),
                  new Point(r, c - 1),
                  new Point(r, c + 1),
                  new Point(r + 1, c - 1), 
                  new Point(r + 1, c), 
                  new Point(r + 1, c + 1)
              )
              .filter(p ->
                  p.r >= 0 && p.r < maxr
                      && p.c >= 0 && p.c < maxc)
              .collect(Collectors.toList());
    }
  }

  public static int getFlashCount(int[][] input, int steps) {
    var flashCount = 0;
    for (var step = 0; step < steps; step++) {
      flashCount += runStep(input);
    }
    return flashCount;
  }
  
  public static int getAllFlashStep(int[][] input) {
    var count = input.length * input[0].length;
    var flashed = -1;
    var step = 0;
    while (flashed != count) {
      flashed = runStep(input);
      step += 1;
    }
    return step;
  }
  
  static int runStep(int[][] input) {
    var maxr = input.length;
    var maxc = input[0].length;

    var queue = new ArrayDeque<Point>(maxr * maxc);
    for (var r = 0; r < maxr; r++) {
      for (var c = 0; c < maxc; c++) {
        input[r][c] += 1;
        queue.add(new Point(r,c));
      }
    }
   
    var flashed = new HashSet<Point>();
    
    while(!queue.isEmpty()) {
      var point = queue.pop();
      if (input[point.r][point.c] > 9 && !flashed.contains(point)) {
        flashed.add(point);
        for (var nbor : point.nbors(maxr, maxc)) {
          input[nbor.r][nbor.c] += 1;
          queue.add(nbor);
        }
      }
    }
    
    for (var f : flashed) {
      input[f.r][f.c] = 0;
    }
    
    return flashed.size();
  }
}
