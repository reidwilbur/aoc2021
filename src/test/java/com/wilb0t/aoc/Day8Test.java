package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day8Test {

  private static final List<Day8.Patterns> PUZZLE_INPUT = Input.PUZZLE.load(Day8.Patterns::parse);

  private static final List<Day8.Patterns> TEST_INPUT = Input.TEST.load(Day8.Patterns::parse);

  @Test
  void testGetSimpleDigitCount_testInput() {
    assertThat(Day8.getSimpleDigitCount(TEST_INPUT), is(26));
  }
  
  @Test
  void testGetSimpleDigitCount_puzzleInput() {
    assertThat(Day8.getSimpleDigitCount(PUZZLE_INPUT), is(521));
  }
  
  @Test
  void testGetOutputSum_testInput() {
    assertThat(Day8.getOutputSum(TEST_INPUT), is(61229));
  }

  @Test
  void testGetOutputSum_puzzleInput() {
    assertThat(Day8.getOutputSum(PUZZLE_INPUT), is(1016804));
  }
}