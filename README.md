# Standalone Stream
Research and development of Data Streaming and Processing using Spring Cloud Data Flow and Kafka.

## Building the apps

Use the appropriate binder profiles `kafka` (active by default) or `rabbit` to build a binary for use with that binder.

```bash
$./mvnw clean package -Pkafka
```

## Building the distribution zip file

```bash
$./mvnw package -Pdist

```
