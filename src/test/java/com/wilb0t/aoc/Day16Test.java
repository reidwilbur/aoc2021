package com.wilb0t.aoc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Day16Test {

  private static final String PUZZLE_INPUT = Input.PUZZLE.loadStrings()[0];

  @Test
  void testLitValParse() {
    var packet = Day16.LitVal.parse("110100101111111000101000", 0);
    var exp = new Day16.LitVal(6, 4, 2021L, 21);
    assertThat(packet, is(exp));
  }
  
  @Test
  void testOpParse_lenid0() {
    var packet = Day16.Op.parse("00111000000000000110111101000101001010010001001000000000", 0);
    var exp = new Day16.Op(
        1,
        6,
        List.of(
            new Day16.LitVal(6, 4, 10L, 11),
            new Day16.LitVal(2, 4, 20L, 16)
        ),
        49);
    assertThat(packet, is(exp));
  }

  @Test
  void testOpParse_lenid1() {
    var packet = Day16.Op.parse("11101110000000001101010000001100100000100011000001100000", 0);
    var exp = new Day16.Op(
        7,
        3,
        List.of(
            new Day16.LitVal(2, 4, 1L, 11),
            new Day16.LitVal(4, 4, 2L, 11),
            new Day16.LitVal(1, 4, 3L, 11)
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
  
  @Test
  void testGetPacketValue_testInput() {
    assertThat(Day16.getPacketValue("C200B40A82"), is(3L));
    assertThat(Day16.getPacketValue("04005AC33890"), is(54L));
    assertThat(Day16.getPacketValue("880086C3E88112"), is(7L));
    assertThat(Day16.getPacketValue("CE00C43D881120"), is(9L));
    assertThat(Day16.getPacketValue("D8005AC2A8F0"), is(1L));
    assertThat(Day16.getPacketValue("F600BC2D8F"), is(0L));
    assertThat(Day16.getPacketValue("9C005AC2F8F0"), is(0L));
    assertThat(Day16.getPacketValue("9C0141080250320F1802104A08"), is(1L));
  }
  
  @Test
  void testGetPacketValue_puzzleInput() {
    assertThat(Day16.getPacketValue(PUZZLE_INPUT), is(739303923668L));
  }
}