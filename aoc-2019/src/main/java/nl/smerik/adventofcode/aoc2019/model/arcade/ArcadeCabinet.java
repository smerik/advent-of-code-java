package nl.smerik.adventofcode.aoc2019.model.arcade;

import lombok.Getter;
import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;
import java.util.List;

@Getter
public class ArcadeCabinet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArcadeCabinet.class);

    private static final Point SCORE_POINT = new Point(-1, 0);

    private final IntcodeComputer computer;

    private final Screen screen;

    private long score;

    private enum JoystickState {
        NEUTRAL_POSITION(0),
        TILT_LEFT(-1),
        TILT_RIGHT(1);

        private final int code;

        JoystickState(final int code) {
            this.code = code;
        }
    }

    public ArcadeCabinet(final IntcodeComputer computer) {
        this.computer = computer;
        this.screen = new Screen();
        this.score = 0;
    }

    public void start(final boolean playGame) {
        final List<Long> output = this.computer.run();
        updateScreen(output);
        if (playGame) {
            while (computer.isPausedExecution() || screen.countTilesByType(Tile.Type.BLOCK) > 0L) {
                playGame();
                screen.render();
            }
        }
    }

    private void playGame() {
        final JoystickState joystickMove = determineJoystickMove();
        final List<Long> output = this.computer.run((long) joystickMove.code);
        updateScreen(output);
    }

    private JoystickState determineJoystickMove() {
        final Tile paddle = screen.findAny(Tile.Type.HORIZONTAL_PADDLE);
        final Tile ball = screen.findAny(Tile.Type.BALL);
        if (paddle.getPoint().x > ball.getPoint().x) {
            return JoystickState.TILT_LEFT;
        }
        if (paddle.getPoint().x < ball.getPoint().x) {
            return JoystickState.TILT_RIGHT;
        }
        return JoystickState.NEUTRAL_POSITION;
    }

    private void updateScreen(final List<Long> output) {
        for (int i = 0; i < output.size(); i = i + 3) {
            final Point point = new Point(Math.toIntExact(output.get(i)), Math.toIntExact(output.get(i + 1)));
            final Long value = output.get(i + 2);
            if (point.equals(SCORE_POINT)) {
                this.score = value;
                LOGGER.debug("New score: {}", this.score);
                continue;
            }
            final Tile.Type type = Tile.Type.valueOfTileTypeId(Math.toIntExact(value));
            screen.draw(point, type);
        }
    }
}
