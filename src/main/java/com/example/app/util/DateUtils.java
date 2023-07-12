package com.example.app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * Date utilities
 */
@Service
@Qualifier("dateUtils")
public class DateUtils {


  private final String baseDateFormatStr = "dd-MMM-yyyy HH:mm:ss";

  private final DateFormat baseDateFormat = new SimpleDateFormat(baseDateFormatStr);

  public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
    long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
    return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(startDate::plusDays)
        .collect(Collectors.toList());
  }

  public long getDaysCountBetween(LocalDateTime fromDate, LocalDateTime toDate) {
    return ChronoUnit.DAYS.between(fromDate, toDate) + 1;
  }

  public long getDaysCountBetween(LocalDate fromDate, LocalDate toDate) {
    return ChronoUnit.DAYS.between(fromDate, toDate) + 1;
  }

  //Converting milliseconds to Date using java.util.Date
  //current time in milliseconds
  public String millsToBaseFormattedDate(long currentDateTime) {
    return baseDateFormat.format(new Date(currentDateTime));
  }

}
