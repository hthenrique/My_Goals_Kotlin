package com.example.mygoalskotlin.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class UserTest{
    private val expectedUser: User = User()

    @BeforeEach
    fun setup(){
        expectedUser.name = "Test"
        expectedUser.setEmail("test@test.com")
        expectedUser.setPassword("Test#Test1")
    }

    @Test
    fun testUser(){
        var testUser: User = User()
        testUser = expectedUser

        Assertions.assertEquals(expectedUser, testUser)
    }

}