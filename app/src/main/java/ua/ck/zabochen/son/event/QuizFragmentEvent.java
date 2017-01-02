package ua.ck.zabochen.son.event;

public class QuizFragmentEvent {

    private boolean response;

    public QuizFragmentEvent(boolean response) {
        this.response = response;
    }

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

}
