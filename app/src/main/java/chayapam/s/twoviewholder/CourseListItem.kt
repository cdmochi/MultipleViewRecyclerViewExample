package chayapam.s.twoviewholder

class CourseListItem : ListItem() {

    //There existed the courses if and only if the semester is present
    lateinit var courseRegistered : CourseRegistered

    override fun getType(): Int {
        return ListItem.TYPE_COURSE
    }


}