package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day9Test {

  private static final int[][] PUZZLE_INPUT = Input.PUZZLE.loadIntGrid();

  private static final int[][] TEST_INPUT = Input.TEST.loadIntGrid();

  @Test
  void testCalcRisk_testInput() {
    assertThat(Day9.calcRisk(Input.copy(TEST_INPUT)), is(15));
  }

  @Test
  void testCalcRisk_puzzleInput() {
    assertThat(Day9.calcRisk(Input.copy(PUZZLE_INPUT)), is(580));
  }
  
  @Test
  void testCalcBasins_testInput() {
    assertThat(Day9.calcBasins(Input.copy(TEST_INPUT)), is(1134));
  }

  @Test
  void testCalcBasins_puzzleInput() {
    assertThat(Day9.calcBasins(Input.copy(PUZZLE_INPUT)), is(856716));
  }
}