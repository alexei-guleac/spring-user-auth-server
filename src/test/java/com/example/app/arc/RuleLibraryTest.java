package com.example.app.arc;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;


@AnalyzeClasses(packages = "com.example.app.*")
class RuleLibraryTest {

  @ArchTest
  static final ArchRules LIBRARY = ArchRules.in(RuleSetsTest.class);

  @ArchTest
  static final ArchRules FURTHER_CODING_RULES = ArchRules.in(CodingRulesTest.class);

  @ArchTest
  static final ArchRules SLICES_ISOLATION_RULES = ArchRules.in(SlicesIsolationTest.class);
}
