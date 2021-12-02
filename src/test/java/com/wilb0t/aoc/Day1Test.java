package com.wilb0t.aoc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class Day1Test {

  private static final int[] PUZZLE_INPUT = Input.PUZZLE.loadInts();

  private static final int[] TEST_INPUT = Input.TEST.loadInts();

  @Test
  void testGetIncCount_testInput() {
    assertThat(Day1.getIncCount(TEST_INPUT), is(7));
  }

  @Test
  void testGetIncCount_puzzleInput() {
    assertThat(Day1.getIncCount(PUZZLE_INPUT), is(1581));
  }

  @Test
  void testGetIncWindowCount_testInput() {
    assertThat(Day1.getIncWindowCount(TEST_INPUT), is(5));
  }

  @Test
  void testGetIncWindowCount_puzzleInput() {
    assertThat(Day1.getIncWindowCount(PUZZLE_INPUT), is(1618));
  }
}
