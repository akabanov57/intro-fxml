# About the project.

This project was created after reading the
article <https://www.pragmaticcoding.ca/javafx/Mvci-Introduction>. I found it very useful. However,
I disagree with the author's assertion that FXML shouldn't be used. So, I reworked his example using
FXML and dependency injection. I think this project is easier to understand than the original. And
the architecture of this project can be called MVCI.

## How to build this project.

To build this project, you'll first need to build the
project <https://github.com/akabanov57/micronaut-javafx> and publish it to a local Maven server.
Next, specify this local Maven server in the project's
buildSrc/src/main/kotlin/buildlogic.java-common-conventions.gradle.kts file. Finally, navigate to
the project's root directory and run ./gradlew clean build.
