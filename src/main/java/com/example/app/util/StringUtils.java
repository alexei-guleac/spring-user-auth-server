package com.example.app.util;

import org.springframework.stereotype.Service;

@Service
public class StringUtils {

  public long countOccurrences(String input, char letter) {
    return input.chars().filter(ch -> ch == letter).count();
  }
}
