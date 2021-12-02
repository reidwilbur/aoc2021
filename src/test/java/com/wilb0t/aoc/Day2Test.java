package com.wilb0t.aoc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class Day2Test {

  private static final List<Day2.Command> PUZZLE_INPUT = Input.load(Day2.INPUT_MAPPER);

  private static final List<Day2.Command> TEST_INPUT =
      Stream.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
          .map(Day2.INPUT_MAPPER)
          .collect(Collectors.toList());

  @Test
  public void testGetDistMult_testInput() {
    assertThat(Day2.getDistMult(TEST_INPUT), is(150));
  }

  @Test
  public void testGetDistMult_puzzleInput() {
    assertThat(Day2.getDistMult(PUZZLE_INPUT), is(1524750));
  }
  
  @Test
  public void testGetAimDistMult_testInput() {
    assertThat(Day2.getAimDistMult(TEST_INPUT), is(900));
  }

  @Test
  public void testGetAimDistMult_puzzleInput() {
    assertThat(Day2.getAimDistMult(PUZZLE_INPUT), is(1592426537));
  }
}
