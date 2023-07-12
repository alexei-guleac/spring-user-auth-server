package com.example.app.arc;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;


@AnalyzeClasses(packages = "com.example.app.*")
class RuleSetsTest {

  @ArchTest
  private final ArchRules CODING_RULES = ArchRules.in(CodingRulesTest.class);

  @ArchTest
  private final ArchRules NAMING_CONVENTION_RULES = ArchRules.in(NamingConventionTest.class);
}
