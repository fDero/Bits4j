![license: MIT](https://img.shields.io/badge/license-MIT-blue)
![version: pre-release](https://img.shields.io/badge/version-pre--release-red)

# Bits4j
A simple user-friendly library to perform bits manipulations in java. You might find this useful if you're implementing
compression/encryption algorithms. 

### Brief overview
- This library comes with the `BitValue` enum, wich has two values, `BitValue.ZERO` and `BitValue.ONE`. 
- You can create a `BitList` (an efficient implementation of `List<BitValue>`) to store them.
- You can use `BitListConversions` to convert between `byte`,`int`,`long`,`String` and `BitList`.
- The `BitReader` is a wrapper around `InputStream` that allows the user to read bit by bit.
- The `BitWriter` is a wrapper around `OutputStream` that allows the user to write bit by bit.
- The `flush` method of `BitWriter` ignores the last bits if they are not part of a byte.
- The `BitWriter` has a `addPadding` method that adds zeros to complete the byte.
- The `BitListInputStream` it's an implementation of `InputStream` that reads from a `List<BitValue>`.
- The `BitListOutputStream` it's an implementation of `OutputStream` that writes to `List<BitValue>`.
