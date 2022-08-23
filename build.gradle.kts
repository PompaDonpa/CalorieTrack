// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
<<<<<<< Updated upstream:build.gradle
    ext {
        compose_version = '1.2.1'
    }
=======
>>>>>>> Stashed changes:build.gradle.kts
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
<<<<<<< Updated upstream:build.gradle
        classpath 'com.android.tools.build:gradle:7.2.2'
        classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10'
=======
        classpath (Build.androidBuildTools)
        classpath (Build.hiltAndroidGradlePlugin)
        classpath (Build.kotlinGradlePlugin)
>>>>>>> Stashed changes:build.gradle.kts

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

<<<<<<< Updated upstream:build.gradle
task clean(type: Delete) {
    delete rootProject.buildDir
=======
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
>>>>>>> Stashed changes:build.gradle.kts
}
