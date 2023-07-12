package com.example.app.util;

import java.util.Optional;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Data utilities
 */
@Service
@Slf4j
public class DataUtils {

  public static <T> Optional<T> resolve(Supplier<T> resolver) {
    try {
      T result = resolver.get();
      return Optional.ofNullable(result);
    } catch (NullPointerException e) {
      return Optional.empty();
    }
  }


  public static <T> Optional<T> resolveWithNumber(Supplier<T> resolver) {
    try {
      T result = resolver.get();
      return Optional.ofNullable(result);
    } catch (NullPointerException | NumberFormatException e) {
      return Optional.empty();
    }
  }
}
