package com.wilb0t.aoc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class Day3Test {

  private static final String[] PUZZLE_INPUT = Input.PUZZLE.loadStrings();

  private static final String[] TEST_INPUT = Input.TEST.loadStrings();

  @Test
  void testGetPowerCons_testInput() {
    assertThat(Day3.getPowerCons(TEST_INPUT), is(198));
  }

  @Test
  void testGetPowerCons_puzzleInput() {
    assertThat(Day3.getPowerCons(PUZZLE_INPUT), is(4139586));
  }
  
  @Test
  void testGetLifeSupportRating_testInput() {
    assertThat(Day3.getLifeSupportRating(TEST_INPUT), is(230));
  }

  @Test
  void testGetLifeSupportRating_puzzleInput() {
    assertThat(Day3.getLifeSupportRating(PUZZLE_INPUT), is(1800151));
  }
}
