package nl.smerik.adventofcode.aoc2020.util;

/**
 * Utility class for breaking the Day 25 encryption key.
 */
public final class ComboBreakerUtil {

    private static final int SUBJECT_NUMBER = 7;
    private static final int DIVIDER = 20201227;

    private ComboBreakerUtil() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Determines the encryption key the handshake is trying to establish between the card and the door.
     *
     * @param publicKeyCard the public key of the card
     * @param publicKeyDoor the public key of the door
     * @return the encryption key
     */
    public static int determineEncryptionKey(final int publicKeyCard, final int publicKeyDoor) {
        final int loopSizeCard = determineLoopSize(publicKeyCard);
        final int loopSizeDoor = determineLoopSize(publicKeyDoor);
        // Although the encryption key only had to be calculated for either one of the device's
        // actually calculate both to do an insanity check
        final int encryptionKeyByCard = calculateEncryptionKey(publicKeyCard, loopSizeDoor);
        final int encryptionKeyByDoor = calculateEncryptionKey(publicKeyDoor, loopSizeCard);
        if (encryptionKeyByCard != encryptionKeyByDoor) {
            throw new IllegalStateException("Calculated encryption keys should be equal");
        }
        return encryptionKeyByCard;
    }

    /**
     * Determines the device's loop size for given public key.
     *
     * @param publicKey the device's public key
     * @return the loop size
     */
    public static int determineLoopSize(final int publicKey) {
        int loopSize = 0;
        int value = 1;
        do {
            value = calculateValue(value, SUBJECT_NUMBER);
            loopSize++;
        } while (value != publicKey);
        return loopSize;
    }

    /**
     * Calculates the encryption key for given subject number based on given  loop size.
     *
     * @param subjectNumber the subject number (a.k.a. the used public key)
     * @param loopSize      the loop size
     * @return the encryption key
     */
    public static int calculateEncryptionKey(final int subjectNumber, final int loopSize) {
        int result = 1;
        for (int i = 0; i < loopSize; i++) {
            result = calculateValue(result, subjectNumber);
        }
        return result;
    }

    /**
     * Calculates a new value for given value and subject number.
     * This calculation implements the step involved in transforming the subject number during handshake.
     *
     * @param value         the input value
     * @param subjectNumber the subject number to be transformed
     * @return the output value
     */
    public static int calculateValue(final int value, final int subjectNumber) {
        return (int) ((long) value * subjectNumber % DIVIDER);
    }
}
