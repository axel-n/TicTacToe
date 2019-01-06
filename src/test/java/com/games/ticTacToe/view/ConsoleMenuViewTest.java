package com.games.ticTacToe.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ConsoleMenuViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testEnterSizeIncorrectInputString() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("Xonstantin\n Oleg\n".getBytes());
        System.setIn(in);
        try {
            ConsoleMenuView.enterSize();
        }
        catch (final NoSuchElementException e){
            e.printStackTrace();
        }
        assertEquals("Enter board size:\n" +
                "Input is wrong, please enter correct integer greater than 3\n" +
                "Enter board size:\n", outContent.toString());
        outContent.reset();
    }

    @Test
    public void testEnterSizeIncorrectInputInt() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(in);
        try {
            ConsoleMenuView.enterSize();
        }
        catch (final NoSuchElementException e){
            e.printStackTrace();
        }
        assertEquals("Enter board size:\n" +
                "Input is wrong, please enter correct integer greater than 3\n" +
                "Enter board size:\n", outContent.toString());
        outContent.reset();
    }

    @Test
    public void testEnterSizeInputInt() throws Exception {
        final int expectedValue = 4;
        ByteArrayInputStream in = new ByteArrayInputStream("4\n".getBytes());
        System.setIn(in);
        assertEquals(expectedValue,ConsoleMenuView.enterSize());
    }

}