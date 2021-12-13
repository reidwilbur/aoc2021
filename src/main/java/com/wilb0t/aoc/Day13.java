package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13 {
  public static record Point(int x, int y) { 
    public static Point parse(String line) {
      var parts = line.split(",");
      return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
  }
  
  public static record Fold(int line, Dir dir) {
    
    public enum Dir { HORIZ, VERT }
    
    public static Fold parse(String line) {
      var parts = line.split("=");
      var lineNum = Integer.parseInt(parts[1]);
      var dir = 
          (parts[0].charAt(parts[0].length() - 1) == 'y') 
              ? Dir.VERT 
              : Dir.HORIZ;
      return new Fold(lineNum, dir);
    }
    
    public Point fold(Point dot) {
      if (dir == Dir.VERT && dot.y > line) {
        var fy = line - (dot.y - line);
        return new Point(dot.x, fy);
      } else if (dir == Dir.HORIZ && dot.x > line) {
        var fx = line - (dot.x - line);
        return new Point(fx, dot.y);
      } else {
        return dot;
      }
    }
  }

  public static record Puzzle(List<Point> dots, List<Fold> folds) { 
    public static Puzzle parse(String[] lines) {
      var dots = 
          Arrays.stream(lines)
              .filter(l -> !l.isEmpty() && !l.startsWith("fold"))
              .map(Point::parse)
              .collect(Collectors.toList());
      
      var folds = 
          Arrays.stream(lines)
              .filter(l -> l.startsWith("fold"))
              .map(Fold::parse)
              .collect(Collectors.toList());
      
      return new Puzzle(dots, folds);
    }
  }
  
  public static int getDotsAfterFirstFold(Puzzle puzzle) {
    return puzzle.dots.stream()
        .map(puzzle.folds.get(0)::fold)
        .collect(Collectors.toSet())
        .size();
  }
  
  public static String getCode(Puzzle puzzle) {
    Set<Point> dots = new HashSet<>(puzzle.dots);
    for (var fold : puzzle.folds) {
      dots = dots.stream()
              .map(fold::fold)
              .collect(Collectors.toSet());
    }
    return toString(dots);
  }
  
  public static String toString(Collection<Point> dots) {
    var mx = Integer.MIN_VALUE;
    var my = Integer.MIN_VALUE;
    for (var dot : dots) {
      mx = Math.max(mx, dot.x);
      my = Math.max(my, dot.y);
    }
    mx++;
    my++;
    
    var fmx = mx;
    var buffer = 
        IntStream.range(0, my)
            .mapToObj(i -> new StringBuilder(" ".repeat(fmx)))
            .collect(Collectors.toList());
    
    for (var dot : dots) {
      buffer.get(dot.y).setCharAt(dot.x, '#');
    }
    
    return String.join("\n", buffer);
  }
}
