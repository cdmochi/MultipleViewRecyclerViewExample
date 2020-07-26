package chayapam.s.twoviewholder

abstract class ListItem {

    companion object {
        const val TYPE_SEMESTER = 0
        const val TYPE_COURSE = 1
    }

    abstract fun getType() : Int

}