# ImagesToChar

## Requirements

Having java and javac installed and available in PATH env variable.

## Compile and Run

### Compile
```bash
mkdir -p target; find src -type f -name "*.java" | xargs javac -cp src -d target
```

### Run

```bash
java -cp target edu._42roma.printer.app.Program . O ../it.bmp
```

Just copy and pasta this:

```bash
mkdir target; find src -type f -name "*.java" | xargs javac -cp src -d target && java -cp target edu._42roma.printer.app.Program . O ../it.bmp
```
