package org.logstash.health;

public class ResourceIndicator extends ProbeIndicator<IndicatorReport> {

    public ResourceIndicator() {
        addProbe(new DummyCpuProbe());
        addProbe(new DummyMemoryProbe());
        addProbe(new DummyDiskProbe());
    }

    static class DummyMemoryProbe implements Probe {
        @Override
        public String identifier() {
            return "heap_usage";
        }

        @Override
        public ProbeCapture capture() {
            return ProbeCapture.green("probe.heap.usage");
        }
    }

    static class DummyDiskProbe implements Probe {
        @Override
        public String identifier() {
            return "disk_usage";
        }

        @Override
        public ProbeCapture capture() {
            return new ProbeCapture(
                    "probe.disk.usage",
                    Status.YELLOW,
                    new Diagnosis("High disk usage", "add more disk", "https://foo.bar/disk"),
                    new Details()
            );
        }
    }

    static class DummyCpuProbe implements Probe {
        @Override
        public String identifier() {
            return "cpu_usage";
        }

        @Override
        public ProbeCapture capture() {
            return new ProbeCapture(
                    "probe.cpu.usage",
                    Status.RED,
                    new Diagnosis("High CPU usage", "add more CPU", "https://foo.bar/cpu"),
                    new Details()
            );
        }
    }

    @Override
    public String identifier() {
        return "resources";
    }
}
