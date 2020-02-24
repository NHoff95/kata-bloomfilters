# Solution for Kata: Bloomfilters

This is a solution for the [Kata Bloomfilters](http://codekata.com/kata/kata05-bloom-filters/).
It reads a given dictionary and checks all words of a provided file, if those words are part of that dictionary ("Spell Checker").
All words, which are not contained in the dictionary, will be printed.
As hash functions for the bloomfilter, MD5 and SHA1 are used. To add any other hash function, implement the [Hash](src/main/kotlin/de/nhoff95/kata/bloomfilters/hash/Hash.kt) interface

In order to run the tests, the [wordlist](http://codekata.com/data/wordlist.txt), which is provided on the kata page, needs to be added to [src/test/resources](src/test/resources).

### Running the tool

To run the tool, execute the [Main](src/main/kotlin/de/nhoff95/kata/bloomfilters/Main.kt) with the following parameters:
```shell script
<size of bitmap>  <absolutepath/to/dictionary> <absolutepath/to/texttocheck>
```

Example:
```shell script
5000000 "/Users/testuser/dictionary.txt" "/Users/testuser/text.txt"
```
