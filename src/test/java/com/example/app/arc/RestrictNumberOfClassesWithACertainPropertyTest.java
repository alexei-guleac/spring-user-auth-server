package com.example.app.arc;

import com.tngtech.archunit.junit.AnalyzeClasses;


@AnalyzeClasses(packages = "com.example.app.*")
public class RestrictNumberOfClassesWithACertainPropertyTest {

//   @ArchTest
//   static final ArchRule no_new_classes_should_implement_SomeBusinessInterface =
//           classes().that().implement(SomeBusinessInterface.class)
//                   .should().containNumberOfElements(lessThanOrEqualTo(1))
//                   .because("from now on new classes should implement " + SomeOtherBusinessInterface.class.getName());

}
