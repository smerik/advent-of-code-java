package nl.smerik.adventofcode.aoc2020.service.math;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

@Service
public class MathService {

    private enum Operator {
        ADDITION('+'),
        MULTIPLICATION('*'),
        GROUP_START('('),
        GROUP_END(')');

        private final int token;

        Operator(final char token) {
            this.token = token;
        }

        public static Operator valueOfToken(final int token) {
            for (final Operator operator : values()) {
                if (operator.token == token) {
                    return operator;
                }
            }
            throw new IllegalArgumentException("Invalid token '" + token + "'");
        }

        /**
         * Checks if this operator has a greater precedence than given operator.
         *
         * @param operator the operator to compare to
         * @return <code>true</code> on greater precedence; <code>false</code> otherwise
         */
        public boolean hasGreaterPrecedence(final Operator operator) {
            return operator != GROUP_START;
        }
    }

    /**
     * Evaluates given infix expression and returns the output of the evaluation.
     *
     * @param expression the infix expression
     * @return the evaluation result
     */
    public Long evaluate(final String expression) {
        final Queue<Object> rpn = parseInfixToReversePolishNotation(expression);
        return evaluateReversePolishNotation(rpn);
    }

    /**
     * Parses a mathematical expression specified in infix notation
     * and produces the Reverse Polish notation (RPN) output represented on an output queue.
     * <p>
     * See
     * <a href="https://en.wikipedia.org/wiki/Shunting-yard_algorithm">Sunting-yard algorithm</a>
     * for more detail.
     *
     * @param infix the infix expression
     * @return the output as Reverse Polish notation represented on a queue
     */
    private Queue<Object> parseInfixToReversePolishNotation(final String infix) {
        final Queue<Object> output = new ArrayDeque<>();
        final Deque<Operator> operators = new ArrayDeque<>();

        try (StringReader reader = new StringReader(infix)) {
            final StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
            while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    output.add((long) streamTokenizer.nval);
                    continue;
                }

                final Operator operator = Operator.valueOfToken(streamTokenizer.ttype);
                if (operator == Operator.GROUP_END) {
                    while (!operators.isEmpty()
                            && operators.peekLast() != Operator.GROUP_START) {
                        output.add(operators.pollLast());
                    }
                    operators.pollLast(); // remove Operator.GROUP_START
                } else {
                    while (!operators.isEmpty()
                            && operators.peekLast() != Operator.GROUP_START
                            && operators.peekLast().hasGreaterPrecedence(operator)) {
                        output.add(operators.pollLast());
                    }
                    operators.add(operator);
                }
            }
        } catch (IOException e) {
            throw new InfixParseException("Error while tokenizing infix expression '" + infix + "'.", e);
        }

        while (!operators.isEmpty()) {
            output.add(operators.pollLast());
        }
        return output;
    }

    private Long evaluateReversePolishNotation(final Queue<Object> rpn) {
        final Deque<Long> result = new ArrayDeque<>();

        Object item;
        while ((item = rpn.poll()) != null) {
            if (item instanceof Operator operator) {
                final Long operand2 = result.pop();
                final Long operand1 = result.pop();
                switch (operator) {
                    case ADDITION -> result.push(operand1 + operand2);
                    case MULTIPLICATION -> result.push(operand1 * operand2);
                    default -> throw new IllegalArgumentException("Cannot handle operator " + operator);
                }
            } else {
                result.push((Long) item);
            }

        }
        return result.pop();
    }

    /**
     * Sums all the results of the evaluated infix expressions
     *
     * @param expressions the infix expressions
     * @return the sum of all evaluated expressions
     */
    public Long sumEvaluation(final List<String> expressions) {
        return expressions.stream()
                .map(this::evaluate)
                .mapToLong(Long::valueOf)
                .sum();
    }
}
