package com.uprr.app.tng.spring.hmi;

import com.uprr.app.tng.spring.GameBoard;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by david on 8/8/16.
 */
public class HumanInterface {
    private static final int QUIT_NUMBER = -1;

    @Nonnull private final Scanner     scanner;
    @Nonnull private final PrintStream printer;
    @Nonnull private final GameBoard   gameBoard;

    public HumanInterface(@Nonnull final Scanner scanner, @Nonnull final PrintStream printer,
                          @Nonnull final GameBoard gameBoard) {
        this.scanner = scanner;
        this.printer = printer;
        this.gameBoard = gameBoard;
    }

    public void run() {
        final int totalTiles   = this.gameBoard.getMapArea();
        final int pokemonCount = this.getInt("How many pokemon should be generated (" + totalTiles + " total tiles)");
        this.gameBoard.fillBoard(pokemonCount);

        Pair<Integer, Integer> coordinate;
        do {
            coordinate = this.getIntPair();
            if (this.shouldContinue(coordinate)) {
                this.printer.println(this.gameBoard.check(coordinate.getLeft(), coordinate.getRight()));
                this.printer.println();
            }
        } while (this.shouldContinue(coordinate));
    }

    private boolean shouldContinue(@Nonnull final Pair<Integer, Integer> coordinate) {
        return coordinate.getLeft() != QUIT_NUMBER && coordinate.getRight() != QUIT_NUMBER;
    }

    private int getInt(@Nonnull final String prompt) {
        this.printer.println(prompt);
        this.printer.print(">>> ");
        return this.scanner.nextInt();
    }

    private Pair<Integer, Integer> getIntPair() {
        this.printer.println("Enter a coordinate to check for pokemon");
        this.printer.print(">>> ");
        final int left  = this.scanner.nextInt();
        final int right = this.scanner.nextInt();
        return ImmutablePair.of(left, right);
    }
}
