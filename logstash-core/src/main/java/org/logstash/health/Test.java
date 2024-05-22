package org.logstash.health;

public class Test {
    public static void main(String... args) {
        final HealthReport healthReport = HealthReport.builder(new ApiReportBuilder())
                .withIndicator(new ManagedPipelinesIndicator())
                .withIndicator(new ResourceIndicator())
                .build();

        System.out.println(healthReport);
    }
}
