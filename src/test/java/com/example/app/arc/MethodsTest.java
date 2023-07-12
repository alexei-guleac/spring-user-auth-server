package com.example.app.arc;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noCodeUnits;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;


@AnalyzeClasses(packages = "com.example.app.*")
public class MethodsTest {

  @ArchTest
  static ArchRule all_public_methods_in_the_controller_layer_should_return_API_response_wrappers =
      methods()
          .that().areDeclaredInClassesThat().resideInAPackage("..app..")
          .and().arePublic()
          .should().haveRawReturnType(ResponseEntity.class)
          .because(
              "we do not want to couple the client code directly to the return types of the encapsulated module");

  @ArchTest
  static ArchRule code_units_in_DAO_layer_should_not_be_Secured =
      noCodeUnits()
          .that().areDeclaredInClassesThat().resideInAPackage("..persistence..")
          .should().beAnnotatedWith(Secured.class);
}
