package com.gabodev.recipebook

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.MockKAnnotations
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipesRepositoryTest {
    @Test
    fun setup() {
        MockKAnnotations.init(this)
    }
}
