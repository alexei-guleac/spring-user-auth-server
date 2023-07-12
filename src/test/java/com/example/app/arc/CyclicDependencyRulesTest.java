package com.example.app.arc;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;


@AnalyzeClasses(packages = "com.example.app.*")
public class CyclicDependencyRulesTest {

  @ArchTest
  static final ArchRule no_cycles_by_controller_between_slices =
      slices().matching("..(controller).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

  @ArchTest
  static final ArchRule no_cycles_by_service_calls_between_slices =
      slices().matching("..(service).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

  @ArchTest
  static final ArchRule no_cycles_by_repository_between_slices =
      slices().matching("..(repository).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

  @ArchTest
  static final ArchRule no_cycles_by_converter_between_slices =
      slices().matching("..(converter).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

  @ArchTest
  static final ArchRule no_cycles_by_member_dependencies_between_slices =
      slices().matching("..(membercycle).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();
}
