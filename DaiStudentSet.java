import java.util.Arrays;
import java.util.Comparator;

class DaiStudent {
        int id;
        String name;
        double GPA;
        String grade;

        public DaiStudent(int id, String name, double GPA, String grade) {
                this.id = id;
                this.name = name;
                this.GPA = GPA;
                this.grade = grade;
        }
        public String getGrade() {
                return grade;
        }
}
public class DaiStudentSet {
        private DaiStudent[] students;
        public  DaiStudentSet(DaiStudent[] students){
                this.students = students;
        }
        //Complexity: O(n)
        public int countGrade(String grade) {
                int count = 0;
                for (DaiStudent student : students){
                        if (student.getGrade().equals(grade)){
                                count++;
                        }
                }
                return count;
        }
        //Complexity: 0(N log(N))
        public DaiStudent[] sortByGrade() {
                DaiStudent[] sortedStudents = Arrays.copyOf(students, students.length);
                Arrays.sort(sortedStudents, new Comparator<DaiStudent>() {
                        @Override
                        public int compare(DaiStudent s1, DaiStudent s2) {
                                String[] gradeOrder = {"HD", "DI", "CR", "PA", "NN"};
                                int index1 = Arrays.asList(gradeOrder).indexOf(s1.getGrade());
                                int index2 = Arrays.asList(gradeOrder).indexOf(s2.getGrade());
                                return Integer.compare(index1, index2);
                        }
                });
                return sortedStudents;
        }

        public static void main(String[] args) {
                DaiStudent[] students = {new DaiStudent(1, "Dai Handsome 1", 3.1, "HD"),
                                         new DaiStudent(2, "Dai Handsome 2", 3.0, "HD"),
                                         new DaiStudent(3, "Dai 3", 2.5, "DI"),
                                         new DaiStudent(4, "Dai 4", 2.8, "CR"),
                                         new DaiStudent(5, "Dai 5", 2.2, "CR"),
                                         new DaiStudent(6, "Dai 6", 2.0, "PA"),
                                         new DaiStudent(7, "Dai 7", 1.0, "NN")};
                DaiStudentSet set = new DaiStudentSet(students);
                System.out.println("Number of 'NN' students is " + set.countGrade("NN"));
                System.out.println("Number of 'HD' students is " + set.countGrade("HD"));

                DaiStudent[] sortedStudents = set.sortByGrade();
                for (DaiStudent student: sortedStudents) {
                        System.out.println(student.name + "-" + student.grade+ "-" + student.GPA);
                }
        }
}
