package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day6Test {

  private static final List<Integer> PUZZLE_INPUT = Input.PUZZLE.loadFlat(Day6::load);

  private static final List<Integer> TEST_INPUT = Input.TEST.loadFlat(Day6::load);

  @Test
  void testGetFishCount_testInput() {
    assertThat(Day6.getFishCount(TEST_INPUT, 80), is(5934L));
    assertThat(Day6.getFishCount(TEST_INPUT, 256), is(26984457539L));
  }

  @Test
  void testGetFishCount_puzzleInput() {
    assertThat(Day6.getFishCount(PUZZLE_INPUT, 80), is(365131L));
    assertThat(Day6.getFishCount(PUZZLE_INPUT, 256), is(1650309278600L));
  }
}