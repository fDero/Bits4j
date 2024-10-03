![license: MIT](https://img.shields.io/badge/license-MIT-blue)
![version: pre-release](https://img.shields.io/badge/version-pre--release-red)

# Bits4j
A simple user-friendly library to perform bits manipulations in java. You might find this useful if you're implementing
compression/encryption algorithms. 

### Brief explanation
- This library comes with the `BitValue` enum, wich has two values, `BitValue.ZERO` and `BitValue.ONE`. 
- You can create a `BitList` (an efficient implementation of `java.util.List<BitValue>`) to store them.
- You can use `BitListConversions` to convert between `byte`,`int`,`long`,`String` and `BitList`.
- The `BitInputStream` is a wrapper around `java.io.InputStream` that allows the user to read bit by bit.
- The `BitOutputStream` is a wrapper around `java.io.OutputStream` that allows the user to write bit by bit.
- The `flush` method of `BitOutputStream` ignores the last bits if they are not part of a byte.
- The `BitOutputStream` has a `roundUp` method that adds as many zeros as needed to complete the byte.
