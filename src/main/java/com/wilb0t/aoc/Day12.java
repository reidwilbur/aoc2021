package com.wilb0t.aoc;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {
  
  static final Cave START = new Cave("start", false);
  static final Cave END = new Cave("end", false);
  
  public static record Cave(String name, boolean large) { 
    public static Cave forName(String name) {
      return (Character.isUpperCase(name.charAt(0))) 
          ? new Cave(name, true) 
          : new Cave(name, false);
    }
  }
  
  public static Map<Cave, Set<Cave>> parse(String[] lines) {
    return Arrays.stream(lines)
        .flatMap(
            line -> { 
              var parts = line.split("-");
              return Stream.of(
                  new AbstractMap.SimpleEntry<>(Cave.forName(parts[0]), Cave.forName(parts[1])),
                  new AbstractMap.SimpleEntry<>(Cave.forName(parts[1]), Cave.forName(parts[0]))
              );
            })
        .collect(
            Collectors.groupingBy(
                Map.Entry::getKey, 
                Collectors.mapping(Map.Entry::getValue, Collectors.toSet())));
  }
  
  public static int getPathCount(Map<Cave, Set<Cave>> map) {
    var paths = getPaths(START, map, Set.of());
    return paths.size();
  }
  
  public static Set<List<Cave>> getPaths(Cave cave, Map<Cave, Set<Cave>> map, Set<Cave> visited) {
    if (END.equals(cave)) {
      return Set.of(List.of(cave));
    }
    if (visited.contains(cave)) {
      return Set.of();
    }
    var subPaths = new HashSet<List<Cave>>();
    var subVisited = new HashSet<>(visited);
    if (!cave.large) {
      subVisited.add(cave);
    }
    for (var subCave : map.get(cave)) {
      subPaths.addAll(getPaths(subCave, map, subVisited));
    }
    var paths = new HashSet<List<Cave>>();
    for (var subPath : subPaths) {
      var path = new ArrayList<Cave>();
      path.add(cave);
      path.addAll(subPath);
      paths.add(path);
    }
    return paths;
  }
  
  public static int getPathCountWithNonsene(Map<Cave, Set<Cave>> map) {
    var canDouble = 
        map.keySet().stream()
            .filter(c -> !c.large)
            .filter(c -> !(c.equals(START) || c.equals(END)))
            .collect(Collectors.toSet());
    
    var paths = new HashSet<List<Cave>>();
    for (var dbl : canDouble) {
      paths.addAll(getPathsWithNonsense(START, map, dbl, Map.of()));
    }
    return paths.size();
  }
  
  static Set<List<Cave>> getPathsWithNonsense(Cave cave, Map<Cave, Set<Cave>> map, Cave canDouble, Map<Cave, Integer> visited) {
    if (END.equals(cave)) {
      return Set.of(List.of(cave));
    }
    if ((canDouble.equals(cave) && visited.getOrDefault(cave, 0) == 2) 
        || (!cave.large && !canDouble.equals(cave) && visited.containsKey(cave))) {
      return Set.of();
    }
    var subPaths = new HashSet<List<Cave>>();
    var subVisited = new HashMap<>(visited);
    if (!cave.large) {
      subVisited.merge(cave, 1, Integer::sum);
    }
    for (var subCave : map.get(cave)) {
      subPaths.addAll(getPathsWithNonsense(subCave, map, canDouble, subVisited));
    }
    var paths = new HashSet<List<Cave>>();
    for (var subPath : subPaths) {
      var path = new ArrayList<Cave>();
      path.add(cave);
      path.addAll(subPath);
      paths.add(path);
    }
    return paths;
  }
}
