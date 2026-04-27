package com.example.equili

import com.example.equili.utils.ValidationUtils
import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for main application logic.
 */
class ExampleUnitTest {

    @Test
    fun testPasswordValidation() {
        // Valid password: Start Capital, 6+ length, Number, Symbol
        assertTrue(ValidationUtils.isValidPassword("Pass123!"))

        // Invalid: No capital
        assertFalse(ValidationUtils.isValidPassword("pass123!"))

        // Invalid: Too short
        assertFalse(ValidationUtils.isValidPassword("P12!"))

        // Invalid: No number
        assertFalse(ValidationUtils.isValidPassword("Pass!!!"))

        // Invalid: No symbol
        assertFalse(ValidationUtils.isValidPassword("Pass123"))
    }

    @Test
    fun testEmailValidation() {
        assertTrue(ValidationUtils.isValidEmail("test@example.com"))
        assertTrue(ValidationUtils.isValidEmail("user.name@domain.co.za"))

        assertFalse(ValidationUtils.isValidEmail("invalid-email"))
        assertFalse(ValidationUtils.isValidEmail("test@domain"))
    }
}
