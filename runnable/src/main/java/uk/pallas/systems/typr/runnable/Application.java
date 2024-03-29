package uk.pallas.systems.typr.runnable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Typr is a simple webservice, its goal is simply to hold information about different fields types, Post Code (UK),
 * Latitude (Decimal Degrees),Mobile Country Code, etc.. The goal is to hold a description for each field, validation
 * information for the field and ideally some logic for translation. One of the key things is a "categories" which
 * can be assigned to a type, this is so I can say something is a good 'edge' or this is 'geo' (comes in handy
 * for downstream processing).
 */
@SpringBootApplication
@EnableJpaRepositories("uk.pallas.systems.typr.domain")
@EntityScan(basePackages = {"uk.pallas.systems.typr.domain.entities.v1",
  "uk.pallas.systems.typr.domain.entities.v1.validation",
  "uk.pallas.systems.typr.domain.entities.v1.validation.number"})
@ComponentScan("uk.pallas.systems.typr")
public class Application {

  /** Empty utility class constructor. */
  public Application() {
    // empty
  }

  /**
   * Main entry point into Typr.
   *
   * @param args these are ignored.
   */
  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }


}
