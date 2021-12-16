package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day16Test {

  private static final String PUZZLE_INPUT = Input.PUZZLE.loadStrings()[0];

  //private static final String TEST_INPUT = Input.TEST.loadStrings()[0];

  @Test
  void testLitValParse() {
    var packet = Day16.LitVal.parse("110100101111111000101000", 0);
    var exp = new Day16.LitVal("110", "100", "011111100101", 21);
    assertThat(packet, is(exp));
  }
  
  @Test
  void testOpParse_lenid0() {
    var packet = Day16.Op.parse("00111000000000000110111101000101001010010001001000000000", 0);
    var exp = new Day16.Op(
        "001",
        "110",
        List.of(
            new Day16.LitVal("110", "100", "1010", 11),
            new Day16.LitVal("010", "100", "00010100", 16)
        ),
        49);
    assertThat(packet, is(exp));
  }

  @Test
  void testOpParse_lenid1() {
    var packet = Day16.Op.parse("11101110000000001101010000001100100000100011000001100000", 0);
    var exp = new Day16.Op(
        "111",
        "011",
        List.of(
            new Day16.LitVal("010", "100", "0001", 11),
            new Day16.LitVal("100", "100", "0010", 11),
            new Day16.LitVal("001", "100", "0011", 11)
        ),
        51);
    assertThat(packet, is(exp));
  }

  @Test
  void testToBin() {
    assertThat(Day16.toBin("38006F45291200"), is("00111000000000000110111101000101001010010001001000000000"));
  }
  
  @Test
  void testGetVersionNumSum_testInput() {
    assertThat(Day16.getVersionNumSum("8A004A801A8002F478"), is(16));
    assertThat(Day16.getVersionNumSum("620080001611562C8802118E34"), is(12));
    assertThat(Day16.getVersionNumSum("C0015000016115A2E0802F182340"), is(23));
    assertThat(Day16.getVersionNumSum("A0016C880162017C3686B18A3D4780"), is(31));
  }
  
  @Test
  void testGetVersionNumSum_puzzleInput() {
    assertThat(Day16.getVersionNumSum(PUZZLE_INPUT), is(889));
  }
}