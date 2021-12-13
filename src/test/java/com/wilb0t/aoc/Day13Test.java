package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class Day13Test {

  private static final Day13.Puzzle PUZZLE_INPUT = Day13.Puzzle.parse(Input.PUZZLE.loadStrings());

  private static final Day13.Puzzle TEST_INPUT = Day13.Puzzle.parse(Input.TEST.loadStrings());

  @Test
  void testGetDotsAfterFirstFold_testInput() {
    assertThat(Day13.getDotsAfterFirstFold(TEST_INPUT), is(17));
  }

  @Test
  void testGetDotsAfterFirstFold_puzzleInput() {
    assertThat(Day13.getDotsAfterFirstFold(PUZZLE_INPUT), is(684));
  }
  
  @Test
  void testGetCode_puzzleInput() {
    var code = Day13.getCode(PUZZLE_INPUT);
    System.out.println(code);
    assertThat(code.length(), is(239));
  }
}