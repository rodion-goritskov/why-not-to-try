package com.aqa.interview.calculator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExpressionCalculatorTest {
    private val calculator = calculator()

    @Test
    fun `addition of two integers`() {
        assertEquals("3", calculator.evaluate("1 + 2"))
    }

    @Test
    fun `multiple of two integers`() {
        assertEquals("6", calculator.evaluate("3 * 2"))
    }

    @Test
    fun `division of two integers`() {
        assertEquals("3", calculator.evaluate("6 / 2"))
    }

    @Test
    fun `minus of two integers`() {
        assertEquals("2", calculator.evaluate("5 - 3"))
    }

    @Test
    fun `unary minus`() {
        assertEquals("-3", calculator.evaluate("- 3"))
    }

    @Test
    fun `parentheses operation order`() {
        assertEquals("20", calculator.evaluate("4 * (3 + 2)"))
    }

    @Test
    fun `decimals division`() {
        assertEquals("2.5", calculator.evaluate("5 / 2"))
    }

    @Test
    fun `decimals multiplication`() {
        assertEquals("6.25", calculator.evaluate("2.5 * 2.5"))
    }

    @Test
    fun `empty request`() {
        val exception = assertThrows<RuntimeException> {
            calculator.evaluate("")
        }
        assertEquals(exception.message, "Expression must not be blank.")
    }

    @Test
    fun `more than max int`() {
        assertEquals("${Integer.MAX_VALUE + 1L}", calculator.evaluate("${Integer.MAX_VALUE} + 1"))
    }

    @Test
    @Disabled
    fun `more than max long`() {
        assertEquals("", calculator.evaluate("${Long.MAX_VALUE} + 1"))
    }

    @Test
    fun `less than min int`() {
        assertEquals("${Integer.MIN_VALUE - 1L}", calculator.evaluate("${Integer.MIN_VALUE} - 1"))
    }

    @Test
    @Disabled
    fun `less than min long`() {
        assertEquals("", calculator.evaluate("${Long.MIN_VALUE} - 1"))
    }

    @Test
    fun `malformed input`() {
        assertThrows<RuntimeException> {
            calculator.evaluate("35 * abc")
        }
    }

    @Test
    fun `no spaces`() {
        assertEquals("21", calculator.evaluate("1+(4*5)"))
    }

    @Test
    fun `large scaled decimals`() {
        assertEquals("21", calculator.evaluate("1+(4*5)"))
    }

    @Test
    fun `negative decimals`() {
        assertEquals("-9.7", calculator.evaluate("-10 + 0.1 + 0.1 + 0.1"))
    }

    @Test
    fun alloperations() {
        assertEquals("72", calculator.evaluate("-3 + 100 - (2.5 * (100 / -10))"))
    }

}
