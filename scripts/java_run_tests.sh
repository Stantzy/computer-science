#!/bin/bash

sources="sources.txt"

find tests -name "*.java" > "$sources"
find . -path "./tests" -prune -o -name "*.java" -print >> "$sources"

javac @sources.txt
for f in $(find tests -name "*.class"); do
	class=$f
	class=${class%.class}
	class=${class//\//.}
	if [[ "$class" != "tests.algorithms.sorting.SortTest" ]]; then
		java -ea -cp . "$class"
	fi
done

for f in $(find . -path "./out" -prune -o -name "*.class" -print); do
	rm "$f"
done

rm "$sources"
