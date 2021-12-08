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
    var lenToPats = 
        patterns.inputs().stream().collect(Collectors.groupingBy(Set::size));
    
    var patToDigit = new HashMap<Set<Character>, String>();
    patToDigit.put(lenToPats.get(2).get(0), "1");
    patToDigit.put(lenToPats.get(4).get(0), "4");
    patToDigit.put(lenToPats.get(3).get(0), "7");
    patToDigit.put(lenToPats.get(7).get(0), "8");
    var four = lenToPats.get(4).get(0);
    var seven = lenToPats.get(3).get(0);
    
    for (var pattern : lenToPats.get(5)) {
      var cmpSeven = intersect(pattern, seven);
      var cmpFour = intersect(pattern, four);
      
      var digit =
          (cmpFour == 2 && cmpSeven == 2) 
              ? "2" 
              : (cmpFour == 3 && cmpSeven == 3) 
                  ? "3" 
                  : "5";
      patToDigit.put(pattern, digit);
    }
    
    for (var pattern : lenToPats.get(6)) {
      var cmpSeven = intersect(pattern, seven);
      var cmpFour = intersect(pattern, four);
      var digit =
          (cmpFour == 3 && cmpSeven == 3) 
              ? "0"
              : (cmpFour == 3 && cmpSeven == 2) 
                  ? "6" 
                  : "9";
      patToDigit.put(pattern, digit);
    }

    return Integer.parseInt(
        patterns.outputs().stream()
            .map(patToDigit::get)
            .collect(Collectors.joining("")));
  }
  
  static <T> int intersect(Set<T> left, Set<T> right) {
    var cmpSet = new HashSet<>(left);
    cmpSet.retainAll(right);
    return cmpSet.size();
  }
}
