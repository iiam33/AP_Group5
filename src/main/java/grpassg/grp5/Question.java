package grpassg.grp5;

public class Question {
    private int type;
    private char answer;
    private String theQues;
    private String choices[] = new String[4];
    private String quesPic;
    private boolean selected[] = new boolean[4];

    public Question(int t, char a, String tq, String c[], String qp) {
        type = t;
        answer = a;
        theQues = tq;
        choices = c.clone();
        quesPic = qp;

        for (int k = 1; k < 4; k++)
            selected[k] = false;
    }
    public int getType() {
        return type;
    }

    public String getTheQues() {
        return theQues;
    }

    public String getQuesPic() {
        return quesPic;
    }
    public String getChoice(int no) {
        return choices[no];
    }

    public boolean getSelected(int no) {
        return selected[no];
    }

    public void setSelected(int no, boolean status) {
        selected[no] = status;
    }
}