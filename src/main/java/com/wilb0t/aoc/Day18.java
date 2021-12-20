package com.wilb0t.aoc;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Day18 {
  
  public static Number parse(String line) {
    var parsed = Number.parse(line);
    if (parsed.getValue().isEmpty()) {
      return parsed.getKey();
    }
    throw new RuntimeException("Parse failed " + parsed);
  }
  
  public sealed interface Number permits Pair, RegNumber { 
    static Map.Entry<Number, String> parse(String line) {
      var regNumPatLt = Pattern.compile("^\\[(\\d+),");
      var regNumPatRt = Pattern.compile("^(\\d+)\\]+,*");

      var regNumMatcherLt = regNumPatLt.matcher(line);
      Number left = null;
      String rest = null;
      if (regNumMatcherLt.find()) {
        left = new RegNumber(Integer.parseInt(regNumMatcherLt.group(1)));
        rest = line.substring(regNumMatcherLt.end());
      } else {
        var ltparsed = Number.parse(line.substring(1));
        left = ltparsed.getKey();
        rest = ltparsed.getValue();
      }
     
      var regNumMatcherRt = regNumPatRt.matcher(rest);
      Number right = null;
      if (regNumMatcherRt.find()) {
        right = new RegNumber(Integer.parseInt(regNumMatcherRt.group(1)));
        rest = rest.substring(regNumMatcherRt.end());
      } else {
        var rtparsed = Number.parse(rest);
        right = rtparsed.getKey();
        rest = rtparsed.getValue();
      }
      
      return new AbstractMap.SimpleEntry<>(new Pair(left, right), rest);
    }
  }

  public static final class Pair implements Number { 
    Number left;
    Number right;
    
    public Pair(Number left, Number right) {
      this.left = left;
      this.right = right;
    }
    
    public static Pair of(int left, int right) {
      return new Pair(new RegNumber(left), new RegNumber(right));
    }
    
    public static Pair of(int left, Number right) {
      return new Pair(new RegNumber(left), right);
    }

    public static Pair of(Number left, int right) {
      return new Pair(left, new RegNumber(right));
    }
    
    public Pair add(Pair other) {
      var pair = new Pair(this, other);
      return pair;
    }
    
    

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Pair pair = (Pair) o;
      return left.equals(pair.left) && right.equals(pair.right);
    }

    @Override
    public int hashCode() {
      return Objects.hash(left, right);
    }
  }

  public static record RegNumber(int value) implements Number { }
}
