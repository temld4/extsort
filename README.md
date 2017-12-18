# extsort

Build:

mvn clean compile assembly:single

Generate test file:

java -jar ./target/[filename].jar generate [output file name] [lines in file] [line length]

Sort generated file:

java -jar ./target/[filename].jar sort [input file name] [subfile lines amount]
