package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class Day17Test {

  private static final Day17.Target PUZZLE_INPUT = new Day17.Target(143, 177, -106, -71);
  
  private static final Day17.Target TEST_INPUT = new Day17.Target(20, 30, -10, -5);
  
  @Test
  void testSimulate() {
    assertThat(
        Day17.simulate(7, 2, TEST_INPUT),
        is(new Day17.SimResult(28, -7, 3, true)));
    assertThat(
        Day17.simulate(6, 3, TEST_INPUT),
        is(new Day17.SimResult(21, -9, 6, true)));
    assertThat(
        Day17.simulate(9, 0, TEST_INPUT),
        is(new Day17.SimResult(30, -6, 0, true)));
    assertThat(
        Day17.simulate(17, -4, TEST_INPUT),
        is(new Day17.SimResult(33, -9, 0, false)));
    assertThat(
        Day17.simulate(6, 9, TEST_INPUT),
        is(new Day17.SimResult(21, -10, 45, true)));
  }
  
  @Test
  void testGetMaxY_testInput() {
    assertThat(Day17.getMaxY(TEST_INPUT), is(45));
  }

  @Test
  void testGetMaxY_puzzleInput() {
    assertThat(Day17.getMaxY(PUZZLE_INPUT), is(5565));
  }
  
  @Test
  void testGetSuccessVelCount_testInput() {
    assertThat(Day17.getSuccessVelCount(TEST_INPUT), is(112));
  }

  @Test
  void testGetSuccessVelCount_puzzleInput() {
    assertThat(Day17.getSuccessVelCount(PUZZLE_INPUT), is(2118));
  }
}