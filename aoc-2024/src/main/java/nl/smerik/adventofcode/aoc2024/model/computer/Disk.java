package nl.smerik.adventofcode.aoc2024.model.computer;

import java.util.Arrays;

public class Disk {

    private static final int FREE_SPACE_BLOCK = -1;

    private final int[] diskLayout;

    public Disk(final String line) {
        this.diskLayout = parseLine(line);
    }

    private int[] parseLine(final String line) {
        final int[] diskMap = line.chars().map(Character::getNumericValue).toArray();
        final int diskSize = Arrays.stream(diskMap).sum();
        final int[] result = new int[diskSize];
        int blockIndex = 0;
        int fileID = 0;
        boolean isFile = true;
        for (final int length : diskMap) {
            for (int i = 0; i < length; i++) {
                result[blockIndex] = isFile ? fileID : FREE_SPACE_BLOCK;
                blockIndex++;
            }
            if (isFile) {
                fileID++;
            }
            isFile = !isFile;
        }
        return result;
    }

    public void defragment() {
        int leftIndex = 0;
        int rightIndex = diskLayout.length - 1;
        while (true) {
            leftIndex = findLeftmostFreeBlockIndex(leftIndex);
            rightIndex = findRightmostFileBlockIndex(rightIndex);
            if (leftIndex >= rightIndex) {
                break;
            }
            diskLayout[leftIndex] = diskLayout[rightIndex];
            diskLayout[rightIndex] = FREE_SPACE_BLOCK;
        }
    }

    private int findLeftmostFreeBlockIndex(final int startIndex) {
        for (int i = startIndex; i < diskLayout.length; i++) {
            if (diskLayout[i] == FREE_SPACE_BLOCK) {
                return i;
            }
        }
        return diskLayout.length;
    }

    private int findRightmostFileBlockIndex(int rightIndex) {
        for (int i = rightIndex; i > 0; i--) {
            if (diskLayout[i] != FREE_SPACE_BLOCK) {
                return i;
            }
        }
        return 0;
    }

    public long calculateChecksum() {
        long result = 0L;
        for (int i = 0; i < diskLayout.length; i++) {
            if (diskLayout[i] == FREE_SPACE_BLOCK) {
                continue;
            }
            result += (long) i * diskLayout[i];
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (final int block : diskLayout) {
            builder.append(block == FREE_SPACE_BLOCK ? "." : block);
        }
        return builder.toString();
    }
}
