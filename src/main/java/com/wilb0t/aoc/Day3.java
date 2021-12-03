package com.wilb0t.aoc;

public class Day3 {
  
  public static int getPowerCons(String[] report) {
    var bits = report[0].length();
    int[] oneCount = new int[bits];
    
    for (var line : report) {
      for (var idx = 0; idx < bits; idx++) {
        if (line.charAt(idx) == '1') {
          oneCount[idx] += 1;
        }
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
}
