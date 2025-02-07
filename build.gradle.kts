plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly ("org.springframework.boot:spring-boot-devtools")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.springframework.boot:spring-boot-starter-security")

    // QueryDSL
    implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor ("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")
    annotationProcessor ("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor ("jakarta.persistence:jakarta.persistence-api")

    // Swagger
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    // Lombok & mapstruct
    implementation ("org.projectlombok:lombok:1.18.30")
    implementation ("org.mapstruct:mapstruct:1.5.3.Final")
    implementation ("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor ("org.projectlombok:lombok:1.18.30")
    annotationProcessor ("org.mapstruct:mapstruct-processor:1.5.3.Final")
    annotationProcessor ("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    testCompileOnly ("org.projectlombok:lombok:1.18.30")

    // Logging
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")

    // Test
    testImplementation ("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation ("org.mockito:mockito-core:5.5.0")
    testImplementation ("org.mockito:mockito-junit-jupiter:5.5.0")
    testImplementation ("org.assertj:assertj-core:3.24.2")
    implementation("com.h2database:h2")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
