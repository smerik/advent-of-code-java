package nl.smerik.adventofcode.io;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Utility class offering various methods to parse the Advent of Code puzzle input files.
 */
public final class PuzzleInputParser {

    private PuzzleInputParser() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Parses all lines of given resource into a list of strings.
     *
     * @param resource the resource to parse
     * @return a list of strings
     * @throws UnresolvableURIException if the resource cannot be resolved as URI,
     *                                  i.e. if the resource is not available as descriptor
     * @throws OpenPuzzleInputException if an I/O error occurs opening the file
     */
    public static List<String> parseToString(final Resource resource) {
        final Path path = getPath(resource);
        try (final Stream<String> lines = readLines(path)) {
            return lines.toList();
        }
    }

    /**
     * Parses all lines of given resource into a list of integers.
     *
     * @param resource the resource to parse
     * @return a list of integers
     * @throws UnresolvableURIException if the resource cannot be resolved as URI,
     *                                  i.e. if the resource is not available as descriptor
     * @throws OpenPuzzleInputException if an I/O error occurs opening the file
     */
    public static List<Integer> parseToInt(final Resource resource) {
        final Path path = getPath(resource);
        try (final Stream<String> lines = readLines(path)) {
            return lines.mapToInt(Integer::valueOf)
                        .boxed()
                        .toList();
        }
    }

    /**
     * Parses all lines of given resource into a list of longs.
     *
     * @param resource the resource to parse
     * @return a list of longs
     * @throws UnresolvableURIException if the resource cannot be resolved as URI,
     *                                  i.e. if the resource is not available as descriptor
     * @throws OpenPuzzleInputException if an I/O error occurs opening the file
     */
    public static List<Long> parseToLong(final Resource resource) {
        final Path path = getPath(resource);
        try (final Stream<String> lines = readLines(path)) {
            return lines.mapToLong(Long::valueOf)
                        .boxed()
                        .toList();
        }
    }

    /**
     * Converts the given Resource to a {@link Path} object.
     *
     * @param resource the resource to convert
     * @return the resulting {@code Path}
     * @throws UnresolvableURIException if the resource cannot be resolved as URI,
     *                                  i.e. if the resource is not available as descriptor
     */
    private static Path getPath(final Resource resource) {
        try {
            return Paths.get(resource.getURI());
        } catch (final IOException e) {
            throw new UnresolvableURIException(e.getMessage(), e);
        }
    }

    /**
     * Read all lines from a file as a {@code Stream}.
     * Bytes from the file are decoded into characters using the {@link StandardCharsets#UTF_8 UTF-8}
     * {@link Charset charset}.
     *
     * <p>
     * The returned stream contains a reference to an open file.
     * The file is closed by closing the stream.
     *
     * <p>
     * The file contents should not be modified during the execution of the terminal stream operation.
     * Otherwise, the result of the terminal stream operation is undefined.
     *
     * <p>
     * This method works as if invoking it were equivalent to evaluating the expression:
     * <blockquote>Files.lines(path, StandardCharsets.UTF_8)</blockquote>
     *
     * @param path the path to the file
     * @return the lines from the file as a Stream
     * @throws OpenPuzzleInputException if an I/O error occurs opening the file
     * @apiNote This method must be used within a try-with-resources statement or similar control structure
     * to ensure that the stream's open file is closed promptly after the stream's operations have completed.
     */
    private static Stream<String> readLines(final Path path) {
        try {
            return Files.lines(path);
        } catch (final IOException e) {
            throw new OpenPuzzleInputException(e.getMessage(), e);
        }
    }
}
