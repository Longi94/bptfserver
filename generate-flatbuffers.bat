del src\main\java\in\dragonbra\bptfserver\flatbuffers\*.* /s /q

flatc --java -o src/main/java flatbuffers/prices.fbs
flatc --java -o src/main/java flatbuffers/item-schema.fbs
