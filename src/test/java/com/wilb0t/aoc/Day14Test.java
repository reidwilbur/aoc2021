package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class Day14Test {

  private static final Day14.Puzzle PUZZLE_INPUT = Day14.Puzzle.parse(Input.PUZZLE.loadStrings());

  private static final Day14.Puzzle TEST_INPUT = Day14.Puzzle.parse(Input.TEST.loadStrings());
  
  @Test
  void testGetElementCountDiff_testInput_10() {
    assertThat(Day14.getElementCountDiff(TEST_INPUT, 10), is(1588L));
  }

  @Test
  void testGetElementCountDiff_puzzleInput_10() {
    assertThat(Day14.getElementCountDiff(PUZZLE_INPUT, 10), is(2112L));
  }
  
  @Test
  void testGetElementCountDiff_testInput() {
    assertThat(Day14.getElementCountDiff(TEST_INPUT, 40), is(2188189693529L));
  }

  @Test
  void testGetElementCountDiff_puzzleInput() {
    assertThat(Day14.getElementCountDiff(PUZZLE_INPUT, 40), is(3243771149914L));
  }
}