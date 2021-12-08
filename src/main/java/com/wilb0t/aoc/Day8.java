package com.wilb0t.aoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {
  public static record Patterns(List<Set<Character>> inputs, List<Set<Character>> outputs) { 
    public static Patterns parse(String line) {
      var parts = line.split(" \\| ");
      var inputs = 
          Arrays.stream(parts[0].trim().split("\s+"))
              .map(s -> s.chars().mapToObj(i -> (char) i).collect(Collectors.toSet()))
              .collect(Collectors.toList());
      var outputs = 
          Arrays.stream(parts[1].trim().split("\s+"))
              .map(s -> s.chars().mapToObj(i -> (char) i).collect(Collectors.toSet()))
              .collect(Collectors.toList());
      return new Patterns(inputs, outputs);
    }
  }
  
  public static int getSimpleDigitCount(List<Patterns> inputs) {
    return (int) inputs.stream()
        .flatMap(entry -> entry.outputs().stream())
        .mapToInt(Set::size)
        .filter(l -> l == 2 || l == 4 || l == 3 || l ==7)
        .count();
  }
  
  public static int getOutputSum(List<Patterns> inputs) {
    return inputs.stream().mapToInt(Day8::decodeOutput).sum();
  }
  
  static int decodeOutput(Patterns patterns) {
    var grouped = 
        patterns.inputs().stream().collect(Collectors.groupingBy(Set::size));
    
    var digToPat = new HashMap<String, Set<Character>>();
    digToPat.put("1", grouped.get(2).get(0));
    digToPat.put("4", grouped.get(4).get(0));
    digToPat.put("7", grouped.get(3).get(0));
    digToPat.put("8",grouped.get(7).get(0));
    
    for (var pattern : grouped.get(5)) {
      var cmpSeven = intersect(pattern, digToPat.get("7"));
      var cmpFour = intersect(pattern, digToPat.get("4"));
      var dig = "";
      if (cmpFour == 2 && cmpSeven == 2) {
        dig = "2";
      } else if (cmpFour == 3 && cmpSeven == 3) {
        dig = "3";
      } else if (cmpFour == 3 && cmpSeven == 2) {
        dig = "5";
      } else {
        throw new RuntimeException("Unable to decode pattern " + pattern);
      }
      digToPat.put(dig, pattern);
    }
    
    for (var pattern : grouped.get(6)) {
      var cmpSeven = intersect(pattern, digToPat.get("7"));
      var cmpFour = intersect(pattern, digToPat.get("4"));
      var dig = "";
      if (cmpFour == 3 && cmpSeven == 3) {
        dig = "0";
      } else if (cmpFour == 3 && cmpSeven == 2) {
        dig = "6";
      } else if (cmpFour == 4 && cmpSeven == 3) {
        dig = "9";
      } else {
        throw new RuntimeException("Unable to decode pattern " + pattern);
      }
      digToPat.put(dig, pattern);
    }

    return Integer.parseInt(
        patterns.outputs().stream()
            .map(pattern -> 
                digToPat.entrySet().stream()
                    .filter(e -> e.getValue().equals(pattern))
                    .findFirst()
                    .orElseThrow()
                    .getKey())
            .collect(Collectors.joining("")));
  }
  
  static <T> int intersect(Set<T> left, Set<T> right) {
    var cmpSet = new HashSet<>(left);
    cmpSet.retainAll(right);
    return cmpSet.size();
  }
}
