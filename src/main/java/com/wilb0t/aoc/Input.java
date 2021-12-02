package com.wilb0t.aoc;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Input {

  public static Input PUZZLE = new Input("");
  public static Input TEST = new Input("-test");
  private final String suffix;

  private Input(String suffix) {
    this.suffix = suffix;
  }

  static URI getInputUri(Class<?> caller, String suffix) throws URISyntaxException {
    var path = "/" + caller.getName().replace('.', '/').replace("Test", "") + suffix + ".txt";
    return Objects.requireNonNull(Input.class.getResource(path)).toURI();
  }

  public int[] loadInts() {
    try {
      var caller =
          StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
      return Files.readAllLines(Path.of(getInputUri(caller, suffix)), StandardCharsets.UTF_8)
          .stream()
          .mapToInt(Integer::parseInt)
          .toArray();
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public String[] loadStrings() {
    try {
      var caller =
          StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
      return Files.readAllLines(Path.of(getInputUri(caller, suffix)), StandardCharsets.UTF_8)
          .toArray(String[]::new);
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public <T> List<T> load(Function<String, T> mapper) {
    try {
      var caller =
          StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
      return Files.readAllLines(Path.of(getInputUri(caller, suffix)), StandardCharsets.UTF_8)
          .stream()
          .map(mapper)
          .collect(Collectors.toList());
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
