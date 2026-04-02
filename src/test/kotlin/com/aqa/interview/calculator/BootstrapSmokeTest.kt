package com.aqa.interview.calculator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull

class BootstrapSmokeTest {

    @Test
    fun `calculator bootstrap reflects environment`() {
        val result = runCatching { calculator() }

        if (result.isSuccess) {
            // Running inside CI — the private runtime is injected.
            // Verify the calculator is actually usable.
            assertNotNull(result.getOrNull())
        } else {
            // Running locally — no runtime available.
            val exception = result.exceptionOrNull()
            assertNotNull(exception)
            assertEquals(IllegalStateException::class, exception::class)
            assertEquals(
                "The calculator is not available in this environment. Run your tests through the CI service.",
                exception.message,
            )
        }
    }
}
