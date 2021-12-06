package com.wilb0t.aoc;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
  public static record Board(List<Set<Integer>> rows, List<Set<Integer>> cols) { 
    public static Board parse(int idx, String[] lines) {
      var rows = new ArrayList<List<Integer>>();
      var cols = new ArrayList<Set<Integer>>();
      for (var j = 0; j < 5; j++) {
        var row = Arrays.stream(lines[idx+j].trim().split("\s+"))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        rows.add(row);
        cols.add(new HashSet<>());
      }
      for (var j = 0; j < 5; j++) {
        var row = rows.get(j);
        for (var k = 0; k < 5; k++) {
          cols.get(k).add(row.get(k));
        }
      }
      return new Board(rows.stream().map(HashSet::new).collect(Collectors.toList()), cols);      
    }
  }
  
  public static record Input(List<Integer> numbers, List<Board> boards) { 
    public static Input parseInput(String[] lines) {
      var numStrs = Arrays.stream(lines[0].split(","))
          .map(Integer::parseInt)
          .collect(Collectors.toList());

      var boards = new ArrayList<Board>();

      for (var i = 2; i < lines.length; i += 6) {
        boards.add(Board.parse(i, lines));
      }

      return new Input(numStrs, boards);
    }
  }
  
  public static int getWinningScore(Input input) {
    for (var number : input.numbers()) {
      for (var board : input.boards()) {
        var bingo = false;
        for (var row : board.rows()) {
          row.remove(number);
          bingo |= row.isEmpty();
        }
        for (var col : board.cols()) {
          col.remove(number);
          bingo |= col.isEmpty();
        }
        if (bingo) {
          var remaining = board.rows().stream().mapToInt(r -> r.stream().mapToInt(i -> i).sum()).sum();
          return remaining * number;
        }
      }
    }
    return -1;
  }
  
  public static int getLosingScore(Input input) {
    var boards = IntStream.range(0, input.boards.size())
        .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, input.boards().get(i)))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    var score = -1;
    for (var number : input.numbers()) {
      for (var entry : new HashSet<>(boards.entrySet())) {
        var board = entry.getValue();
        var bingo = false;
        for (var row : board.rows()) {
          row.remove(number);
          bingo |= row.isEmpty();
        }
        for (var col : board.cols()) {
          col.remove(number);
          bingo |= col.isEmpty();
        }
        if (bingo) {
          var remaining = board.rows().stream().mapToInt(r -> r.stream().mapToInt(i -> i).sum()).sum();
          score = remaining * number;
          boards.remove(entry.getKey());
        }
      }
    }
    return score;
  }
}