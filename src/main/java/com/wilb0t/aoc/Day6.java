package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day6 {
  
  public static Stream<Integer> load(String line) {
    return Arrays.stream(line.split(",")).map(Integer::parseInt);
  }
  
  public static long getFishCount(List<Integer> initState, int days) {
    List<Integer> sim = new ArrayList<>(initState);

    var cache = new HashMap<Long, Long>();
    var total = 0L;
    for (var fish : sim) {
      total += getFishCount(fish, days, cache);
    }
    return total;
  }
  
  static long getFishCount(long offset, int days, Map<Long, Long> cache) {
    if (cache.containsKey(offset)) {
      return cache.get(offset);
    }
    if (offset >= days) {
      return 1;
    }
    var children = new ArrayList<Long>();
    for (var day = offset; day < days; day += 7) {
      children.add(day + 9L);
    }
    var total = 1L;
    for (var child : children) {
      total += getFishCount(child, days, cache);
    }
    cache.put(offset, total);
    return total;
  }
}
