package nl.smerik.adventofcode.aoc2024.model.bridge;

import lombok.Getter;

import java.util.List;

@Getter
public class Equation {

    private final Long testValue;
    private final List<Long> numbers;
    private final int numbersCount;

    public Equation(final Long testValue, final List<Long> numbers) {
        this.testValue = testValue;
        this.numbers = numbers;
        this.numbersCount = numbers.size();
    }

    public boolean isEquationTrue(final boolean applyConcatenationOperator) {
        return evaluate(numbers.get(0), 1, applyConcatenationOperator);
    }

    private boolean evaluate(final long number, final int index, boolean applyConcatenationOperator) {
        if (index >= numbersCount) {
            return number == testValue;
        }
        if (number > testValue) {
            return false;
        }
        return sum(number, index, applyConcatenationOperator) || multiply(number, index, applyConcatenationOperator)
                || (applyConcatenationOperator && concatenate(number, index));
    }

    private boolean sum(final long number, final int index, final boolean applyConcatenationOperator) {
        long result = number + numbers.get(index);
        return evaluate(result, index + 1, applyConcatenationOperator);
    }

    private boolean multiply(final long number, final int index, final boolean applyConcatenationOperator) {
        long result = number * numbers.get(index);
        return evaluate(result, index + 1, applyConcatenationOperator);
    }

    private boolean concatenate(final long number, final int index) {
        long result = Long.parseLong("" + number + numbers.get(index));
        return evaluate(result, index + 1, true);
    }
}
