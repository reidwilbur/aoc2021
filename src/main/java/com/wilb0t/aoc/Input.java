package com.wilb0t.aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Input {

  public static Input PUZZLE = new Input("");
  public static Input TEST = new Input("-test");
  private final String suffix;

  private Input(String suffix) {
    this.suffix = suffix;
  }

  Stream<String> getInput(Class<?> caller) throws URISyntaxException, IOException {
    var pathStr = "/" + caller.getName().replace('.', '/').replace("Test", "") + suffix + ".txt";
    var path = Path.of(Objects.requireNonNull(Input.class.getResource(pathStr)).toURI());
    return Files.readAllLines(path, StandardCharsets.UTF_8).stream();
  }

  public int[] loadInts() {
    try {
      var caller =
          StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
      return getInput(caller).mapToInt(Integer::parseInt).toArray();
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public String[] loadStrings() {
    try {
      var caller =
          StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
      return getInput(caller).toArray(String[]::new);
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public <T> List<T> load(Function<String, T> mapper) {
    try {
      var caller =
          StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
      return getInput(caller).map(mapper).collect(Collectors.toList());
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
