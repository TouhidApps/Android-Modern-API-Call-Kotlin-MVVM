buildscript {

    val kotlinVersion = "1.5.10"

    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // Mark-1
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.35")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

tasks.register("clean",  Delete::class)  {
    delete(rootProject.buildDir)
}