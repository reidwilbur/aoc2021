package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day7Test {

  private static final int[] PUZZLE_INPUT = Input.PUZZLE.loadIntsFlat();

  private static final int[] TEST_INPUT = Input.TEST.loadIntsFlat();

  @Test
  void testGetCheapestAlignment_testInput() {
    assertThat(Day7.getCheapestAlignment(TEST_INPUT), is(37));
  }

  @Test
  void testGetCheapestAlignment_puzzleInput() {
    assertThat(Day7.getCheapestAlignment(PUZZLE_INPUT), is(340056));
  }
  
  @Test
  void testGetCheapestAlignmentWithCost_testInput() {
    assertThat(Day7.getCheapestAlignmentWithCost(TEST_INPUT), is(168));
  }

  @Test
  void testGetCheapestAlignmentWithCost_puzzleInput() {
    assertThat(Day7.getCheapestAlignmentWithCost(PUZZLE_INPUT), is(96592275));
  }
}