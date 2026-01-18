plugins {
    id("buildlogic.java-application-conventions")
}

group = project.group
version = project.version

application {
    // Define the main class for the application.
    mainModule = "intro.fxml.mvci.gui"
    mainClass = "intro.app.Launcher"
    applicationDefaultJvmArgs = listOf("--enable-native-access=javafx.graphics")
}

dependencies {
    implementation(project(":domain-service"))
    runtimeOnly("org.slf4j:slf4j-simple")
}

