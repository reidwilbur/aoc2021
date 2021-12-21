package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day21 {
  
  public static long playDiracDiceDeterm(int p1pos, int p2pos) {
    var p1score = 0;
    var p2score = 0;
    var dice = 0;
    
    while (p2score < 1000) {
      var roll = (3 * dice) + 6;
      dice += 3;
      p1pos = (((p1pos - 1) + roll) % 10) + 1;
      p1score += p1pos;

      if (p1score >= 1000) {
        return (long)dice * p2score;
      }
      
      roll = (3 * dice) + 6;
      dice += 3;
      p2pos = (((p2pos - 1) + roll) % 10) + 1;
      p2score += p2pos;
    }
    
    return (long)dice * p1score;
  }
  
  public static record GameState(int p1pos, int p2pos, int p1score, int p2score, boolean p1turn) { 
    public GameState next(int roll) {
      var pos = (p1turn) ? p1pos : p2pos;
      var score = (p1turn) ? p1score : p2score;
      
      pos = (((pos - 1) + roll) % 10) + 1;
      score += pos;
      if (p1turn) {
        return new GameState(pos, p2pos, score, p2score, false);
      } else {
        return new GameState(p1pos, pos, p1score, score, true);
      }
    }
  }
  
  public static long playDiracDice(GameState state) {
    var cache = new HashMap<GameState, long[]>();
    var wins = getWins(state, cache);
    return Math.max(wins[0], wins[1]);
  }
  
  public static long[] getWins(GameState state, Map<GameState, long[]> cache) {
    if (state.p1score >= 21) {
      return new long[]{1,0};
    } else if (state.p2score >= 21) {
      return new long[]{0,1};
    }
    
    if (cache.containsKey(state)) {
      return cache.get(state);
    }

    var wins = new long[2];
    var nextStates = new ArrayList<GameState>();
    for (var r1 = 1; r1 < 4; r1++) {
      for (var r2 = 1; r2 < 4; r2++) {
        for (var r3 = 1; r3 < 4; r3++) {
          nextStates.add(state.next(r1 + r2 + r3));
        }
      }
    }
    
    for (var nextState : nextStates) {
      var subwins = getWins(nextState, cache);
      wins[0] += subwins[0];
      wins[1] += subwins[1];
    }
    
    cache.put(state, wins);
    return wins;
  }
}
