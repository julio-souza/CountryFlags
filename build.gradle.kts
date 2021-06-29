// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    Dependencies.addRepos(repositories)
    dependencies {
        classpath(Dependencies.Gradle.android)
        classpath(Dependencies.Gradle.kotlin)
    }
}

allprojects {
    Dependencies.addRepos(repositories)
}

tasks.register("clean").configure {
    delete(rootProject.buildDir)
}