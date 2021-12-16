package com.wilb0t.aoc;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day16 {
  
  static Map<Character, String> HEX_TO_BIN = Map.ofEntries(
      new AbstractMap.SimpleEntry<>('0', "0000"),
      new AbstractMap.SimpleEntry<>('1', "0001"),
      new AbstractMap.SimpleEntry<>('2', "0010"),
      new AbstractMap.SimpleEntry<>('3', "0011"),
      new AbstractMap.SimpleEntry<>('4', "0100"),
      new AbstractMap.SimpleEntry<>('5', "0101"),
      new AbstractMap.SimpleEntry<>('6', "0110"),
      new AbstractMap.SimpleEntry<>('7', "0111"),
      new AbstractMap.SimpleEntry<>('8', "1000"),
      new AbstractMap.SimpleEntry<>('9', "1001"),
      new AbstractMap.SimpleEntry<>('A', "1010"),
      new AbstractMap.SimpleEntry<>('B', "1011"),
      new AbstractMap.SimpleEntry<>('C', "1100"),
      new AbstractMap.SimpleEntry<>('D', "1101"),
      new AbstractMap.SimpleEntry<>('E', "1110"),
      new AbstractMap.SimpleEntry<>('F', "1111")
  );
  
  public static String toBin(String hex) {
    var bldr = new StringBuilder(hex.length() * 4);
    hex.chars().forEach(c -> bldr.append(HEX_TO_BIN.get((char)c)));
    return bldr.toString();
  }
  
  public interface Packet { 
    
    int version();
    
    int length();
    
    List<Packet> subpackets();
    
    long value();
    
    static Packet parse(String input, int offset) {
      var typeid = input.substring(offset + 3, offset + 6);
      if (typeid.equals("100")) {
        return LitVal.parse(input, offset);
      } else {
        return Op.parse(input, offset);
      }
    }
  }
  
  public static record LitVal(int version, int typeid, long value, int length) implements Packet { 

    @Override
    public List<Packet> subpackets() {
      return List.of();
    }
    
    public static LitVal parse(String input, int offset) {
      var version = input.substring(offset, offset + 3);
      var typeid = input.substring(offset + 3, offset + 6);
      var idx = offset + 6;
      var bldr = new StringBuilder();
      while (input.charAt(idx) != '0') {
        bldr.append(input, idx + 1, idx + 5);
        idx += 5;
      }
      bldr.append(input, idx + 1, idx + 5);
      var packetlen = idx + 5 - offset;
      return new LitVal(
          Integer.parseInt(version, 2), 
          Integer.parseInt(typeid, 2), 
          Long.parseLong(bldr.toString(), 2), 
          packetlen);
    }
  }
  
  public static record Op(int version, int typeid, List<Packet> subpackets, int length) implements Packet {
    
    @Override
    public long value() {
      return switch (typeid) {
        case 0 -> subpackets.stream().mapToLong(Packet::value).sum();
        case 1 -> subpackets.stream().mapToLong(Packet::value).reduce(1, (l, r) -> l * r);
        case 2 -> subpackets.stream().mapToLong(Packet::value).summaryStatistics().getMin();
        case 3 -> subpackets.stream().mapToLong(Packet::value).summaryStatistics().getMax();
        case 5 -> (subpackets.get(0).value() > subpackets.get(1).value()) ? 1L : 0L;
        case 6 -> (subpackets.get(0).value() < subpackets.get(1).value()) ? 1L : 0L;
        case 7 -> (subpackets.get(0).value() == subpackets.get(1).value()) ? 1L : 0L;
        default -> throw new RuntimeException("Unknown packet type " + typeid);
      };
    }
    
    public static Op parse(String input, int offset) {
      var version = input.substring(offset, offset + 3);
      var typeid = input.substring(offset + 3, offset + 6);
      var lentypeid = input.charAt(offset + 6) - '0';
      
      var subpackets = new ArrayList<Packet>();
      
      if (lentypeid == 0) {
        var subpackLen = Integer.parseInt(input.substring(offset + 7, offset + 7 + 15), 2);
        var parsedLen = 0;
        var subpackStart = offset + 7 + 15;
        
        while (parsedLen < subpackLen) {
          var packet = Packet.parse(input, subpackStart + parsedLen);
          subpackets.add(packet);
          parsedLen += packet.length();
        }
        return new Op(
            Integer.parseInt(version, 2), 
            Integer.parseInt(typeid, 2), 
            subpackets, 
            subpackStart - offset + parsedLen);
      } else {
        var subpackCount = Integer.parseInt(input.substring(offset + 7, offset + 7 + 11), 2);
        var subpackofs = offset + 7 + 11;
        
        while (subpackCount > 0) {
          var packet = Packet.parse(input, subpackofs);
          subpackets.add(packet);
          subpackCount -= 1;
          subpackofs += packet.length();
        }
        return new Op(
            Integer.parseInt(version, 2), 
            Integer.parseInt(typeid, 2), 
            subpackets, 
            subpackofs - offset);
      }
    }    
  }
  
  public static int getVersionNumSum(String input) {
    var bits = toBin(input);
    var packet = Packet.parse(bits, 0);
    
    var versum = 0;
    
    var queue = new ArrayDeque<Packet>();
    queue.add(packet);
    
    while(!queue.isEmpty()) {
      var p = queue.pop();
      queue.addAll(p.subpackets());
      versum += p.version();
    }
    
    return versum;
  }
  
  public static long getPacketValue(String input) {
    var bits = toBin(input);
    var packet = Packet.parse(bits, 0);
    return packet.value();
  }
  
}
