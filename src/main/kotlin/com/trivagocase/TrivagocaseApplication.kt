package com.trivagocase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class TrivagocaseApplication

fun main(args: Array<String>) {
	runApplication<TrivagocaseApplication>(*args)
}
