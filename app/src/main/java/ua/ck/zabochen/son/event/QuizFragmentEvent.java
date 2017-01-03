package ua.ck.zabochen.son.event;

public class QuizFragmentEvent {

    private boolean answer;

    public QuizFragmentEvent(boolean response) {
        this.answer = response;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

}
