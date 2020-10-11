package io.github.margato.pix.register

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("io.github.margato.pix.register")
		.start()
}

