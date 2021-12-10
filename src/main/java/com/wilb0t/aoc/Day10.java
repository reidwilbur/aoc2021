package com.wilb0t.aoc;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class Day10 {

  private static final Map<Character, Character> CLOSERS =
      Map.of(
          '(', ')',
          '[', ']',
          '{', '}',
          '<', '>');

  private static final Map<Character, Integer> ILLEGAL_VAL =
      Map.of(
          ')', 3,
          ']', 57,
          '}', 1197,
          '>', 25137);

  private static final Map<Character, Integer> COMP_VAL =
      Map.of(
          ')', 1,
          ']', 2,
          '}', 3,
          '>', 4);

  public static int getSyntaxScore(String[] lines) {
    return Arrays.stream(lines)
        .flatMap(l -> getIllegalChar(l).stream())
        .mapToInt(ILLEGAL_VAL::get)
        .sum();
  }

  static Optional<Character> getIllegalChar(String line) {
    var stack = new ArrayDeque<Character>(line.length());
    for (var chr : line.toCharArray()) {
      if (CLOSERS.containsKey(chr)) {
        stack.push(chr);
      } else {
        var opener = stack.pop();
        var closer = CLOSERS.get(opener);
        if (!closer.equals(chr)) {
          return Optional.of(chr);
        }
      }
    }
    return Optional.empty();
  }

  public static long getCompletionScore(String[] lines) {
    var compScores =
        Arrays.stream(lines)
            .flatMap(line -> getCompletionScore(line).stream())
            .mapToLong(l -> l)
            .sorted()
            .toArray();
    var midIdx = compScores.length / 2;
    return compScores[midIdx];
  }

  static Optional<Long> getCompletionScore(String line) {
    var stack = new ArrayDeque<Character>(line.length());
    for (var chr : line.toCharArray()) {
      if (CLOSERS.containsKey(chr)) {
        stack.push(chr);
      } else {
        var opener = stack.pop();
        var closer = CLOSERS.get(opener);
        if (!closer.equals(chr)) {
          return Optional.empty();
        }
      }
    }
    long score = 0;
    while (!stack.isEmpty()) {
      var opener = stack.pop();
      var closer = CLOSERS.get(opener);
      score = (score * 5) + COMP_VAL.get(closer);
    }
    return Optional.of(score);
  }
}
