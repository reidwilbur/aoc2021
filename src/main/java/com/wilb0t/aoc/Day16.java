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
    
    String version();
    
    int length();
    
    List<Packet> subpackets();
    
    static Packet parse(String input, int offset) {
      var typeid = input.substring(offset + 3, offset + 6);
      if (typeid.equals("100")) {
        return LitVal.parse(input, offset);
      } else {
        return Op.parse(input, offset);
      }
    }
  }
  
  public static record LitVal(String version, String typeid, String value, int length) implements Packet { 

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
      return new LitVal(version, typeid, bldr.toString(), packetlen);
    }
  }
  
  public static record Op(String version, String typeid, List<Packet> subpackets, int length) implements Packet {
    public static Op parse(String input, int offset) {
      var version = input.substring(offset, offset + 3);
      var typeid = input.substring(offset + 3, offset + 6);
      var lentypeid = input.charAt(offset + 6) - '0';
      var subpackets = new ArrayList<Packet>();
      if (lentypeid == 0) {
        var subpackLen = Integer.parseInt(input.substring(offset + 7, offset + 7 + 15), 2);
        var parsed = 0;
        var subofs = offset + 7 + 15;
        while (parsed < subpackLen) {
          var packet = Packet.parse(input, subofs);
          subpackets.add(packet);
          parsed += packet.length();
          subofs += packet.length();
        }
        return new Op(version, typeid, subpackets, subofs - offset);
      } else {
        var subpackCount = Integer.parseInt(input.substring(offset + 7, offset + 7 + 11), 2);
        var subofs = offset + 7 + 11;
        while (subpackCount > 0) {
          var packet = Packet.parse(input, subofs);
          subpackets.add(packet);
          subpackCount -= 1;
          subofs += packet.length();
        }
        return new Op(version, typeid, subpackets, subofs - offset);
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
      versum += Integer.parseInt(p.version(), 2);
    }
    
    return versum;
  }
}
