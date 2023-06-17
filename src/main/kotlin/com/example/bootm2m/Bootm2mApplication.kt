package com.example.bootm2m

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Bootm2mApplication

fun main(args: Array<String>) {
	runApplication<Bootm2mApplication>(*args)
}
