package com.gulfunion.medicalapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MedicalApiApplication

fun main(args: Array<String>) {
	runApplication<MedicalApiApplication>(*args)
}
