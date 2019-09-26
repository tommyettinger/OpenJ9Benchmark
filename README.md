# OpenJ9Benchmark
Small Gradle+libGDX project to show a performance regression between OpenJ9 versions

## NOPE, I SCREWED UP
There was no regression; OpenJ9 on Java 11 was configured to use a discrete GPU and
all others were using integrated graphics.

I'm still committing this as a libGDX Batch benchmark.