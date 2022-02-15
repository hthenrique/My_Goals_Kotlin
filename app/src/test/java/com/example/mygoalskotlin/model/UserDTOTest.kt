package com.example.mygoalskotlin.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class UserDTOTest{
    private val expectedUserDTO: UserDTO = UserDTO()

    @BeforeEach
    fun setup(){
        expectedUserDTO.name = "Test"
        expectedUserDTO.email = "test@test.com"
        expectedUserDTO.password = "Test#Test1"
    }

    @Test
    fun testUser(){
        var testUserDTO: UserDTO = UserDTO()
        testUserDTO = expectedUserDTO

        Assertions.assertEquals(expectedUserDTO, testUserDTO)
    }

}