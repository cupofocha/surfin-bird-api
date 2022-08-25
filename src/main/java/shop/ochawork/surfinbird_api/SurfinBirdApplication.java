package shop.ochawork.surfinbird_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class SurfinBirdApplication {

    private static void createUserDir(final String dirName) throws IOException {
        final File homeDir = new File(System.getProperty("user.home"));
        final File dir = new File(homeDir, dirName);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Unable to create " + dir.getAbsolutePath());
        }
    }

    public static void main(String[] args) throws IOException {
        createUserDir("/SurfinBird/Images");

        SpringApplication.run(SurfinBirdApplication.class, args);
    }

}
