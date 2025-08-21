package com.example.moviescompose.ui


// functie helper care mapeaza codul WMO intr-un text descriptiv
fun wmoToText(code: Int): String = when (code) {
    0 -> "Cer senin"
    1, 2, 3 -> "Partial noros"
    45, 48 -> "Ceata"
    51, 53, 55 -> "Burnita"
    61, 63, 65 -> "Ploaie"
    71, 73, 75 -> "Ninsoare"
    80, 81, 82 -> "Averse"
    95 -> "Furtuna"
    96, 99 -> "Furtuna cu grindina"
    else -> "Conditii variabile ($code)"
}
