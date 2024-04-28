package TESTSAMPLE;

public class DaiStudentBST {
    public static void main(String[] args) {
        DaiStudentBST tree = new DaiStudentBST();
        DaiStudent s1 = new DaiStudent(1, "A", 70.0);
        DaiStudent s2 = new DaiStudent(2, "B", 65.0);
        DaiStudent s3 = new DaiStudent(3, "C", 80.0);
        DaiStudent s4 = new DaiStudent(4, "D", 78.0);
        DaiStudent s5 = new DaiStudent(5, "E", 68.0);

        tree.addStudent(s1);
        tree.addStudent(s2);
        tree.addStudent(s3);
        tree.addStudent(s4);
        tree.addStudent(s5);

        System.out.println(tree.nextStudent(s1));
        System.out.println(tree.nextStudent(s5));
        System.out.println(tree.nextStudent(s3));
        System.out.println(tree.nextStudent(s4));
    }
    protected Node root;


//    public DaiStudent nextStudent(DaiStudent student) {
//        Node temp = root;
//        while (student.GPA != temp.data.GPA) {
//            if (student.GPA > temp.data.GPA) {
//                temp = temp.right;
//            } else {
//                temp = temp.left;
//            }
//        }
//        if (temp.right == null) {
//            return null;
//        } else {
//            temp = temp.right;
//             while (temp.left != null) {
//                 temp = temp.left;
//             }
//
//        }return temp.data;
//
//
//    }
    public DaiStudent nextStudent(DaiStudent student) {
        Node current = root;
        DaiStudent successor = null;

        // Traverse the tree to find the node with GPA greater than given student's GPA
        while (current != null) {
            if (student.GPA < current.data.GPA) {
                if (successor == null || current.data.GPA < successor.GPA) {
                    successor = current.data;
                }
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return successor;
    }

    public void addStudent(DaiStudent student) {
        if (root == null) {
            root = new Node(student);
            return;
        }
        Node temppar = null;
        Node temp = root;

        while (temp != null) {
            if (student.GPA > temp.data.GPA) {
                temppar = temp;
                temp = temp.right;
            }
            else {
                temppar = temp;
                temp = temp.left;
            }
        }
        Node stustu = new Node(student);
        stustu.parent = temppar;
        if (stustu.data.GPA > temppar.data.GPA) {
            temppar.right = stustu;
        }else {
            temppar.left = stustu;
        }
        }
    }


class DaiStudent{
    int id;
    String name;
    double GPA;

    public DaiStudent(int id, String name, double GPA) {
        this.id = id;
        this.name = name;
        this.GPA = GPA;
    }

}
class Node{
    DaiStudent data;
    Node parent, left, right;

    public Node(DaiStudent data) {
        this.data = data;
        parent = left = right = null;
    }
}
