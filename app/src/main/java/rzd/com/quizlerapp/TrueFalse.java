package rzd.com.quizlerapp;

public class TrueFalse {

    private int questions;
    private boolean answer;

    public TrueFalse(int mQuestions, boolean mAnswers){
        questions = mQuestions;
        answer = mAnswers;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
