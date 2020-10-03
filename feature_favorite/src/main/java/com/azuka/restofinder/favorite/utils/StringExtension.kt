package com.azuka.restofinder.favorite.utils


/**
 * Created by ivanaazuka on 03/10/20.
 * Android Engineer
 */

fun String.formatPriceToSymbol(symbol: String = "$"): Pair<String, String> {
    val value = this.toInt()
    var priceRangeSymbolHighlight = ""
    for (index in 0 until value) {
        priceRangeSymbolHighlight += symbol
    }

    var priceRangeSymbolDim = ""
    for (index in 0 until 4 - value) {
        priceRangeSymbolDim += symbol
    }
    return Pair(priceRangeSymbolHighlight, priceRangeSymbolDim)
}