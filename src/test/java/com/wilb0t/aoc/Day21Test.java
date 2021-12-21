package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class Day21Test {

  private static final Day21.GameState PUZZLE_INPUT = new Day21.GameState(2,8,0,0, true);
  
  private static final Day21.GameState TEST_INPUT = new Day21.GameState(4,8,0,0, true);

  @Test
  void testPlayDiracDiceDeterm_testInput() {
    assertThat(Day21.playDiracDiceDeterm(TEST_INPUT.p1pos(), TEST_INPUT.p2pos()), is(739785L));
  }

  @Test
  void testPlayDiracDiceDeterm_puzzleInput() {
    assertThat(Day21.playDiracDiceDeterm(PUZZLE_INPUT.p1pos(), PUZZLE_INPUT.p2pos()), is(1196172L));
  }
  
  @Test
  void testPlatDiracDice_testInput() {
    assertThat(Day21.playDiracDice(TEST_INPUT), is(444356092776315L));
  }

  @Test
  void testPlatDiracDice_puzzleInput() {
    assertThat(Day21.playDiracDice(PUZZLE_INPUT), is(106768284484217L));
  }
}