package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class Day11Test {

  private static final int[][] PUZZLE_INPUT = Input.PUZZLE.loadIntGrid();

  private static final int[][] TEST_INPUT = Input.TEST.loadIntGrid();

  @Test
  void testGetFlashCount_testInput() {
    assertThat(Day11.getFlashCount(Input.copy(TEST_INPUT), 10), is(204));
    assertThat(Day11.getFlashCount(Input.copy(TEST_INPUT), 100), is(1656));
  }

  @Test
  void testGetFlashCount_puzzleInput() {
    assertThat(Day11.getFlashCount(Input.copy(PUZZLE_INPUT), 100), is(1785));
  }
  
  @Test
  void testGetAllFlashStep_testInput() {
    assertThat(Day11.getAllFlashStep(Input.copy(TEST_INPUT)), is(195));
  }

  @Test
  void testGetAllFlashStep_puzzleInput() {
    assertThat(Day11.getAllFlashStep(Input.copy(PUZZLE_INPUT)), is(354));
  }
}
