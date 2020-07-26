package chayapam.s.twoviewholder

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.course_itemview.view.*
import kotlinx.android.synthetic.main.semester_itemview.view.*

// specify the inner class's ViewHolder as Parent's Class
class Adapter(var consolidatedList : ArrayList<ListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class SemesterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val header : TextView = itemView.ui_header_textview

    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var containter : ConstraintLayout = itemView.constraintLayout
        val courseCodeTv : TextView = itemView.ui_course_id_tv
        val courseName : TextView = itemView.ui_course_name_tv
        var credit : TextView = itemView.ui_course_credit_tv
        var evaluationGrade : TextView = itemView.ui_course_grade_tv


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var viewHolder : RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ListItem.TYPE_COURSE -> {
                val view = inflater.inflate(R.layout.course_itemview,parent,false)
                viewHolder = CourseViewHolder(view)
                viewHolder

            }

            else -> {
                val view = inflater.inflate(R.layout.semester_itemview,parent,false)
                viewHolder = SemesterViewHolder(view)
                viewHolder
            }

        }

    }

    override fun getItemCount(): Int {
        return consolidatedList.size
    }


    var headerPosition = -1
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {

            //cast ListItem to actual Object
            ListItem.TYPE_SEMESTER -> {

                //set the #headerPosition as global variable
                //if the headerPosition is #header of hte  next node which is the Course

                val semester: SemesterListItem = consolidatedList[position] as SemesterListItem
                val semesterViewHolder: SemesterViewHolder = holder as SemesterViewHolder
                semesterViewHolder.header.text = semester.header
                headerPosition = position


            }

            ListItem.TYPE_COURSE -> {
                val course: CourseListItem = consolidatedList[position] as CourseListItem
                val courseViewHolder: CourseViewHolder = holder as CourseViewHolder
                courseViewHolder.courseCodeTv.text = course.courseRegistered.courseCode
                courseViewHolder.courseName.text = course.courseRegistered.courseName
                courseViewHolder.credit.text = course.courseRegistered.creditAmount.toString() + "CR"
                courseViewHolder.evaluationGrade.text = course.courseRegistered.gradeEvaluation


                //logic for setting First&Last Course of Semester to rounded_cornor
                if (headerPosition + 1 == position ) {
                    courseViewHolder.containter.setBackgroundResource(R.drawable.rounded_top_cornor)
                }

                if (position == consolidatedList.size-1 && consolidatedList[position].getType()==ListItem.TYPE_COURSE) {
                    courseViewHolder.containter.setBackgroundResource(R.drawable.rounded_bottom_cornor)
                }

                if (position+1 <= consolidatedList.size-1){
                    if (consolidatedList[position+1].getType()==ListItem.TYPE_SEMESTER) {
                        courseViewHolder.containter.setBackgroundResource(R.drawable.rounded_bottom_cornor)
                    }
                }



            }




        }



    }



    override fun getItemViewType(position: Int): Int {
        return consolidatedList[position].getType()
    }
}