class StudyRegister {

    open class Human(val name: String, var age: Int) {
        fun getOlder() {
            age++
        }
    }

    class CourseRecord(
        val name: String,
        val yearCompleted: Int,
        val credits: Int,
        val grade: Double
    )

    class Student(name: String, age: Int) : Human(name, age) {
        private val courses = mutableListOf<CourseRecord>()

        fun addCourse(course: CourseRecord) {
            courses.add(course)
        }

        fun getCourses(): List<CourseRecord> = courses

        fun weightedAverage(): Double {
            val totalWeightedGrades = courses.sumOf { it.grade * it.credits }
            val totalCredits = courses.sumOf { it.credits }
            return if (totalCredits == 0) 0.0 else totalWeightedGrades / totalCredits
        }

        fun weightedAverage(year: Int): Double {
            val filteredCourses = courses.filter { it.yearCompleted == year }
            val totalWeightedGrades = filteredCourses.sumOf { it.grade * it.credits }
            val totalCredits = filteredCourses.sumOf { it.credits }
            return if (totalCredits == 0) 0.0 else totalWeightedGrades / totalCredits
        }

        fun minMaxGrades(): Pair<Double, Double> {
            val grades = courses.map { it.grade }
            return grades.minOrNull()!! to grades.maxOrNull()!!
        }
    }

    class Major(val name: String) {
        private val students = mutableListOf<Student>()


        fun addStudent(student: Student) {
            students.add(student)
        }


        fun stats(): Triple<Double, Double, Double> {
            if (students.isEmpty()) return Triple(0.0, 0.0, 0.0)

            val averages = students.map { it.weightedAverage() }
            val min = averages.minOrNull() ?: 0.0
            val max = averages.maxOrNull() ?: 0.0
            val avg = averages.average()


            return Triple(min, max, avg)
        }


        fun stats(courseName: String): Triple<Double, Double, Double> {
            val averages = students.mapNotNull { student ->
                val courseGrades = student.getCourses().filter { it.name == courseName }
                if (courseGrades.isNotEmpty()) {
                    val totalWeightedGrades = courseGrades.sumOf { it.grade * it.credits }
                    val totalCredits = courseGrades.sumOf { it.credits }
                    if (totalCredits > 0) totalWeightedGrades / totalCredits else null
                } else {
                    null
                }
            }

            if (averages.isEmpty()) return Triple(0.0, 0.0, 0.0)

            val min = averages.minOrNull()!!
            val max = averages.maxOrNull()!!
            val avg = averages.average()

            return Triple(min, max, avg)
        }
    }


}
