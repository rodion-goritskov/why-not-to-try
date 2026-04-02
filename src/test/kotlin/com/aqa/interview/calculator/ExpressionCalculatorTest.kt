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
    fun `less than min long`() {
        assertEquals("", calculator.evaluate("${Long.MIN_VALUE} - 1"))
    }

}
