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

    public boolean isEquationTrue() {
        return evaluate(numbers.get(0), 1);
    }

    public boolean evaluate(final long number, final int index) {
        if (index >= numbersCount) {
            return number == testValue;
        }
        if (number > testValue) {
            return false;
        }
        if (sum(number, index)) {
            return true;
        }
        return multiply(number, index);
    }

    private boolean sum(final long number, final int index) {
        long result = number + numbers.get(index);
        return evaluate(result, index + 1);
    }

    private boolean multiply(final long number, final int index) {
        long result = number * numbers.get(index);
        return evaluate(result, index + 1);
    }
}
