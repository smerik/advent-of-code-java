package nl.smerik.adventofcode.aoc2024.model.computer;

import org.apache.commons.math3.util.Pair;

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

    public void defragmentIndividualBlocks() {
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

    private int findRightmostFileBlockIndex(final int rightIndex) {
        for (int i = rightIndex; i > 0; i--) {
            if (diskLayout[i] != FREE_SPACE_BLOCK) {
                return i;
            }
        }
        return 0;
    }

    public void defragmentWholeFiles() {
        int firstRightIndex = diskLayout.length - 1;
        while (true) {
            final Pair<Integer, Integer> rightBlockRangeIndexes = findRightmostWholeFileBlockRangeIndexes(firstRightIndex);
            firstRightIndex = rightBlockRangeIndexes.getFirst() - 1;
            if (firstRightIndex <= 0) {
                break;
            }

            final int leftmostFreeSpanIndex = findLeftmostFreeSpanIndex(rightBlockRangeIndexes);
            if (leftmostFreeSpanIndex == -1 || leftmostFreeSpanIndex >= firstRightIndex) {
                // No available space
                continue;
            }
            moveFileBlock(rightBlockRangeIndexes, leftmostFreeSpanIndex);
        }
    }

    private Pair<Integer, Integer> findRightmostWholeFileBlockRangeIndexes(final int rightIndex) {
        int fileID = FREE_SPACE_BLOCK;
        int startIndex = -1;
        int endIndex = -1;
        for (int i = rightIndex; i > 0; i--) {
            if (diskLayout[i] != FREE_SPACE_BLOCK) {
                if (fileID == FREE_SPACE_BLOCK) {
                    fileID = diskLayout[i];
                    endIndex = i;
                }
                if (fileID == diskLayout[i]) {
                    startIndex = i;
                }
            } else {
                if (fileID != FREE_SPACE_BLOCK) {
                    break;
                }
            }
        }
        if (startIndex == -1) {
            throw new IllegalArgumentException("Index out of range");
        }
        return new Pair<>(startIndex, endIndex);
    }

    private int findLeftmostFreeSpanIndex(final Pair<Integer, Integer> spanIndexes) {
        final int fileSize = spanIndexes.getSecond() - spanIndexes.getFirst() + 1;
        int firstFreeSpaceIndex = -1;
        int endFreeSpaceIndex;
        for (int i = 0; i < diskLayout.length; i++) {
            if (diskLayout[i] == FREE_SPACE_BLOCK) {
                if (firstFreeSpaceIndex == -1) {
                    firstFreeSpaceIndex = i;
                }
                endFreeSpaceIndex = i;
                if (endFreeSpaceIndex - firstFreeSpaceIndex + 1 == fileSize) {
                    return firstFreeSpaceIndex;
                }
            } else {
                // Range not met in last free span: reset span index
                firstFreeSpaceIndex = -1;
            }
        }
        return -1;
    }

    private void moveFileBlock(final Pair<Integer, Integer> rightRangeIndexes, final int leftmostFreeSpanIndex) {
        for (int fileBlockIndex = rightRangeIndexes.getFirst(); fileBlockIndex <= rightRangeIndexes.getSecond(); fileBlockIndex++) {
            int freeIndex = leftmostFreeSpanIndex + fileBlockIndex - rightRangeIndexes.getFirst();
            diskLayout[freeIndex] = diskLayout[fileBlockIndex];
            diskLayout[fileBlockIndex] = FREE_SPACE_BLOCK;
        }
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
