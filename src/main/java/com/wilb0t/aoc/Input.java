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

  static URI getInputUri(Class<?> caller) throws URISyntaxException {
    var path = "/" + caller.getName().replace('.', '/').replace("Test", "") + ".txt";
    return Objects.requireNonNull(Input.class.getResource(path)).toURI();
  }

  public static int[] loadInts() {
    try {
      var caller =
          StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
      return Files.readAllLines(Path.of(getInputUri(caller)), StandardCharsets.UTF_8).stream()
          .mapToInt(Integer::parseInt)
          .toArray();
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public static String[] loadStrings() {
    try {
      var caller =
          StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
      return Files.readAllLines(Path.of(getInputUri(caller)), StandardCharsets.UTF_8)
          .toArray(String[]::new);
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> List<T> load(Function<String, T> mapper) {
    try {
      var caller =
          StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
      return Files.readAllLines(Path.of(getInputUri(caller)), StandardCharsets.UTF_8).stream()
          .map(mapper)
          .collect(Collectors.toList());
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
