package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day5Test {

  private static final List<Day5.Line> PUZZLE_INPUT = Input.PUZZLE.load(Day5.Line::parse);

  private static final List<Day5.Line> TEST_INPUT = Input.TEST.load(Day5.Line::parse);

  @Test
  void testGetDangerCount_testInput() {
    assertThat(Day5.getDangerCount(TEST_INPUT), is(5));
  }

  @Test
  void testGetDangerCount_puzzleInput() {
    assertThat(Day5.getDangerCount(PUZZLE_INPUT), is(5294));
  }
  
  @Test
  void testGetDangerCountAll_testInput() {
    assertThat(Day5.getDangerCountAll(TEST_INPUT), is(12));
  }

  @Test
  void testGetDangerCountAll_puzzleInput() {
    assertThat(Day5.getDangerCountAll(PUZZLE_INPUT), is(21698));
  }
}
