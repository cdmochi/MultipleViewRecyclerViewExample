package chayapam.s.twoviewholder

class SemesterListItem : ListItem() {

    lateinit var header : String

    override fun getType() : Int{
       return ListItem.TYPE_SEMESTER
    }
}