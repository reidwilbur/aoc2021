package com.wilb0t.aoc;

public class Day7 {
  
  public static int getCheapestAlignment(int[] initState) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (var pos : initState) {
      min = Math.min(min, pos);
      max = Math.max(max, pos);
    }
    int minCost = Integer.MAX_VALUE;
    for (var pos = min; pos <= max; pos++) {
      var cost = 0;
      for (var shipPos : initState) {
        cost += Math.abs(shipPos - pos);
      }
      if (cost < minCost ) {
        minCost  = cost;
      }
    }
    return minCost ;
  }
  
  public static int getCheapestAlignmentWithCost(int[] initState) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (var pos : initState) {
      min = Math.min(min, pos);
      max = Math.max(max, pos);
    }
    int minCost = Integer.MAX_VALUE;
    for (var pos = min; pos <= max; pos++) {
      var cost = 0;
      for (var shipPos : initState) {
        var dist = Math.abs(shipPos - pos);
        cost += (dist * (dist + 1)) / 2;
      }
      if (cost < minCost ) {
        minCost  = cost;
      }
    }
    return minCost ;
  }
}
