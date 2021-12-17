package com.wilb0t.aoc;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Day17 {
  
  public static record Target(int xlo, int xhi, int ylo, int yhi) {
    boolean inRange(int xpos, int ypos) {
      return xpos >= xlo && xpos <= xhi
          && ypos >= ylo && ypos <= yhi;
    }
  }
  
  static record SimResult(int xpos, int ypos, int ymax, boolean success) { }
  
  static int getMaxY(Target target) {
    var maxsimres = new SimResult(0, 0, -1, false);
 
    for (var yvel = 1; yvel <= Math.abs(target.ylo); yvel++) {
      for (var xvel = 1; xvel <= target.xhi; xvel++) {
        var simres = simulate(xvel, yvel, target);
        if (simres.success && simres.ymax > maxsimres.ymax) {
          maxsimres = simres;
        }
      }
    }
    return maxsimres.ymax;
  }
  
  static int getSuccessVelCount(Target target) {
    var vels = new HashMap<Map.Entry<Integer, Integer>, SimResult>();
    for (var yvel = target.ylo; yvel <= Math.abs(target.ylo); yvel++) {
      for (var xvel = 1; xvel <= target.xhi; xvel++) {
        var simres = simulate(xvel, yvel, target);
        if (simres.success) {
          vels.put(new AbstractMap.SimpleEntry<>(xvel, yvel), simres);
        }
      }
    }
    return vels.size();    
  }
  
  static SimResult simulate(int xvel, int yvel, Target target) {
    int xpos = 0;
    int ypos = 0;
    int ymax = ypos;
    
    while (xpos <= target.xhi && ypos >= target.ylo) {
      xpos += xvel;
      ypos += yvel;
      xvel = Math.max(0, xvel - 1);
      yvel -= 1;
      
      ymax = Math.max(ypos, ymax);
      
      if (target.inRange(xpos, ypos)) {
        return new SimResult(xpos, ypos, ymax, true);
      }
    }
    return new SimResult(xpos, ypos, ymax, false);
  }
}
 