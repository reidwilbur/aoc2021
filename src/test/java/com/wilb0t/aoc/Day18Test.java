package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static com.wilb0t.aoc.Day18.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day18Test {

  private static final List<Day18.Number> PUZZLE_INPUT = Input.PUZZLE.load(Day18::parse);
  
  private static final List<Day18.Number> TEST_INPUT = Input.TEST.load(Day18::parse);
  
  @Test
  void testParse() {
    assertThat(Day18.Number.parse("[1,2]").getKey(), is(Pair.of(1,2)));
    assertThat(
        Day18.Number.parse("[[1,2],3]").getKey(), 
        is(Pair.of(Pair.of(1,2), 3)));
    assertThat(
        Day18.Number.parse("[9,[8,7]]").getKey(), 
        is(Pair.of(9, Pair.of(8,7))));
    assertThat(
        Day18.Number.parse("[[1,9],[8,5]]").getKey(),
        is(new Pair(Pair.of(1,9), Pair.of(8,5 ))));
    assertThat(
        Day18.Number.parse("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]").getKey(),
        is(
            Pair.of(
                new Pair(
                    new Pair(Pair.of(1,2), Pair.of(3,4)), 
                    new Pair(Pair.of(5,6), Pair.of(7,8))), 
                9)));
    assertThat(
        Day18.Number.parse("[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]").getKey(),
        is(
            new Pair(
                new Pair(
                    Pair.of(9, Pair.of(3,8)),
                    Pair.of(Pair.of(0,9), 6)),
                Pair.of(
                    new Pair(
                        Pair.of(3,7),
                        Pair.of(4,9)),
                    3
                )
            )
        )
    );
  }
  
}