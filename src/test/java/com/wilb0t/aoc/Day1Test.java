package com.wilb0t.aoc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class Day1Test {

  private static final int[] PUZZLE_INPUT = Input.loadInts();

  private static final int[] TEST_INPUT =
      new int[] {
        199, 200, 208, 210, 200, 207, 240, 269, 260, 263,
      };

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
