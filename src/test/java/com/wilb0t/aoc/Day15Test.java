package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day15Test {

  private static final int[][] PUZZLE_INPUT = Input.PUZZLE.loadIntGrid();

  private static final int[][] TEST_INPUT = Input.TEST.loadIntGrid();

  @Test
  void testGetLeastRisk_testInput() {
    assertThat(Day15.getLeastRisk(TEST_INPUT, 1), is(40));
  }
  
  @Test
  void testGetLeastRisk_testInput_big() {
    assertThat(Day15.getLeastRisk(TEST_INPUT, 5), is(315));
  }

  @Test
  void testGetLeastRisk_puzzleInput() {
    assertThat(Day15.getLeastRisk(PUZZLE_INPUT, 1), is(811));
  }

  @Test
  void testGetLeastRisk_puzzleInput_big() {
    assertThat(Day15.getLeastRisk(PUZZLE_INPUT, 5), is(3012));
  }

  @Test
  void testNbors() {
    assertThat(Day15.getNbors(0, 10, 10), is(List.of(10, 1)));
    assertThat(Day15.getNbors(9, 10, 10), is(List.of(19, 8)));
    assertThat(Day15.getNbors(10, 10, 10), is(List.of(0, 20, 11)));
    assertThat(Day15.getNbors(90, 10, 10), is(List.of(80, 91)));
    assertThat(Day15.getNbors(99, 10, 10), is(List.of(89, 98)));
  }
  
  @Test
  void testGetVertRisk() {
    assertThat(Day15.getVertRisk(11,50, 50, TEST_INPUT), is(2));
    assertThat(Day15.getVertRisk(21,50, 50, TEST_INPUT), is(3));
    assertThat(Day15.getVertRisk(41,50, 50, TEST_INPUT), is(5));

    assertThat(Day15.getVertRisk(50*9 + 8,50, 50, TEST_INPUT), is(8));
    assertThat(Day15.getVertRisk(50*19 + 8,50, 50, TEST_INPUT), is(9));
    assertThat(Day15.getVertRisk(50*29 + 8,50, 50, TEST_INPUT), is(1));
    
    assertThat(Day15.getVertRisk(50*10,50, 50, TEST_INPUT), is(2));
    assertThat(Day15.getVertRisk(50*20,50, 50, TEST_INPUT), is(3));
    assertThat(Day15.getVertRisk(50*49,50, 50, TEST_INPUT), is(6));
  }
}