package chayapam.s.twoviewholder

class SemesterListItem : ListItem() {

    lateinit var header : String
    var creditTaken : Int = 0


    override fun getType() : Int{
       return ListItem.TYPE_SEMESTER
    }


}