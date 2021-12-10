package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class Day10Test {

  private static final String[] PUZZLE_INPUT = Input.PUZZLE.loadStrings();

  private static final String[] TEST_INPUT = Input.TEST.loadStrings();

  @Test
  void testGetSyntaxScore_testInput() {
    assertThat(Day10.getSyntaxScore(TEST_INPUT), is(26397));
  }

  @Test
  void testGetSyntaxScore_puzzleInput() {
    assertThat(Day10.getSyntaxScore(PUZZLE_INPUT), is(392043));
  }

  @Test
  void testGetCompScore_testInput() {
    assertThat(Day10.getCompletionScore(TEST_INPUT), is(288957L));
  }

  @Test
  void testGetCompScore_puzzleInput() {
    assertThat(Day10.getCompletionScore(PUZZLE_INPUT), is(1605968119L));
  }

  @Test
  void testGetCompScore_line() {
    var cases =
        Map.of(
            "[({(<(())[]>[[{[]{<()<>>", 288957L,
            "[(()[<>])]({[<{<<[]>>(", 5566L,
            "(((({<>}<{<{<>}{[]{[]{}", 1480781L,
            "{<[[]]>}<{[{[{[]{()[[[]", 995444L,
            "<{([{{}}[<[[[<>{}]]]>[]]", 294L);
    for (var tcase : cases.entrySet()) {
      assertThat(Day10.getCompletionScore(tcase.getKey()), is(Optional.of(tcase.getValue())));
    }
  }
}
