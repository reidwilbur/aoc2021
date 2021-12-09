package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day9Test {

  private static final int[][] PUZZLE_INPUT = Day9.load(Input.PUZZLE.loadStrings());

  private static final int[][] TEST_INPUT = Day9.load(Input.TEST.loadStrings());

  @Test
  void testCalcRisk_testInput() {
    assertThat(Day9.calcRisk(TEST_INPUT), is(15));
  }

  @Test
  void testCalcRisk_puzzleInput() {
    assertThat(Day9.calcRisk(PUZZLE_INPUT), is(580));
  }
  
  @Test
  void testCalcBasins_testInput() {
    assertThat(Day9.calcBasins(TEST_INPUT), is(1134));
  }

  @Test
  void testCalcBasins_puzzleInput() {
    assertThat(Day9.calcBasins(PUZZLE_INPUT), is(856716));
  }
}