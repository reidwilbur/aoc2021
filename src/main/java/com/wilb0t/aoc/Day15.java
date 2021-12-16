package com.wilb0t.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Day15 {
  
  public static int getLeastRisk(int[][] riskGraph, int repeats) {
    var rows = riskGraph.length * repeats;
    var cols = riskGraph[0].length * repeats;
    
    var verts = rows * cols;
    
    var dist = new int[verts];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[0] = 0;
    
    var visited = new boolean[verts];
    var unvisited = new PriorityQueue<Integer>(Comparator.comparingInt(v -> dist[v]));
    unvisited.add(0);
   
    var end = verts - 1;
    while (!unvisited.isEmpty()) {
      var minVert = unvisited.poll();
      visited[minVert] = true;
      
      if (minVert == end) {
        return dist[minVert];
      }
   
      var nbors = getNbors(minVert, rows, cols);
      for (var nbor : nbors) {
        if (!visited[nbor]) {
          var newdist = dist[minVert] + getVertRisk(nbor, rows, cols, riskGraph);
          if (newdist < dist[nbor]) {
            dist[nbor] = newdist;
          }
          unvisited.remove(nbor);
          unvisited.add(nbor);
        }
      }
    }
    return -1;
  }
  
  static int getVertRisk(int idx, int rows, int cols, int[][] riskGraph) {
    var col = idx % cols;
    var row = idx / rows;
    
    var colreps = col / riskGraph[0].length;
    var rowreps = row / riskGraph.length;
    
    var baserisk = riskGraph[row % riskGraph.length][col % riskGraph[0].length];
    
    return (((baserisk - 1) + colreps + rowreps) % 9) + 1;
  }
  
  static List<Integer> getNbors(int idx, int rows, int cols) {
    var nbors = new ArrayList<Integer>(4);
    var rowminidx = (idx / rows) * cols;
    var rowmaxidx = rowminidx + cols;
    
    var upidx = idx - cols;
    var dnidx = idx + cols;
    var ltidx = idx - 1;
    var rtidx = idx + 1;
    
    if (upidx >= 0) {
      nbors.add(upidx);
    }
    if (dnidx < rows * cols) {
      nbors.add(dnidx);
    }
    if (rtidx < rowmaxidx) {
      nbors.add(rtidx);
    }
    if (ltidx >= rowminidx) {
      nbors.add(ltidx);
    }
    return nbors;
  }
}
