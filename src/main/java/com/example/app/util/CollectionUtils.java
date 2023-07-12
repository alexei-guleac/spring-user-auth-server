package com.example.app.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Collection utilities
 */
@Service
@Slf4j
public class CollectionUtils {

  public static <T> Collector<T, ?, List<T>> toSortedList(Comparator<? super T> comparator) {
    return Collectors.collectingAndThen(
        Collectors.toCollection(() -> new TreeSet<>(comparator)), ArrayList::new);
  }

}
