package grpassg.grp5;

public class Question {
    private int type;
    private char answer;
    private String theQues;
    private String choices[] = new String[4];
    private String quesPic;
    private boolean selected[] = new boolean[4];

    public Question(int t, char a, String tq, String c[], String qp) { //Question's constructor
        type = t;
        answer = a;
        theQues = tq;
        choices = c.clone();
        quesPic = qp;

        for (int k = 1; k < 4; k++)
            selected[k] = false;
    }

    public Question() {

    }

    public int getType() {
        return type;
    } //Function to get the type of questions

    public char getAns() {return answer; } //Function to get the correct Answer

    public String getTheQues() {
        return theQues;
    } //Function to get the questions

    public String getQuesPic() {
        return quesPic;
    } //Function to get the question's image

    public String getChoice(int no) {
        return choices[no];
    } //Function to get the choices available

    public boolean getSelected(int no) {
        return selected[no];
    } //Function to get the user's input

    public void setSelected(int no, boolean status) {
        selected[no] = status;
    }

}