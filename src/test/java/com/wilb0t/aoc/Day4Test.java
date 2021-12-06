package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class Day4Test {

  private static final String[] PUZZLE_LINES = Input.PUZZLE.loadStrings();

  private static final String[] TEST_LINES = Input.TEST.loadStrings();

  @Test
  void testParseInput() {
    var input = Day4.Input.parseInput(TEST_LINES);

    assertThat(
        input.numbers(),
        is(
            List.of(
                7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8,
                19, 3, 26, 1)));
    
    assertThat(input.boards().size(), is(3));
  }

  @Test
  void testParseBoard() {
    var rows =
        List.of(
            Set.of(22, 13, 17, 11, 0),
            Set.of(8, 2, 23, 4, 24),
            Set.of(21, 9, 14, 16, 7),
            Set.of(6, 10, 3, 18, 5),
            Set.of(1, 12, 20, 15, 19));
    var cols =
        List.of(
            Set.of(22, 8, 21, 6, 1),
            Set.of(13, 2, 9, 10, 12),
            Set.of(17, 23, 14, 3, 20),
            Set.of(11, 4, 16, 18, 15),
            Set.of(0, 24, 7, 5, 19));
    assertThat(Day4.Board.parse(2, TEST_LINES), is(new Day4.Board(rows, cols)));
  }
  
  @Test
  void testGetWinningScore_testInput() {
    Day4.Input testInput = Day4.Input.parseInput(TEST_LINES);
    assertThat(Day4.getWinningScore(testInput), is(4512));
  }

  @Test
  void testGetWinningScore_puzzleInput() {
    Day4.Input puzzleInput = Day4.Input.parseInput(PUZZLE_LINES);
    assertThat(Day4.getWinningScore(puzzleInput), is(38594));
  }
  
  @Test
  void testGetLosingScore_testInput() {
    Day4.Input testInput = Day4.Input.parseInput(TEST_LINES);
    assertThat(Day4.getLosingScore(testInput), is(1924));
  }

  @Test
  void testGetLosingScore_puzzleInput() {
    Day4.Input puzzleInput = Day4.Input.parseInput(PUZZLE_LINES);
    assertThat(Day4.getLosingScore(puzzleInput), is(21184));
  }
}
