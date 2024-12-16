// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

ext {
    val appCompatVersion by extra("1.5.1")
    val constraintLayoutVersion by extra("2.1.4")
    val coreTestingVersion by extra("2.1.0")
    val lifecycleVersion by extra("2.3.1")
    val materialVersion by extra("1.7.0")
    val roomVersion by extra("2.4.3")

// Testing versions
    val junitVersion by extra("4.13.2")
    val espressoVersion by extra("3.5.0")
    val androidxJunitVersion by extra("1.1.2")

}