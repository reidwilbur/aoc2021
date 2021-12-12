package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Map;
import java.util.Set;
import com.wilb0t.aoc.Day12.Cave;
import org.junit.jupiter.api.Test;

public class Day12Test {

  private static final Map<Cave, Set<Cave>> PUZZLE_INPUT = Day12.parse(Input.PUZZLE.loadStrings());

  private static final Map<Cave, Set<Cave>> TEST_INPUT = Day12.parse(Input.TEST.loadStrings());
  
  @Test
  void testGetPathCount_testInput() {
    assertThat(Day12.getPathCount(TEST_INPUT), is(10));
  }

  @Test
  void testGetPathsCount_puzzleInput() {
    assertThat(Day12.getPathCount(PUZZLE_INPUT), is(3410));
  }

  @Test
  void testGetPathCountWithNonsense_testInput() {
    assertThat(Day12.getPathCountWithNonsene(TEST_INPUT), is(36));
  }

  @Test
  void testGetPathCountWithNonsense_puzzleInput() {
    assertThat(Day12.getPathCountWithNonsene(PUZZLE_INPUT), is(98796));
  }
}