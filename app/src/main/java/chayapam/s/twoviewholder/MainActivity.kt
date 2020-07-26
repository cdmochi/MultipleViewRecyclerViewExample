package chayapam.s.twoviewholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var rawData = ArrayList<CourseRegistered>()
    private var consolidatedList = ArrayList<ListItem>()
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : Adapter
    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val sem1 = "SEMESTER 1/2019"
        val sem2 = "SEMESTER 2/2019"

        recyclerView = ui_semester_course_rv
        rawData.add(
            CourseRegistered(
                sem1
            , "PROFESSIONAL ETHIC SEMINAR IVV"
            ,"BG14038",0,"S"))

        rawData.add(
            CourseRegistered(
                sem1
                , "SELECTED TOPIC IN CISCO NETWORKING WORKSHOP"
                ,"CS4409",3,"A"))

        rawData.add(CourseRegistered(
                sem1, "SOCIAL INTERESTS, GOVERNMENT POLICIES"
                    ,"MT4201",3,"B"))


        rawData.add(
            CourseRegistered(
                sem2
                , "SOFTWARE ENGINEERING"
                ,"CS3414",3,"A"))

        rawData.add(
            CourseRegistered(
                sem2
                , "UI/UX DESIGN AND PROTOTYPING"
                ,"CS3436",3,"A"))


        rawData.add(
            CourseRegistered(
                sem2
                , "ANDROID APPLICATION DEVELOPMENT"
                ,"CS3431",3,"A"))


        rawData.add(
            CourseRegistered(
                sem2
                , "DIGITAL TRANSFORMATION"
                ,"CS4415",3,"A"))


        rawData.add(
            CourseRegistered(
                sem2
                , "SENIOR PROJECT II"
                ,"CS4200",3,"C+"))


        val courseRegisteredHashMap= groupDataToHashMap(rawData)

        // Add To Linear ArrayList
        for (semesterKey in courseRegisteredHashMap.keys){
            var newSemester : SemesterListItem = SemesterListItem()
            newSemester.header = semesterKey
            consolidatedList.add(newSemester)

            for (courseValue in courseRegisteredHashMap.getValue(semesterKey)) {
                val newCourseTaken : CourseListItem = CourseListItem()
                newCourseTaken.courseRegistered = courseValue
                consolidatedList.add(newCourseTaken)
            }
        }


        adapter = Adapter(consolidatedList)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)




    }

    // convert the List to the Hashmap
    private fun groupDataToHashMap(courseRegisteredList : List<CourseRegistered>)
            : MutableMap<String, ArrayList<CourseRegistered>> {

        var groupHash : MutableMap<String, ArrayList<CourseRegistered>> = mutableMapOf()

        for (courseRegistered in courseRegisteredList) {
            //use semester as a key
            //To map Semester -> [ LIST OF COURSES ]
            val hashKey = courseRegistered.semester
            if (groupHash.containsKey(hashKey)) {
                //get the list from HashKey and add it to existing List
                groupHash.getValue(hashKey).add(courseRegistered)
            }
            else {
                //No following Keys existed
                //Create newList & addItem
                //Put new Key -> List to the groupHash
                val emptyList = ArrayList<CourseRegistered>()
                emptyList.add(courseRegistered)
                groupHash[hashKey] = emptyList
            }
        }
        return groupHash
    }

}