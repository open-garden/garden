package com.zipc.garden.parser.pict.constraint;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class PictConstraintParserTest {

    private PictConstraintParser sut;

    private Reader sutInput;

    @AfterEach
    public void teardown() throws IOException {
        sutInput.close();
    }

    @Test
    public void test_CanParseIf() throws ParseException {
        sutInput = new StringReader("if [A] = \"a1\" and [B] <> \"b2\" then [C] = \"c3\";");
        sut = new PictConstraintParser(sutInput);
        sut.Root();
    }

    @Test
    public void test_CanParseWithoutIf() throws ParseException {
        sutInput = new StringReader("([A] = \"a1\" and [B] <> \"b2\");");
        sut = new PictConstraintParser(sutInput);
        sut.Root();
    }

}
