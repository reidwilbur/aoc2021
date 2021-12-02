package com.wilb0t.aoc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day2Test {

  private static final List<Day2.Command> PUZZLE_INPUT = Input.PUZZLE.load(Day2.INPUT_MAPPER);

  private static final List<Day2.Command> TEST_INPUT = Input.TEST.load(Day2.INPUT_MAPPER);

  @Test
  void testGetDistMult_testInput() {
    assertThat(Day2.getDistMult(TEST_INPUT), is(150));
  }

  @Test
  void testGetDistMult_puzzleInput() {
    assertThat(Day2.getDistMult(PUZZLE_INPUT), is(1524750));
  }

  @Test
  void testGetAimDistMult_testInput() {
    assertThat(Day2.getAimDistMult(TEST_INPUT), is(900));
  }

  @Test
  void testGetAimDistMult_puzzleInput() {
    assertThat(Day2.getAimDistMult(PUZZLE_INPUT), is(1592426537));
  }
}
