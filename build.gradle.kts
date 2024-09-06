plugins {
   `java-library`
}

repositories {
    mavenCentral()
    flatDir{dirs ("/lib")}
}

java {                                      
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation ("net.vproject:cimageio:0.8.1")
    implementation ("org.apache.ant:ant:1.10.15")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.3")
}
