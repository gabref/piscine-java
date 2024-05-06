# ImagesToChar

## Requirements

Having java and javac installed and available in PATH env variable.

## Compile and Run

### Compile
```bash
mkdir -p target; find src -type f -name "*.java" | xargs javac -cp src -d target
```

### Arquive

1. c - create file
2. m - path to manifest
3. f - output file name
4. -C - root directory + the path in root directory

```bash
cp -R src/resources target/
jar cmf src/manifest.txt target/images-to-chars-printer.jar -C target .
chmod u+x target/images-to-chars-printer.jar
```

### Run

```bash
java -jar target/images-to-chars-printer.jar . O
```
