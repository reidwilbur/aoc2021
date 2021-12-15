package com.wilb0t.aoc;

import java.util.HashMap;
import java.util.Map;

public class Day14 {
  
  public static record Puzzle(String template, Map<String, Character> rules) {
    public static Puzzle parse(String[] lines) {
      var rules = new HashMap<String, Character>();
      for (var idx = 2; idx < lines.length; idx++) {
        var parts = lines[idx].split(" -> ");
        rules.put(parts[0], parts[1].charAt(0));
      }
      return new Puzzle(lines[0], rules);
    }
  }
  
  public static long getElementCountDiff(Puzzle puzzle, int steps) {
    var counts = new HashMap<Character, Long>();
    var cache = new HashMap<String, Map<Character, Long>>();
    var tmplLen = puzzle.template.length();
    for (var idx = 0; idx < tmplLen - 1; idx++) {
      var pair = puzzle.template.substring(idx, idx + 2);
      var pairCount = getElementCounts(pair, puzzle.rules, steps, cache);
      for (var entry : pairCount.entrySet()) {
        counts.merge(entry.getKey(), entry.getValue(), Long::sum);
      }
      // fix up counts from overlapping pair chars
      if (idx >= 1 && idx < tmplLen - 1) {
        counts.put(pair.charAt(0), counts.get(pair.charAt(0)) - 1);
      }
    }
   
    var stats = counts.values().stream().mapToLong(l -> l).summaryStatistics();
    return stats.getMax() - stats.getMin();
  }

  static Map<Character, Long> getElementCounts(
      String pair, 
      Map<String, Character> rules, 
      int step, 
      Map<String, Map<Character, Long>> cache) {
    if (step == 0 || !rules.containsKey(pair)) {
      if (pair.charAt(0) == pair.charAt(1)) {
        return Map.of(pair.charAt(0), 2L);
      }
      return Map.of(pair.charAt(0), 1L, pair.charAt(1), 1L);
    }
    var cacheKey = pair + step;
    if (cache.containsKey(cacheKey)) {
      return cache.get(cacheKey);
    }
    var middle = rules.get(pair);
    var ltPair = String.valueOf(pair.charAt(0)) + middle;
    var rtPair = String.valueOf(middle) + pair.charAt(1);
    var ltCounts = new HashMap<>(getElementCounts(ltPair, rules, step - 1, cache));
    ltCounts.put(middle, ltCounts.get(middle) - 1);
    var rtCounts = getElementCounts(rtPair, rules, step - 1, cache);
    for (var entry : rtCounts.entrySet()) {
      ltCounts.merge(entry.getKey(), entry.getValue(), Long::sum);
    }
    cache.put(cacheKey, ltCounts);
    return ltCounts;
  }
}
