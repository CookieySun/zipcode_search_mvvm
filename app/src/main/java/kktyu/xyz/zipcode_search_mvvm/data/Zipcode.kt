package kktyu.xyz.zipcode_search_mvvm.data

class Zipcode {
    var firstHalf = 0
    var secondHalf = 0

    fun Zipcode(firstHalf: Int, secondHalf: Int) {
        this.firstHalf = firstHalf
        this.secondHalf = secondHalf
    }
}