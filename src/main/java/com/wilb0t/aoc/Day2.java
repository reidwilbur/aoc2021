package com.wilb0t.aoc;

import java.util.List;
import java.util.function.Function;

public class Day2 {
  public static final Function<String, Command> INPUT_MAPPER = (line) ->  {
    var parts = line.split(" ");
    var dir = parts[0];
    var dist = Integer.parseInt(parts[1]);
    return new Command(dir, dist);
  };
 
  public static int getDistMult(List<Command> commands) {
    var hz = 0;
    var ht = 0;

    for (var cmd : commands) {
      switch (cmd.dir()) {
        case "forward" -> hz += cmd.dist();
        case "up" -> ht -= cmd.dist();
        case "down" -> ht += cmd.dist();
      }
    }
    
    return hz*ht;
  }
  
  public static int getAimDistMult(List<Command> commands) {
    var hz = 0;
    var ht = 0;
    var aim = 0;
    
    for (var cmd : commands) {
      switch (cmd.dir()) {
        case "forward" -> {
          hz += cmd.dist();
          ht += aim * cmd.dist();
        }
        case "up" -> aim -= cmd.dist();
        case "down" -> aim += cmd.dist();
      }
    }
    
    return hz*ht;
  }
  
  public static record Command(String dir, int dist) { }
}
