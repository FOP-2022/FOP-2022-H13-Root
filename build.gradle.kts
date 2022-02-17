import org.sourcegrade.submitter.submit

plugins {
    java
    application
    eclipse
    id("org.sourcegrade.style") version "1.2.0"
    id("org.sourcegrade.submitter") version "0.4.0"
}

version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

val grader: SourceSet by sourceSets.creating {
    val test = sourceSets.test.get()
    compileClasspath += test.compileClasspath + test.output
    runtimeClasspath += output + compileClasspath + test.runtimeClasspath
}

submit {
    assignmentId = "h13"
    studentId = "ab12cdef"
    firstName = "sol_first"
    lastName = "sol_last"
}

dependencies {
    "graderImplementation"("org.sourcegrade:jagr-launcher:0.4.0-SNAPSHOT")
    implementation("com.formdev:flatlaf:2.0.1")
    implementation("com.google.guava:guava:31.0.1-jre")
    "graderImplementation"("org.mockito:mockito-core:4.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

application {
    mainClass.set("h13.TwoSimpleWindows")
}

tasks {
    test {
        useJUnitPlatform()
    }
    val graderTest by creating(Test::class) {
        group = "verification"
        testClassesDirs = grader.output.classesDirs
        classpath = grader.runtimeClasspath
        useJUnitPlatform()
    }
    named("check") {
        dependsOn(graderTest)
    }
    create<Jar>("graderJar") {
        group = "build"
        afterEvaluate {
            archiveFileName.set("FOP-2022-H13-${project.version}.jar")
            from(sourceSets.main.get().allSource)
            from(sourceSets.test.get().allSource)
            from(grader.allSource)
        }
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    jar {
        enabled = false
    }
}
