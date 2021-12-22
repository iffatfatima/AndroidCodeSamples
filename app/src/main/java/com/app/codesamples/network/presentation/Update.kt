package com.app.codesamples.network.presentation

data class Update(val state: STATE,
                  val message: String)
enum class STATE {
    LOADING,
    ERROR,
    SUCCESS,
}