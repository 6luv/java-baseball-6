package baseball;

import static baseball.Constant.END_MESSAGE;
import static baseball.Constant.EXIT;
import static baseball.Constant.INPUT_NUMBERS_MESSAGE;
import static baseball.Constant.INPUT_RETRY_EXIT_MESSAGE;
import static baseball.Constant.RETRY;
import static baseball.Constant.START_MESSAGE;

import java.util.Objects;

public class BaseballGame {
    private PlayerView playerView = new PlayerView();
    private GameResult gameResult = new GameResult();
    private Referee referee = new Referee();

    public void playGame() {
        playerView.output(START_MESSAGE);
        do {
            playSingleGame();
            playerView.output(END_MESSAGE);
            playerView.output(INPUT_RETRY_EXIT_MESSAGE);
        } while (isRetry());
    }

    private void playSingleGame() {
        Computer computer = new Computer();
        String computerNumbers = computer.getComputerNumbers();
        do {
            playerView.output(INPUT_NUMBERS_MESSAGE);
            String playerNumbers = playerView.input();
            Player player = new Player(playerNumbers);
            gameResult = referee.judge(computerNumbers, playerNumbers);
            playerView.output(gameResult.result());
        } while (!gameResult.isWin());
    }

    private boolean isRetry() {
        String playerChoice = playerView.input();
        validateRetryExit(playerChoice);
        return playerChoice.equals(RETRY);
    }

    private void validateRetryExit(String playerChoice) {
        if (!Objects.equals(playerChoice, RETRY) &&
                !Objects.equals(playerChoice, EXIT)) {
            throw new IllegalArgumentException("1, 2만 입력할 수 있습니다.");
        }
    }
}
