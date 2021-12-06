package com.wilb0t.aoc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day5 {
  public static record Point(int x, int y) { }
  
  public static record Line(Point p1, Point p2) {
    
    public static Line parse(String input) {
      var parts = input.split(" -> ");
      var p1parts = parts[0].split(",");
      var p2parts = parts[1].split(",");
      var p1 = new Point(Integer.parseInt(p1parts[0]), Integer.parseInt(p1parts[1]));
      var p2 = new Point(Integer.parseInt(p2parts[0]), Integer.parseInt(p2parts[1]));
      return new Line(p1, p2);
    }
    
    public Set<Point> toPoints() {
      var points = new HashSet<Point>();
      if (p1.x == p2.x) {
        var ystart = Math.min(p1.y, p2.y);
        var yend = Math.max(p1.y, p2.y);
        for (var y = ystart; y <= yend; y++) {
          points.add(new Point(p1.x, y));
        }
      } else {
        var start = (p1.x <= p2.x) ? p1 : p2;
        var end = (start.equals(p1)) ? p2 : p1;
        var yinc = Integer.compare(end.y - start.y, 0);
        points.add(start);
        var last = start;
        for (var x = start.x + 1; x <= end.x; x++) {
          last = new Point(x, last.y + yinc);
          points.add(last);
        }
      }
      return points;
    }
    
    public boolean isHoriz() {
      return p1.x == p2.x;
    }
    
    public boolean isVert() {
      return p1.y == p2.y;
    }
  }
  
  public static int getDangerCount(List<Line> input) {
    var noDiags = input.stream().filter(l -> l.isHoriz() || l.isVert()).collect(Collectors.toList());
    
    var pointCounts = new HashMap<Point, Integer>();
    for (var line : noDiags) {
      line.toPoints().forEach(p -> pointCounts.merge(p, 1, Integer::sum));
    }
    
    return (int) pointCounts.values().stream().filter(i -> i>= 2).count();
  }

  public static int getDangerCountAll(List<Line> input) {
    var pointCounts = new HashMap<Point, Integer>();
    for (var line : input) {
      line.toPoints().forEach(p -> pointCounts.merge(p, 1, Integer::sum));
    }

    return (int) pointCounts.values().stream().filter(i -> i>= 2).count();
  }
}
