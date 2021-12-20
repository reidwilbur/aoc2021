package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class Day20Test {

  private static final Day20.Puzzle PUZZLE_INPUT = Day20.Puzzle.parse(Input.PUZZLE.loadStrings());
  
  private static final Day20.Puzzle TEST_INPUT = Day20.Puzzle.parse(Input.TEST.loadStrings());
  
  @Test
  void testGetLitPixels_testInput() {
    assertThat(Day20.getLitPixels(TEST_INPUT, 2), is(35));
  }

  @Test
  void testGetLitPixels_testInput_enhance() {
    assertThat(Day20.getLitPixels(TEST_INPUT, 50), is(3351));
  }

  @Test
  void testGetLitPixels_puzzlenput() {
    assertThat(Day20.getLitPixels(PUZZLE_INPUT, 2), is(5179));
  }

  @Test
  void testGetLitPixels_puzzlenput_enhance() {
    assertThat(Day20.getLitPixels(PUZZLE_INPUT, 50), is(16112));
  }
}